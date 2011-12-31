/**
 * 
 */
package com.spotserver.dao.hardcoded;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.spotserver.dao.IConfirmedSpotDao;
import com.spotserver.model.ConfirmedSpot;
import com.spotserver.model.Location;

/**
 * @author svend
 *
 */
public class HardcodedConfirmedSpotDat implements IConfirmedSpotDao {

	
	
	
	
	/* (non-Javadoc)
	 * @see com.spotserver.dao.IConfirmedSpotDao#searchSpotsNear(com.spotserver.model.Location)
	 */
	@Override
	public List<ConfirmedSpot> searchSpotsNear(Location location, double maxDistanceInKm) {
		
		List<ConfirmedSpot> response = new LinkedList<ConfirmedSpot>();
		
		response.add(new ConfirmedSpot("1", new Location(57d, 43d), new Date(), 0.3));
		response.add(new ConfirmedSpot("2", new Location(58d, 43d), new Date(), 0.4));
		response.add(new ConfirmedSpot("3", new Location(59d, 43d), new Date(), 0.7));
		response.add(new ConfirmedSpot("4", new Location(60d, 43d), new Date(), 0.9));
		response.add(new ConfirmedSpot("5", new Location(61d, 43d), new Date(), 0.15));
		
		return response;
	}

	
	@Override
	public void addSpot(double latitude, double longitude, Date discoveryDate) {
		// NOP
	}

}
