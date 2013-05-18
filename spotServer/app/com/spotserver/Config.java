package com.spotserver;

public class Config {

	
	private final MongoParams localMongoConfig = new MongoParams("localhost", 27017, "spotdb");
	private final MongoParams cloudfoundryMongoConfig = new MongoParams("172.30.48.66", 25090, "db", "16684576-4c30-41b8-8771-cdda1e7c0a62", "6c64a3e4-e790-4bde-9dab-ae75f96cdbc6".toCharArray());
	
	
	//{\"mongodb-1.8\":[{\"name\":\"spotmongo\",\"label\":\"mongodb-1.8\",\"plan\":\"free\",\"tags\":[\"mongodb\",\"mongodb-1.8\",\"nosql\"],\"
	
	//credentials\":
	//{\"hostname\":\"172.30.48.66\",\"host\":\"172.30.48.66\",\
	//"port\":25090,\"
	//username\":\"16684576-4c30-41b8-8771-cdda1e7c0a62\",\"
	//password\":\"6c64a3e4-e790-4bde-9dab-ae75f96cdbc6\",\
	//"name\":\"adbaefe4-249b-4ad5-adbb-536213180aae\",\"db\":\"db\"}}]}"
	
	
	
	public MongoParams getMongoConfig() {
		//return localMongoConfig;
		return cloudfoundryMongoConfig;
	}
	
	
	
	
}
