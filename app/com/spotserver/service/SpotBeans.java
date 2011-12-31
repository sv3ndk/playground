package com.spotserver.service;

import com.spotserver.Config;
import com.spotserver.dao.IConfirmedSpotDao;
import com.spotserver.dao.hardcoded.HardcodedConfirmedSpotDat;
import com.spotserver.dao.mongo.ConfirmedSpotDaoMongo;

/**
 * Quick and dirty dependency injection framework
 * 
 * @author svend
 *
 */
public class SpotBeans {
	
	
	private static final IConfirmedSpotDao hardCodedconfirmedSpotDao = new HardcodedConfirmedSpotDat();
	private static final IConfirmedSpotDao mongoconfirmedSpotDao = new ConfirmedSpotDaoMongo();
	private static final Config config = new Config();
	
	private static final ISpotService spotService = new SpotService(); 
	
	
	
	
	public static IConfirmedSpotDao getConfirmedSpotDao() {
		return mongoconfirmedSpotDao;
		//return hardCodedconfirmedSpotDao;
	}


	public static ISpotService getSpotService() {
		return spotService;
	}


	public static Config getConfig() {
		return config;
	}

	

}
