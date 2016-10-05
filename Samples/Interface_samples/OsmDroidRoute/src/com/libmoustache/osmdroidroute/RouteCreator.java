package com.libmoustache.osmdroidroute;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.bonuspack.location.GeocoderNominatim;
import org.osmdroid.bonuspack.overlays.FolderOverlay;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.Polygon;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.bonuspack.routing.RoadNode;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.DirectedLocationOverlay;

import com.libmoustache.osmdroidroute.model.AddressModel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class RouteCreator {
	
	private MapView m_map;
	private Context m_context;
	
	private Marker m_startMarker;
	private Marker m_FinishMarker;
	protected DirectedLocationOverlay myLocationOverlay;
	
	private GeoPoint startPoint;
	private GeoPoint destinationPoint;
	private List<GeoPoint> m_routePoints;
	
	protected Polygon mDestinationPolygon;
	
	private ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
	
	static String SHARED_PREFS_APPKEY = "OSMNavigator";
	static String PREF_LOCATIONS_KEY = "PREF_LOCATIONS";
	private final static String LOG_TAG="RouteCreator(CLASS)";
	protected FolderOverlay itineraryMarkers; 
	public enum ROUTE_RANGES
	{
		RR_START_POINT,
		RR_FINISH_POINT
	}
	
	public RouteCreator(Context context,MapView map) {
		m_context=context;
		m_map=map;
		// TODO Auto-generated constructor stub
	}
	public void clear()
	{
		
	}
	public void setStartAddress(String address)
	{
		//searchAddress(address, ROUTE_RANGES.RR_START_POINT);
		
		FindAddressTask task=new FindAddressTask();
		AddressModel a=new AddressModel();
		a.setAddress(address);
		a.setRouteType(ROUTE_RANGES.RR_START_POINT);
		task.execute(a);
	}
	public void setFinishAddress(String address)
	{
		//searchAddress(address, ROUTE_RANGES.RR_FINISH_POINT);
		FindAddressTask task=new FindAddressTask();
		AddressModel a=new AddressModel();
		a.setAddress(address);
		a.setRouteType(ROUTE_RANGES.RR_FINISH_POINT);
		task.execute(a);
	}
	public void createRoute()
	{
		RoadSync rs=new RoadSync();
		rs.execute();
	}
	
	public void searchAddress(String saddress, ROUTE_RANGES rr)
	{
		//Hide the soft keyboard:
		/*InputMethodManager imm = (InputMethodManager)m_context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(locationEdit.getWindowToken(), 0);*/
		
		String locationAddress = saddress;
		
		if (locationAddress.equals("")){
			//removePoint(index);
			//map.invalidate();
			return;
		}
		
		//Toast.makeText(m_context, "Searching:\n"+locationAddress, Toast.LENGTH_LONG).show();
		AutoCompleteOnPreferences.storePreference(m_context, locationAddress, SHARED_PREFS_APPKEY, PREF_LOCATIONS_KEY);
		GeocoderNominatim geocoder = new GeocoderNominatim(m_context);
		geocoder.setOptions(true); //ask for enclosing polygon (if any)
		try {
			List<Address> foundAdresses = geocoder.getFromLocationName(locationAddress, 3);
			if (foundAdresses.size() == 0) { //if no address found, display an error
				//Toast.makeText(m_context, "Address not found.", Toast.LENGTH_SHORT).show();
			} else {
				Address address = foundAdresses.get(0); //get first address
				if (rr.equals(ROUTE_RANGES.RR_START_POINT)){
					startPoint = new GeoPoint(address.getLatitude(), address.getLongitude());
					/*markerStart = updateItineraryMarker(markerStart, startPoint, START_INDEX,
						R.string.departure, R.drawable.marker_departure, -1);
					map.getController().setCenter(startPoint);*/
				} else if (rr.equals(ROUTE_RANGES.RR_FINISH_POINT))
				{
					destinationPoint = new GeoPoint(address.getLatitude(), address.getLongitude());
					/*markerDestination = updateItineraryMarker(markerDestination, destinationPoint, DEST_INDEX,
						R.string.destination, R.drawable.marker_destination, -1);
					map.getController().setCenter(destinationPoint);*/
				}
				/*getRoadAsync();
				//get and display enclosing polygon:
				Bundle extras = address.getExtras();
				if (extras != null && extras.containsKey("polygonpoints")){
					ArrayList<GeoPoint> polygon = extras.getParcelableArrayList("polygonpoints");
					//Log.d("DEBUG", "polygon:"+polygon.size());
					updateUIWithPolygon(polygon);
				} else {
					updateUIWithPolygon(null);
				}*/
			}
		} catch (Exception e) {
			//Toast.makeText(m_context, "Geocoding error", Toast.LENGTH_SHORT).show();
			Log.v(LOG_TAG,"Geocoding error"+e.toString());
		}
	}
	private class FindAddressTask extends AsyncTask<AddressModel, Void, String> {

		@Override
		protected String doInBackground(AddressModel... params) {
			// TODO Auto-generated method stub
			AddressModel am=params[0];
			searchAddress(am.getAddress(), am.getRouteType());
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}
	private class RoadSync extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			int location=-1;
			waypoints.add(startPoint);
			waypoints.add(destinationPoint);
			if(m_routePoints==null)
			{
				m_routePoints=new ArrayList();
			}
			m_routePoints.clear();
			//m_routePoints.add(startPoint);
			RoadManager roadManager = new OSRMRoadManager();
			Road road = roadManager.getRoad(waypoints);
			Drawable nodeIcon = m_context.getResources().getDrawable(R.drawable.marker_node);
	        for (int i=0; i<road.mNodes.size(); i++){
	                RoadNode node = road.mNodes.get(i);
	                Marker nodeMarker = new Marker(m_map);
	                nodeMarker.setPosition(node.mLocation);
	                nodeMarker.setIcon(nodeIcon);
	                nodeMarker.setTitle("Step "+i);
	                m_map.getOverlays().add(nodeMarker);
	                m_routePoints.add(node.mLocation);
	        }
	        if (mDestinationPolygon != null)
	        {
				location = m_map.getOverlays().indexOf(mDestinationPolygon);
	        }
	        
	        mDestinationPolygon = new Polygon(m_context);
			mDestinationPolygon.setFillColor(0x30FF0080);
			mDestinationPolygon.setStrokeColor(0x800000FF);
			mDestinationPolygon.setStrokeWidth(5.0f);
			mDestinationPolygon.setPoints(m_routePoints);
			if (location != -1)
				m_map.getOverlays().set(location, mDestinationPolygon);
			else
				m_map.getOverlays().add(mDestinationPolygon);
			    //m_map.invalidate();
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}

}
