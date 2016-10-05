package com.libmoustache.gpstracker_maps;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GpsTracker extends Service implements LocationListener{
	private Context m_context;
	private final static String LOG_TAG="GpsTracker";
	private static GpsTracker m_instance;
	private static boolean isGpsTrackerInit=false;
	// The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
 
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
   
    // Declaring a Location Manager
    protected LocationManager m_locationManager;
    // flag for GPS status
    boolean isGPSEnabled = false;
 
    // flag for network status
    boolean isNetworkEnabled = false;
 
    boolean canGetLocation = false;
 
    Location m_location; // location
    double latitude; // latitude
    double longitude; // longitude
    float velocity=0;
    public GpsTracker(Context context) {
        this.m_context = context;
        m_locationManager = (LocationManager) m_context.getSystemService(Context.LOCATION_SERVICE);
        List<String> listaProviders = m_locationManager.getAllProviders();
        
        //getLocation();
    }
    public static void init(Context context)
    {
    	if(!isGpsTrackerInit)
    	{
    		m_instance=new GpsTracker(context);
    		isGpsTrackerInit=true;
    	}
    	else
    	{
    		Log.v(LOG_TAG,"GPSTracker instance has already activated");
    	}
    }
    public static GpsTracker getInstance()
    {
    	if(!isGpsTrackerInit)
    	{
    		Log.v(LOG_TAG,"is very important to init The context first");
    	}
    	return m_instance;
    }
    public boolean connect()
    {
	 try {
             m_locationManager = (LocationManager) m_context.getSystemService(LOCATION_SERVICE);
             m_locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                     3000,   // 3 sec
                     0, this);
             // getting GPS status
             isGPSEnabled = m_locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
  
             // getting network status
             isNetworkEnabled = m_locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
             if(isNetworkEnabled && isGPSEnabled) {
            	 Toast.makeText(m_context, "everything is correct", Toast.LENGTH_SHORT).show();
            	 getInitLocation();
            	 return true;
             }
             else
             {
            	 Toast.makeText(m_context, "No GPS or NOT NEt", Toast.LENGTH_SHORT).show();
             }
             
             return true;
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    		 return false;
    	 }
    }
    public boolean areDevicesConnected()
    {
    	//return isGPSEnabled&isNetworkEnabled;
    	return true;
    }
    public Location getInitLocation()
    {
    	try
    	{
	    if (isNetworkEnabled) {
	        m_locationManager.requestLocationUpdates(
	                LocationManager.NETWORK_PROVIDER,
	                MIN_TIME_BW_UPDATES,
	                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
	        Log.d("Network", "Network");
	        if ( m_locationManager != null) {
	            m_location =  m_locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

	        }
	    }
	    // if GPS Enabled get lat/long using GPS Services
	    if (isGPSEnabled) {
	        if (m_location == null) {
	        	 m_locationManager.requestLocationUpdates(
	                    LocationManager.GPS_PROVIDER,
	                    MIN_TIME_BW_UPDATES,
	                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
	            Log.d("GPS Enabled", "GPS Enabled");
	            if ( m_locationManager != null) {
	                m_location =  m_locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	            }
	        }
	    }
    	}
    	catch(Exception e)
    	{
    		Log.v(LOG_TAG,"___error getting the geolocalization");
    	}
    	return m_location;
	}
	@Override
	public void onLocationChanged(Location location) {
		Toast.makeText(m_context, "OnLocationChanged", Toast.LENGTH_SHORT).show();
		m_location=location;
		MapController.getInstance().addMarkerInMap(location.getLatitude(), location.getLongitude(), "My Location",false);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(m_context, "GPS TURN OFF", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(m_context, "GPS TURN ON", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Toast.makeText(m_context, "onStatusChanged provider "+provider+" status "+status, Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
    public double getLatitude(){
    	
    	if(m_location!=null)
    	{
	     latitude = m_location.getLatitude();
	     } 
	     return latitude;
	 }
	     
	    /**
	     * Function to get longitude
	     * */
	    public double getLongitude(){
	    	if(m_location!=null){
	    		longitude = m_location.getLongitude();
	        }
	        return longitude;
	    }
	    
	    public float getVelocity()
	    {
	    	if(m_location!=null)
	    	{
	    	velocity=m_location.getSpeed();
	    	}
	    	return velocity;
	    	
	    }
	   
	    

}
