package com.spotserver.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.spotserver.model.Location;
import com.spotserver.model.SpotRequest;

public class DummyClient {

	private static final ObjectMapper jsonMapper = new ObjectMapper();

	public static final String SPOTS_SERVICE_URL = "http://localhost:9000/spots";

	/**
	 * @param args
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		SpotRequest request = new SpotRequest(new Location(45d, 30d), 10);
		String requestStr = jsonMapper.writeValueAsString(request);
		System.out.println("sending this: " + requestStr);
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(SPOTS_SERVICE_URL + "?lat=55.5&longit=45&range=10.5");

		HttpResponse response = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}		
		

	}
	
	
	
	
	
}
