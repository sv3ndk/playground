package com.spotserver.service;

import java.util.Date;
import java.util.List;

import com.spotserver.dao.IConfirmedSpotDao;
import com.spotserver.model.ConfirmedSpot;
import com.spotserver.model.Location;
import com.spotserver.model.SpotException;
import com.spotserver.model.Status;

/**
 * @author svend
 *
 */
public class SpotService implements ISpotService{

	// spots reports which are less then 50 meters from an existing spot are assumed to be identical to this exising spot 
	public final static double SPOT_TOLERANCE_IN_METERS = 50;
	
	@Override
	public Status reportNewSpot(double latitude, double longitude) throws SpotException {
		
		IConfirmedSpotDao confirmedSpotDao = SpotBeans.getConfirmedSpotDao();
		
		List<ConfirmedSpot> existingSpots = confirmedSpotDao.searchSpotsNear(new Location(latitude, longitude), SPOT_TOLERANCE_IN_METERS/1000);
		
		if (existingSpots == null || existingSpots.isEmpty()) {
			// ok, we can add this new spot
			confirmedSpotDao.addSpot(latitude, longitude, new Date());
			return new Status(true, "thanks, spot added");
		} else {
			// TODO: add a +1 vote here
			return new Status(true, "thanks, we knew about this one already");
		}
		
	}
	
	
	

}