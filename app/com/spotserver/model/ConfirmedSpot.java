package com.spotserver.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;

public class ConfirmedSpot {

	private final String DATE_FORMAT = "yyyyMMdd-HHmmss";
	
	
	private String id;
	private Location location;

	// moment when the first report about this spot was received
	private Date discoveryDate;

	// this describes the extend to which we are confident this spot actually exist (or still exists)
	// this must always be between 0 and 1 (both included)
	// 0 means not sure at all, 1 means absolute certainty
	private double confidenceLevel;

	public ConfirmedSpot() {
		super();
	}

	public ConfirmedSpot(Location location, Date discoveryDate, double confidenceLevel) {
		super();
		this.location = location;
		this.discoveryDate = discoveryDate;
		this.confidenceLevel = confidenceLevel;
		
	}
	
	public ConfirmedSpot(String id, Location location, Date discoveryDate, double confidenceLevel) {
		this (location, discoveryDate, confidenceLevel);
		this.id = id;
	}
	
	
	//////////////////////
	// mongo stuff
	

	public BasicDBObject toMongoDbo() {
		BasicDBObject converted = new BasicDBObject();
		converted.put("discoveryDate", new SimpleDateFormat(DATE_FORMAT).format(discoveryDate));
		converted.put("latitude", location.getLatitude());
		converted.put("longitude", location.getLongitude());
		converted.put("confidenceLevel", confidenceLevel);
		return converted;
		
	}
	
	
	
	//////////////////////

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDiscoveryDate() {
		return discoveryDate;
	}

	public void setDiscoveryDate(Date discoveryDate) {
		this.discoveryDate = discoveryDate;
	}

	public double getConfidenceLevel() {
		return confidenceLevel;
	}

	public void setConfidenceLevel(double confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}

}
