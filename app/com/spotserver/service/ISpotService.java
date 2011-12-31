package com.spotserver.service;

import com.spotserver.model.SpotException;
import com.spotserver.model.Status;

public interface ISpotService {

	public Status reportNewSpot(double latitude, double longitude) throws SpotException;
	
}
