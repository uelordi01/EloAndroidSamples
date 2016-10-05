package com.libmoustache.gpstracker_maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class MainActivity extends  android.support.v4.app.FragmentActivity {
	private GoogleMap mapa;
	private Context m_context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mapa = ((SupportMapFragment) getSupportFragmentManager()
				   .findFragmentById(R.id.map)).getMap();
		m_context=getApplicationContext();
		GpsTracker.init(m_context);
		GpsTracker.getInstance().connect();
		MapController.getInstance().setMap(mapa);
		//GpsTracker.getInstance().setMap(mapa);
	//	GpsTracker.getInstance().putMarkerInMap();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
