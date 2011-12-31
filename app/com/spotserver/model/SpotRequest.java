package com.spotserver.model;

public class SpotRequest {

	private Location near;
	private double maxDistanceInKm;

	public SpotRequest() {
		super();
	}

	public SpotRequest(Location near, double maxDistanceInKm) {
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
