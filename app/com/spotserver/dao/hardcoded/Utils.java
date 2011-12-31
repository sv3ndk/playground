package com.spotserver.dao.hardcoded;

import com.google.common.base.Strings;
import com.spotserver.model.Location;
import com.spotserver.model.SpotException;
import com.spotserver.model.spotservice.SpotListRequest;

import controllers.SpotServiceRest;

public class Utils {

	public static Location parseLocation(String latitude, String longitude) throws SpotException {

		if (Strings.isNullOrEmpty(latitude) || Strings.isNullOrEmpty(longitude)) {
			throw new SpotException("invalid latitude or longitude");
		}

		try {
			return new Location(Double.parseDouble(latitude), Double.parseDouble(longitude));
		} catch (NumberFormatException exc) {
			throw new SpotException("invalid latitude or longitude");
		}

	}

	public static SpotListRequest parseSpotServiceRequest(String latitude, String longitude, String range) throws SpotException {
		if (Strings.isNullOrEmpty(range)) {
			throw new SpotException("invalid range");
		}

		try {
			return new SpotListRequest(parseLocation(latitude, longitude), Double.parseDouble(range));
		} catch (NumberFormatException exc) {
			throw new SpotException("invalid range");
		}

	}

}
