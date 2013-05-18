package com.spotserver.model.spotservice;

import com.spotserver.model.Location;

public class SpotListRequest {

	private Location near;
	private double maxDistanceInKm;

	public SpotListRequest() {
		super();
	}

	public SpotListRequest(Location near, double maxDistanceInKm) {
		super();
		this.near = near;
		this.maxDistanceInKm = maxDistanceInKm;
	}

	public Location getNear() {
		return near;
	}

	public void setNear(Location near) {
		this.near = near;
	}

	public double getMaxDistanceInKm() {
		return maxDistanceInKm;
	}

	public void setMaxDistanceInKm(double maxDistanceInKm) {
		this.maxDistanceInKm = maxDistanceInKm;
	}

}
