package localhost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Sample class that makes a simple request to Google Maps Directions
 * 
 * @author Jacob Chmielewski
 * @since 2016-09-06
 *
 */

public class RestRequestProject{

	/*
	 * The URL of the API we want to connect to
	 */
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	
	/*
	 * The character set to use when encoding URL parameters
	 */
	protected static String charset = "UTF-8";
			
	/*
	 * API key used for make requests to API	
	 */
	protected static String key = "AIzaSyDGP5PNMHqUms__GLT_Org_lRAPxe-qIx8";
	
	
	public static void main(String[] args) {
		
		try {
			
			//The origin or starting point for directions
			String origin = "Columbia MD";
			
			//The destination or end point for directions
			String destination = "Charlotte NC";
			
			//The return type of the response xml | json
			String returnType = "json";
			
			//The transportation mode
			String mode = "transit";
			
			//The travel mode used
			String travelMode = "bus";
			
			//avoid tolls
			String avoid = "tolls&ferries";
			
			//changes to danish directions
			String language = "da";
			
			//creates the url parameters as a string encoding them with the defined charset
			String queryString = String.format("origin=%s&destination=%s&mode=%s&travelMode=%s&avoid=%s&language=%s&key=%s",
					URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination, charset),
					URLEncoder.encode(mode, charset),
					URLEncoder.encode(travelMode, charset),
					URLEncoder.encode(avoid, charset),
					URLEncoder.encode(language, charset),
					URLEncoder.encode(key, charset)
			);		
			
			//creates a new URL out of the endpoint, returnType and queryString
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");
			
			//if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() !=200){
				throw new RuntimeException("Failed: HTTP error code : " + connection.getResponseCode());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer line by line until it returns null meaning there are no more lines
			while (br.readLine() != null){
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
		//close connection to API
		connection.disconnect();	
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		
		} catch (IOException e){
			
			e.printStackTrace();
			
		}
		
		
	}
	
	
	
	
} //ResetRequest class


