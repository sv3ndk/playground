package controllers;

import com.spotserver.model.Location;
import com.spotserver.model.SpotList;
import com.spotserver.model.SpotRequest;
import com.spotserver.service.SpotBeans;

import play.mvc.Controller;

public class SpotServiceRest extends Controller {

	public static void getSpots(SpotRequest request) {
		SpotList response = new SpotList();
		
		
		if (request == null) {
			request = new SpotRequest(new Location(),0);
		}
		
		
		response.setSpots(SpotBeans.getConfirmedSpotDao().searchSpotsNear(request.getNear(), request.getMaxDistanceInKm()));
		
		
		
		renderJSON(response);
	}

}
