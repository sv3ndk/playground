package com.spotserver;

public class Config {

	
	private final MongoParams localMongoConfig = new MongoParams("localhost", 27017, "spotdb");
	
	
	public MongoParams getMongoConfig() {
		return localMongoConfig;
	}
	
	
	
	
}
