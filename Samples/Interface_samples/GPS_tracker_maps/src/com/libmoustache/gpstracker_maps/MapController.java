package com.libmoustache.gpstracker_maps;


import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapController {
	private GoogleMap m_mapa;
	private static final Object sSyncObj = new Object();
	private static volatile MapController m_instance;
	private final static String LOG_TAG="MapController(Class)";
	private boolean isDragging;
	public static MapController getInstance()
	{
		if (m_instance == null) 
	    {
	        synchronized (sSyncObj) 
	        {
	            if (m_instance == null) 
	            {
	            	m_instance = new MapController();
	            }
	        }
	    }
    return m_instance;
	}
	public boolean setMap(GoogleMap mapa)
    {
		if(mapa!=null)
		{
    	m_mapa=mapa;
    	m_mapa.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point) {
            	addMarkerInMap(point.latitude,point.longitude,"new point added",true);
            }
            });

		}
		m_mapa.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

			@Override
			public void onMarkerDrag(Marker arg0) {
				if(isDragging)
				{
					Log.v(LOG_TAG,"dragging pos "+arg0.getPosition().latitude+","+arg0.getPosition().longitude);
				}
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onMarkerDragEnd(Marker arg0) {
				// TODO Auto-generated method stub
				
				if(isDragging)
				{
					Log.v(LOG_TAG,"drag finished");
					isDragging=false;
				}
				
			}

			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub
				isDragging=true;
				Log.v(LOG_TAG,"drag started");
				
			}
		});
    	return true;
    }
	public void addMarkerInMap(double latitude,double longitude,String title,boolean dragabble)
	{
		if(m_mapa!=null)
		{
			m_mapa.addMarker(new MarkerOptions()
	        .position(new LatLng(latitude,longitude))
	        .title(title)
	        .draggable(dragabble)
	        );
		}
		else
		{
			Log.v(LOG_TAG,"___Map is not initialized");
		}
	}


}
