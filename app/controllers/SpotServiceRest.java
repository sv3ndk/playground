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
			SpotListRequest req = Utils.parseAndValidateSpotServiceRequest(lat, longit, range);
			response.setSpots(SpotBeans.getConfirmedSpotDao().searchSpotsNear(req.getNear(), req.getMaxDistanceInKm()));
			response.setStatus(new Status(true, "happy"));
		} catch (SpotException e) {
			e.printStackTrace();
			response.setStatus(new Status(false, e.getMessage()));
		}

		renderJSON(response);
	}

	/**
	 * POST
	 * 
	 * /spotted : used to report a new spot
	 * 
	 * 
	 * @param lat
	 * @param longit
	 * @param range
	 */
	public static void postNewSpot(SpotReportRequest howCanIgetThis) {

		try {
			if (params == null || params.data == null || !params.data.containsKey("report")) {
				throw new SpotException("could not find a 'report' field in the post message");
			}
			
			String reportStr = params.data.get("report")[0];
			SpotReportRequest report = Utils.parseAndValidateSpotReportRequest(reportStr);
			Status status = SpotBeans.getSpotService().reportNewSpot(report.getSpotLocation().getLatitude(), report.getSpotLocation().getLongitude());
			renderJSON(status);
		} catch (Exception e) {
			Status status = new Status();
			e.printStackTrace();
			status.setOk(false);
			status.setMessage("error while trying to process spot report" + e.getMessage());
			renderJSON(status);
		}
	}

}
