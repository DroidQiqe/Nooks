package ata.it.solutions.nooks;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;



public class InicioFragment extends ActionBarActivity implements ConnectionCallbacks,OnConnectionFailedListener{
	
	private static final int RC_SIGN_IN = 0;
	// Google client to interact with Google API
	private GoogleApiClient mGoogleApiClient;
	
	private SignInButton btnGooglePlus;
	private ConnectionResult mConnectionResult;
	private boolean mIntentInProgress;
	private boolean mSignInClicked;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_inicio);
        ActionBar ab=getSupportActionBar();
        ab.hide();
          
        
        btnGooglePlus=(SignInButton)findViewById(R.id.sign_in_button);
        
        btnGooglePlus.setOnClickListener(new View.OnClickListener() {
			
    			@Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				/*
    				 * Google+
    				 */
    				signInWithGplus();
    			}
    		});
            
        String TextGooglePlus="Iniciar sesión con Google";
        
        setGooglePlusButtonText(btnGooglePlus,TextGooglePlus);
        
        
        
        
        
        mGoogleApiClient = new GoogleApiClient.Builder(this)
		.addConnectionCallbacks(this)
		.addOnConnectionFailedListener(this).addApi(Plus.API)
		.addScope(Plus.SCOPE_PLUS_LOGIN).build(); 
        
  
       
	}
	
	
	private void setGooglePlusButtonText(SignInButton btnGooglePlus,String buttontxt) {
		
	    // Find the TextView that is inside of the SignInButton and set its text
	    for (int i = 0; i < btnGooglePlus.getChildCount(); i++) {
	        View v = btnGooglePlus.getChildAt(i);

	        if (v instanceof TextView) {
	            TextView tv = (TextView) v;
	            tv.setText(buttontxt);
	            return;
	        }
	    }
		
	}


	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}
	
	
	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}
	
	/**
	 * Method to resolve any signin errors
	 * */
	private void resolveSignInError() {
		if (mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;
				mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
			} catch (SendIntentException e) {
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}
	/**
	 * Sign-in into google
	 * */
	private void signInWithGplus() {
		if (!mGoogleApiClient.isConnecting()) {
			mSignInClicked = true;
			
			
			
			resolveSignInError();
		}
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			if (responseCode != RESULT_OK) {
				mSignInClicked = false;
			}

			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
				
			}
		}
	}
	

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		mSignInClicked = false;
		
		Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        
		// Get user's information
				getProfileInformation();
				finish();
	}

	private void getProfileInformation() {
		// TODO Auto-generated method stub
		try
		{
			if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
				Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
				String personName = currentPerson.getDisplayName();
				String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
				/*Toast.makeText(this, "USUARIO: "+personName, Toast.LENGTH_LONG).show();
				Toast.makeText(this, "GMAIL: "+email, Toast.LENGTH_LONG).show();*/
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public void onConnectionSuspended(int cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		if (!result.hasResolution()) {
			GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
					0).show();
			return;
		}

		if (!mIntentInProgress) {
			// Store the ConnectionResult for later usage
			mConnectionResult = result;

			if (mSignInClicked) {
				// The user has already clicked 'sign-in' so we attempt to
				// resolve all
				// errors until the user is signed in, or they cancel.
				resolveSignInError();
			}
		}
		
		
	}

	
	}