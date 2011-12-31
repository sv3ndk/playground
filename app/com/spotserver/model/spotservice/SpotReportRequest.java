package com.spotserver.model.spotservice;

import com.spotserver.model.Location;

public class SpotReportRequest {

	Location spotLocation;

	public Location getSpotLocation() {
		return spotLocation;
	}

	public void setSpotLocation(Location spotLocation) {
		this.spotLocation = spotLocation;
	}

	public SpotReportRequest() {
		super();
	}

	public SpotReportRequest(Location spotLocation) {
		super();
		this.spotLocation = spotLocation;
	}

}
