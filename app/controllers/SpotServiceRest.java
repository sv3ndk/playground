package controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.spotserver.dao.hardcoded.Utils;
import com.spotserver.model.Location;
import com.spotserver.model.SpotException;
import com.spotserver.model.Status;
import com.spotserver.model.spotservice.SpotReportRequest;
import com.spotserver.model.spotservice.SpotList;
import com.spotserver.model.spotservice.SpotListRequest;
import com.spotserver.service.SpotBeans;

import play.mvc.Controller;

public class SpotServiceRest extends Controller {

	private final static ObjectMapper jsonMaper = new ObjectMapper();
	
	/**
	 * GET
	 * 
	 * /spot : used to query existing spots
	 * 
	 * 
	 * @param lat
	 * @param longit
	 * @param range
	 */
	public static void getSpots(String lat, String longit, String range) {
		SpotList response = new SpotList();
		
		try {
			SpotListRequest req = Utils.parseSpotServiceRequest(lat, longit, range);
			response.setSpots(SpotBeans.getConfirmedSpotDao().searchSpotsNear(req.getNear(), req.getMaxDistanceInKm()));
			response.setStatus(new Status(true,"happy"));
		} catch (SpotException e) {
			e.printStackTrace();
			response.setStatus(new Status(false,e.getMessage()));
		}
		
		renderJSON(response);
	}
	
	
	
	
	
	/**
	 * POST
	 * 
	 * /spotted  : used to report a new spot
	 * 
	 * 
	 * @param lat
	 * @param longit
	 * @param range
	 */
	public static void postNewSpot(SpotReportRequest howCanIgetThis) {
		
		Status response  = new Status();
		
		System.out.println("params " + params);
		System.out.println("params data" + params.data);
		for (String key: params.data.keySet()) {
			System.out.println("k " + key + " value: " + params.data.get(key));
		}
		
		if (params == null || params.data == null || ! params.data.containsKey("report")) {
			response.setOk(false);
			response.setMessage("could not find a 'report' field in the post message");
		}
		
		String reportStr = params.data.get("report")[0];
		
		SpotReportRequest report = null;
		try {
			report = jsonMaper.readValue(reportStr, SpotReportRequest.class);
		} catch (Exception e) {
			e.printStackTrace();
			response.setOk(false);
			response.setMessage("error while trying to deserialize json string " + e.getMessage());
		}
		
		
		
		if (report == null) {
			response.setOk(false);
			response.setMessage("no request found in POST body");
		} else if (report.getSpotLocation() == null || report.getSpotLocation().getLatitude() == null || report.getSpotLocation().getLatitude() == null) {
			response.setOk(false);
			response.setMessage("missing or invalid location in request");
		} else {
			response.setOk(true);
			response.setMessage("happy");
		}
		
		renderJSON(response);
		
	}
	
	

}
