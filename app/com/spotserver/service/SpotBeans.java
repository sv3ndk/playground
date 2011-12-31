package com.spotserver.service;

import com.spotserver.dao.IConfirmedSpotDao;
import com.spotserver.dao.hardcoded.HardcodedConfirmedSpotDat;

/**
 * Quick and dirty dependency injection framework
 * 
 * @author svend
 *
 */
public class SpotBeans {
	
	
	private static final IConfirmedSpotDao confirmedSpotDao = new HardcodedConfirmedSpotDat(); 
	private static final ISpotService spotService = new SpotService(); 
	
	
	public static IConfirmedSpotDao getConfirmedSpotDao() {
		return confirmedSpotDao;
	}


	public static ISpotService getSpotService() {
		return spotService;
	}

	

}
