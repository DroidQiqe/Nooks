package ata.it.solutions.nooks;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Gallery;
import android.widget.Toast;

public class DetailsPlace extends ActionBarActivity implements OnMarkerClickListener{

	private double mLatitud;
	private double mLongitud;
	
	private GoogleMap mOGoogleMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailsplace);
		
		 getSupportActionBar().setTitle("Detalles del lugar");
		 
		 Gallery gallery = (Gallery)findViewById(R.id.mGaleria);
	        gallery.setAdapter(new ImgAdapter(this));
	        
	        
	        Intent intent = getIntent();
	    	Bundle extra = intent.getExtras();
	    	
	    	
	    	mLatitud=extra.getDouble("LocLat");
	    	mLongitud=extra.getDouble("LocLng");
	    	
	    	
	    	ConfigMaps();
	    	
	    	
	    	
	}
	private void ConfigMaps() {
		// TODO Auto-generated method stub
		if (mOGoogleMap==null)
		{
			
			mOGoogleMap=((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapdetails)).getMap();
			
			if (mOGoogleMap!=null)
			{
				mOGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				
				LatLng mLatLong=new LatLng(mLatitud,mLongitud);
				MarkerOptions mO=new MarkerOptions();
				mO.position(mLatLong);
				
				//Se define un titulo para el Marker
				mO.title("Tú");
				mO.icon(BitmapDescriptorFactory.fromResource(R.drawable.posicion));
				
				//Agregas el Marker al mapa
				mOGoogleMap.addMarker(mO);
			mOGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLong));
				
				mOGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(5));
				
				double GuadLatitude=20.686426;
				double GuadLongitud=-103.333433;
				LatLng GLatLong=new LatLng(GuadLatitude,GuadLongitud);
				MarkerOptions mO2=new MarkerOptions();
				mO2.position(GLatLong);
				
				mO2.title("Objetivo");
				mO2.icon(BitmapDescriptorFactory.fromResource(R.drawable.pg));
				mOGoogleMap.addMarker(mO2);
				mOGoogleMap.setOnMarkerClickListener(this);
				
				
				
				
			}
		}
	}
	@Override
	public boolean onMarkerClick(final Marker marker) {
		// TODO Auto-generated method stub
		 final Handler handler = new Handler();
	        
	        marker.showInfoWindow();
	        
	        final long startTime = SystemClock.uptimeMillis();
	        final long duration = 2000;
	        
	        Projection proj = mOGoogleMap.getProjection();
	        final LatLng markerLatLng = marker.getPosition();
	        Point startPoint = proj.toScreenLocation(markerLatLng);
	        startPoint.offset(0, -100);
	        final LatLng startLatLng = proj.fromScreenLocation(startPoint);

	        final Interpolator interpolator = new BounceInterpolator();

	        handler.post(new Runnable() {
	            @Override
	            public void run() {
	                long elapsed = SystemClock.uptimeMillis() - startTime;
	                float t = interpolator.getInterpolation((float) elapsed / duration);
	                double lng = t * markerLatLng.longitude + (1 - t) * startLatLng.longitude;
	                double lat = t * markerLatLng.latitude + (1 - t) * startLatLng.latitude;
	                marker.setPosition(new LatLng(lat, lng));

	                if (t < 1.0) {
	                    // Post again 16ms later.
	                    handler.postDelayed(this, 16);
	                }
	            }
	        });
		
		
		return false;
	}

}
