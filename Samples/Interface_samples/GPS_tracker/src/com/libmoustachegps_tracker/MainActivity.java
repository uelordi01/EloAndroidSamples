package com.libmoustachegps_tracker;

import com.libmoustachegps_tracker.MyLocation.LocationResult;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
private Context m_context;
GpsTracker m_tracker;
MyLocation myLocation;
LocationResult locationResult;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		m_context=getApplicationContext();
		/*GpsTracker.init(m_context);
		GpsTracker.getInstance().connect();*/
		locationResult = new LocationResult(){
		    @Override
		    public void gotLocation(Location location){
		        //Got the location!
		    	Log.v("GPS","latitude: "+location.getLatitude()+"lONGITUDE: "+location.getLatitude());
		    	if(myLocation!=null) {
		    		//myLocation.getLocation(m_context, locationResult);
		    	}
		    }
		};
		myLocation = new MyLocation();
		myLocation.getLocation(this, locationResult);
	}
	/*public void getPosition(View view)
	{
		Toast.makeText(this, "latitude: "+GpsTracker.getInstance().getLatitude()+" longitude: "+GpsTracker.getInstance().getLongitude(), Toast.LENGTH_SHORT).show();
		Log.d("LOCATION ","latitude: "+GpsTracker.getInstance().getLatitude());
		Log.d("LOCATION ","longitude: "+GpsTracker.getInstance().getLongitude());
	}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
