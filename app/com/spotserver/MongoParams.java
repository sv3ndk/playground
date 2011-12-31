package com.spotserver;

public class MongoParams {

	private String hostname;
	private int port;
	private String dbname;
	private String username;
	private char[] password;

	
	public MongoParams() {
		super();
	}
	
	public MongoParams(String hostname, int port, String dbname) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.dbname = dbname;
	}

	public MongoParams(String hostname, int port, String dbname, String username, char[] password) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.dbname = dbname;
		this.username = username;
		this.password = password;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}


}
