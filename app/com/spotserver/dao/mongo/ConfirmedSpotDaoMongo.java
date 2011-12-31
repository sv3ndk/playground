package com.spotserver.dao.mongo;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.spotserver.MongoParams;
import com.spotserver.dao.IConfirmedSpotDao;
import com.spotserver.model.ConfirmedSpot;
import com.spotserver.model.Location;
import com.spotserver.model.SpotException;
import com.spotserver.model.Status;
import com.spotserver.service.SpotBeans;

/**
 * @author svend
 * 
 */
public class ConfirmedSpotDaoMongo implements IConfirmedSpotDao {

	private DB mongoSpotDb;

	private final String COLLECTION_NAME = "spots";


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spotserver.dao.IConfirmedSpotDao#searchSpotsNear(com.spotserver.model.Location, double)
	 */
	@Override
	public List<ConfirmedSpot> searchSpotsNear(Location location, double maxDistanceInKm) throws SpotException {

		DBCollection coll = getMongoDb().getCollection(COLLECTION_NAME);
		BasicDBObject query = new BasicDBObject();
		BasicDBObject nearQuery = new BasicDBObject();
		BasicDBObject center = new BasicDBObject();
		center.put("latitude", location.getLatitude());
		center.put("longitude", location.getLongitude());
		
		System.out.println("max distance" + maxDistanceInKm);
		
		nearQuery.put("$near", center);
		nearQuery.put("$maxDistance", maxDistanceInKm);
		query.put("location", nearQuery);
		
		System.out.println("query " + query);

		List<ConfirmedSpot> response = new LinkedList<ConfirmedSpot>();
		DBCursor cur = coll.find(query);
		while (cur.hasNext()) {
			response.add(new ConfirmedSpot(cur.next()));
		}
		return response;
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
						MongoParams mongoParams = SpotBeans.getConfig().getMongoConfig();
						Mongo mongo = new Mongo(mongoParams.getHostname(), mongoParams.getPort());
						mongoSpotDb = mongo.getDB(mongoParams.getDbname());
					}

				}
			}
		} catch (Exception e) {
			throw new SpotException("could not connect to monog", e);
		}

		return mongoSpotDb;

	}

	@Override
	public Status ensureSpotIndex() {
		try {
			DBCollection coll = getMongoDb().getCollection(COLLECTION_NAME);
			BasicDBObject indexDbo = new BasicDBObject();
			indexDbo.put("location", "2d");
			coll.ensureIndex(indexDbo);
			Status status = new Status(true, "index created");
			return status;

		} catch (Exception e) {
			Status status = new Status();
			status.setOk(false);
			status.setMessage("could not create index " + e.getMessage());
			e.printStackTrace();
			return status;
		}
	}

}
