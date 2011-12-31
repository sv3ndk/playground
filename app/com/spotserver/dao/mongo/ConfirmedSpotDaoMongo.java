package com.spotserver.dao.mongo;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.spotserver.MongoParams;
import com.spotserver.dao.IConfirmedSpotDao;
import com.spotserver.model.ConfirmedSpot;
import com.spotserver.model.Location;
import com.spotserver.model.SpotException;
import com.spotserver.service.SpotBeans;

/**
 * @author svend
 * 
 */
public class ConfirmedSpotDaoMongo implements IConfirmedSpotDao {

	private DB mongoSpotDb;

	private final String COLLECTION_NAME = "spots";

	public ConfirmedSpotDaoMongo() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spotserver.dao.IConfirmedSpotDao#searchSpotsNear(com.spotserver.model.Location, double)
	 */
	@Override
	public List<ConfirmedSpot> searchSpotsNear(Location location, double maxDistanceInKm) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spotserver.dao.IConfirmedSpotDao#addSpot(double, double, java.util.Date)
	 */
	@Override
	public void addSpot(double latitude, double longitude, Date discoveryDate, double confidenceLevel) throws SpotException {
		ConfirmedSpot spot = new ConfirmedSpot(new Location(latitude, longitude), discoveryDate, confidenceLevel);
		DBCollection coll = getMongoDb().getCollection(COLLECTION_NAME);
		coll.insert(spot.toMongoDbo());

	}

	// //////////////////////

	protected DB getMongoDb() throws SpotException {

		try {
			if (mongoSpotDb == null) {
				synchronized (this) {
					if (mongoSpotDb == null) {
						System.out.println("getting params, config  is " + SpotBeans.getConfig());
						MongoParams mongoParams = SpotBeans.getConfig().getMongoConfig();
						System.out.println("creating mongo instance");
						Mongo mongo;
						mongo = new Mongo(mongoParams.getHostname(), mongoParams.getPort());
						System.out.println("gettng collection");
						mongoSpotDb = mongo.getDB(mongoParams.getDbname());

					}

				}
			}
		} catch (Exception e) {
			throw new SpotException("could not connect to monog", e);
		}

		return mongoSpotDb;

	}

}
