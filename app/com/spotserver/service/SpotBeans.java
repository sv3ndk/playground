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
	
	
	private static IConfirmedSpotDao confirmedSpotDao = new HardcodedConfirmedSpotDat(); 
	
	
	public static IConfirmedSpotDao getConfirmedSpotDao() {
		return confirmedSpotDao;
	}
	

}
