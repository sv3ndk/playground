package controllers;

import com.spotserver.dao.hardcoded.Utils;
import com.spotserver.model.Location;
import com.spotserver.model.SpotException;
import com.spotserver.model.SpotList;
import com.spotserver.model.SpotRequest;
import com.spotserver.model.Status;
import com.spotserver.service.SpotBeans;

import play.mvc.Controller;

public class SpotServiceRest extends Controller {

	public static void getSpots(String lat, String longit, String range) {
		SpotList response = new SpotList();
		
		try {
			SpotRequest req = Utils.parseSpotServiceRequest(lat, longit, range);
			response.setSpots(SpotBeans.getConfirmedSpotDao().searchSpotsNear(req.getNear(), req.getMaxDistanceInKm()));
			response.setStatus(new Status(true,"happy"));
		} catch (SpotException e) {
			e.printStackTrace();
			response.setStatus(new Status(false,e.getMessage()));
		}
		
		renderJSON(response);
	}
	
	
	
	
	
	
	

}
