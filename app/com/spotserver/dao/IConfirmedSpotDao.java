package com.spotserver.dao;

import java.util.Date;
import java.util.List;

import com.spotserver.model.ConfirmedSpot;
import com.spotserver.model.Location;
import com.spotserver.model.SpotException;

/**
 * @author svend
 *
 */
public interface IConfirmedSpotDao {

	abstract public List<ConfirmedSpot> searchSpotsNear(Location location, double maxDistanceInKm)  throws SpotException;

	public abstract void addSpot(double latitude, double longitude, Date discoveryDate, double confidenceLevel)  throws SpotException;
	
	
}
