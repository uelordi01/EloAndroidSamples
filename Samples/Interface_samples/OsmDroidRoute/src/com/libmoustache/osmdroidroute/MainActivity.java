package com.libmoustache.osmdroidroute;

import java.util.ArrayList;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.bonuspack.overlays.FolderOverlay;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.MapQuestRoadManager;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.bonuspack.routing.RoadNode;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends Activity {
	RouteCreator rc;
	MapView map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			map = (MapView) findViewById(R.id.map);
	        map.setTileSource(TileSourceFactory.MAPNIK);
	        map.setBuiltInZoomControls(true);
	        map.setMultiTouchControls(true);

	        GeoPoint startPoint = new GeoPoint(48.13, -1.63);
	        IMapController mapController = map.getController();
	        mapController.setZoom(5);
	        mapController.setCenter(startPoint);
	        rc=new RouteCreator(this,map);
	     /*   Marker startMarker = new Marker(map);
	        startMarker.setPosition(startPoint);
	        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
	        
	        RoadManager roadManager = new OSRMRoadManager();
	        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
			waypoints.add(startPoint);
			GeoPoint endPoint = new GeoPoint(48.4, -1.9);
			waypoints.add(endPoint);
			Road road = roadManager.getRoad(waypoints);
			Polyline roadOverlay = RoadManager.buildRoadOverlay(road, this);
			map.getOverlays().add(roadOverlay);
			FolderOverlay roadMarkers = new FolderOverlay(this);
			map.getOverlays().add(roadMarkers);*/
			map.invalidate();
	        Button search=(Button)findViewById(R.id.search);
	        search.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					EditText estart=(EditText)findViewById(R.id.start_address);
					EditText eDestination=(EditText)findViewById(R.id.destination_address);
					rc.setStartAddress(estart.getText().toString());
					rc.setFinishAddress(eDestination.getText().toString());
					rc.createRoute();

				}
	        	
	        });
	        //RoadManager roadManager = new OSRMRoadManager();
	        
	       /* ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
	        waypoints.add(startPoint);
	        GeoPoint endPoint = new GeoPoint(48.4, -1.9);
	        waypoints.add(endPoint);
	        //Road road = roadManager.getRoad(waypoints);
	        //Polyline roadOverlay = RoadManager.buildRoadOverlay(road, this);
	        //map.getOverlays().add(roadOverlay);
	        
	        RoadManager roadManager = new MapQuestRoadManager("Fmjtd%7Cluur2q6t2h%2Crn%3Do5-9a20lw");
	        Road road = roadManager.getRoad(waypoints);/*
	        //roadManager.addRequestOption("routeType=bicycle");
	         */
	      /*  Drawable nodeIcon = getResources().getDrawable(R.drawable.marker_node);
	        for (int i=0; i<road.mNodes.size(); i++){
	                RoadNode node = road.mNodes.get(i);
	                Marker nodeMarker = new Marker(map);
	                nodeMarker.setPosition(node.mLocation);
	                nodeMarker.setIcon(nodeIcon);
	                nodeMarker.setTitle("Step "+i);
	                map.getOverlays().add(nodeMarker);
	        }*/
	        map.invalidate();
	        
	       
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */

}
