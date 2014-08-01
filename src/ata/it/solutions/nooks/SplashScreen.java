package ata.it.solutions.nooks;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;



public class SplashScreen extends Activity implements AnimationListener{
	private static final long SPLASH_SCREEN_DELAY = 2000;
	private TextView txturl;
   Animation animFadeIn;
   Animation animFadeOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        txturl=(TextView)findViewById(R.id.textView1);
        
        /*
         * Load animations
         */
        animFadeIn=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animFadeOut=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        
        animFadeIn.setAnimationListener(this);
        animFadeOut.setAnimationListener(this);
        
        txturl.startAnimation(animFadeIn);
        
        
        TimerTask tarea= new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				/*
				 * Iniciar la pantalla de Login
				 */
				
				  Intent mainIntent = new Intent().setClass(
						  SplashScreen.this,InicioFragment.class);
	                startActivity(mainIntent);
				
				
				 // Close the activity so the user won't able to go back this
                // activity pressing Back button
             
                finish();
				
			}
        };
        
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(tarea, SPLASH_SCREEN_DELAY);
        
    }

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		if (animation==animFadeIn)
		{
			txturl.startAnimation(animFadeOut);
		}
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}


    
}
