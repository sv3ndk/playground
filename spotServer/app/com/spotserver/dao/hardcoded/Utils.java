package com.spotserver.dao.hardcoded;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.base.Strings;
import com.spotserver.model.Location;
import com.spotserver.model.SpotException;
import com.spotserver.model.spotservice.SpotListRequest;
import com.spotserver.model.spotservice.SpotReportRequest;
import com.spotserver.service.SpotBeans;

import controllers.SpotServiceRest;

public class Utils {

	private final static ObjectMapper jsonMaper = new ObjectMapper();

	
	public static Location parseAndValidateLocation(String latitude, String longitude) throws SpotException {

		if (Strings.isNullOrEmpty(latitude) || Strings.isNullOrEmpty(longitude)) {
			throw new SpotException("invalid latitude or longitude");
		}

		try {
			return new Location(Double.parseDouble(latitude), Double.parseDouble(longitude));
		} catch (NumberFormatException exc) {
			throw new SpotException("invalid latitude or longitude");
		}

	}

	public static SpotListRequest parseAndValidateSpotServiceRequest(String latitude, String longitude, String range) throws SpotException {
		if (Strings.isNullOrEmpty(range)) {
			throw new SpotException("invalid range");
		}

		try {
			return new SpotListRequest(parseAndValidateLocation(latitude, longitude), Double.parseDouble(range));
		} catch (NumberFormatException exc) {
			throw new SpotException("invalid range");
		}
	}
	
	
	public static SpotReportRequest parseAndValidateSpotReportRequest(String jsonRequest) throws SpotException {

		SpotReportRequest report = null;
		try {
			report = jsonMaper.readValue(jsonRequest, SpotReportRequest.class);
		} catch (Exception e) {
			throw new SpotException("Could not parse JSON report");
		}

		if (report == null) {
			throw new SpotException("no request found in POST body");
		} else if (report.getSpotLocation() == null || report.getSpotLocation().getLatitude() == null || report.getSpotLocation().getLatitude() == null) {
			throw new SpotException("missing or invalid location in request");
		} else {
			return report;
		}
		

	}

}
