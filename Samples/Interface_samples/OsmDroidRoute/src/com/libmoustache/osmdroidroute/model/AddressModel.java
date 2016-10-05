package com.libmoustache.osmdroidroute.model;

import com.libmoustache.osmdroidroute.RouteCreator.ROUTE_RANGES;
public class AddressModel {
	ROUTE_RANGES routeType;
	String address;
	public ROUTE_RANGES getRouteType() {
		return routeType;
	}
	public void setRouteType(ROUTE_RANGES routeType) {
		this.routeType = routeType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
