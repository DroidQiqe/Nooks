package ata.it.solutions.nooks;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;

public class Fragment_Home_Inicio extends Fragment  implements OnQueryTextListener,LocationListener,OnMarkerClickListener,OnInfoWindowClickListener{
	
	private static View view,viewx;
	private LocationManager mLocationManager;
	private Boolean GPS_ON=false;
	private Boolean NETWORK=false;
	private GoogleMap mGoogleMap;
	private Marker mMarkerLocation;
	private double mLatitudeP;
	private double mLongitudP;
	private double mLatitudeP2;
	private double mLongitudP2;
	public Fragment_Home_Inicio(){
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);

		}

		try {
			view = inflater.inflate(R.layout.fragment_home_inicio, container,
					false);
		} catch (InflateException e) {
			 e.printStackTrace();
		}
		
		
		
		
		if (getLocationPermission())
		{
			viewx=view;
			InicializarGoogleMaps();
		}
		
			
		
	
		return viewx;
			
		
		
		
		
	}
	
	
	


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		 mLocationManager.removeUpdates(this);
		super.onDestroy();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void InicializarGoogleMaps() {
		// TODO Auto-generated method stub
	
		if (mGoogleMap==null)
		{
			mGoogleMap=((SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			
			
			
			if (mGoogleMap != null)
			{
				//mGoogleMap.setMyLocationEnabled(true);
				mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				mLocationManager=(LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
				Criteria mCriteria=new Criteria();
				String mProvider=mLocationManager.getBestProvider(mCriteria, true);
				Location mLastLocation=mLocationManager.getLastKnownLocation(mProvider);
				mGoogleMap.setOnMarkerClickListener(this);
				mGoogleMap.setOnInfoWindowClickListener(this);
				
				if(mLastLocation !=null)
				{
					onLocationChanged(mLastLocation);
				}
				mLocationManager.requestLocationUpdates(mProvider, 6000, 50, this);
			}
		}
	}

	public boolean getLocationPermission(){
		
		mLocationManager=(LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		
		try
		{
			GPS_ON=mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		try
		{
			NETWORK=mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		if (!GPS_ON && !NETWORK)
		{
			showAlertProvider();
			return (false);
		}
		else
		{
			return (true);
		}
			
		
		
		
	
	}

	
	
	
	
	
	private void showAlertProvider() {
		// TODO Auto-generated method stub
		AlertDialog.Builder dialogEliminar = new AlertDialog.Builder(getActivity());
		dialogEliminar.setIcon(R.drawable.alertnooks);
		dialogEliminar.setTitle("Atencion");
		dialogEliminar.setMessage("Nooks ha detectado que no le has concedido ningun proveedor para localizarte, ¿Deseas activar esta opción? ");
		dialogEliminar.setCancelable(false);
		dialogEliminar.setPositiveButton("Si", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int boton) {
				 Intent intent = new Intent(
			                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				 
				 Fragment_Home_Inicio.this.startActivity(intent);
			}
		});
		
		dialogEliminar.setNegativeButton("No",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity().getApplicationContext(),"Lo sentimos, pero Nooks requiere de tu ubicacion,No se podra continuar", Toast.LENGTH_LONG).show();
				getActivity().finish()	;
				}
		});
	
		dialogEliminar.show();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.vistabusqueda, menu);
		MenuItem searchItem = menu.findItem(R.id.menu3_buscar);
		SearchView msearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		 msearchView.setQueryHint("Buscar lugar");
	    msearchView.setOnQueryTextListener(this);
	    
	}

	@Override
	public boolean onQueryTextChange(String place) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	@Override
	public boolean onQueryTextSubmit(String place) {
		// TODO Auto-generated method stub
		
		
		if (place !=null)
		{
			double GuadLatitude=20.686426;
			double GuadLongitud=-103.333433;
			
			LatLng GuadLatLong=new LatLng(GuadLatitude,GuadLongitud);
			
			CameraPosition mCP=new CameraPosition.Builder()
			.target(GuadLatLong)
			.zoom(17)
			.bearing(0)
			.tilt(90)
			.build();
			
			mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(mCP));
			
			
			
			//
			Marker mMarkerG= mGoogleMap.addMarker(new MarkerOptions()
			.position(GuadLatLong)
			.title("Guadalajara")
			.snippet("Pulsa para ver más opciones")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.pg))		
			);
			//
			
		
			
		}
		
		return true;
	}

	@Override
	public void onLocationChanged(Location mLocation) {
		// TODO Auto-generated method stub
		
		try
		{
			mGoogleMap.clear();
			double mLatitud=mLocation.getLatitude();
			double mLongitud=mLocation.getLongitude();
			
			mLatitudeP=mLatitud;
			mLongitudP=mLongitud;
			
		
			
			
			LatLng mLatLong=new LatLng(mLatitud,mLongitud);
			
			CameraPosition mCP=new CameraPosition.Builder()
			.target(mLatLong)
			.zoom(17)
			.bearing(0)
			.tilt(90)
			.build();
			
			mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(mCP));
			

			
			//mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLong));
			//mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
			 mMarkerLocation=mGoogleMap.addMarker(new MarkerOptions()
			.position(mLatLong)
			.title("Tú estas aqui")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.posicion))
			);
			 
			
		}
		catch(Exception ex){
			Toast.makeText(getActivity().getApplicationContext(), "Ocurrio un problema interno:"+ex, Toast.LENGTH_LONG).show();
		}
		
		
		
		
		
		
		
	}


	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMarkerClick(final Marker marker) {
		// TODO Auto-generated method stub
		
		if (marker.getTitle().equals("Tú estas aqui"))
		{

			
	        final Handler handler = new Handler();
	        
	        marker.showInfoWindow();
	        
	        final long startTime = SystemClock.uptimeMillis();
	        final long duration = 2000;
	        
	        Projection proj = mGoogleMap.getProjection();
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
	        
	        return true;
		}
		
		
		return false;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		// TODO Auto-generated method stub
		if (marker.getTitle().equals("Guadalajara"))
		{
			
			
			final LatLng mMarkerLatLng=mMarkerLocation.getPosition();
			String Latitude="LocLat";
			String Longitud="LocLng";
			
			Intent intDetais=new Intent();
			intDetais.setClass(getActivity().getApplicationContext(),DetailsPlace.class);
			intDetais.putExtra(Latitude,mMarkerLatLng.latitude);
			intDetais.putExtra(Longitud, mMarkerLatLng.longitude);
			
			startActivity(intDetais);
		}
		
	}

	

}
