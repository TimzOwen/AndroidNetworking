package com.codewithtimzowen.earthquakes;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


// Helper methods related to requesting and receiving earthquake data from USGS.

public final class QueryUtils {

  private final static String LOG_TAG = "";

    //private constructor
    private QueryUtils() {
    }

    /**
     * Return a list of {@link EarthQuakes} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<EarthQuakes> extractFeatureFromJson(String earthquakeJSON) {

        //check if JSON String is null or empty then return early
        if (TextUtils.isEmpty(earthquakeJSON)){
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<EarthQuakes> earthquakes = new ArrayList<>();

        try {

            // create a Json Object response of JSON String.
            JSONObject baseJsonObject = new JSONObject(earthquakeJSON);

            //Get the features array
            JSONArray earthquakeArray = baseJsonObject.getJSONArray("features");

            //Loop through all elements in the array
            for (int i = 0; i < earthquakeArray.length(); i ++){

                //Get JSON Object at the specified index
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);

                //Get the JSON Object with JSON properties
                JSONObject properties = currentEarthquake.getJSONObject("properties");

                // Find individual Strings
                double magnitude = properties.getDouble("mag");
                String location = properties.getString("place");
                //get time in a human readble format
                long time  = properties.getLong("time");
                //extract URL for Intent swtiching:
                String url = properties.getString("url");

                //create a new object from the three strings
                EarthQuakes earthQuakes = new EarthQuakes(magnitude,location,time,url);
                earthquakes.add(earthQuakes);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Query the USGS dataset and return a list of {@link EarthQuakes} objects.
     */
    public static List<EarthQuakes> fetchEarthquakeData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<EarthQuakes> earthquakes = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }

}