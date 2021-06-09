package com.codewithtimzowen.earthquakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the list view ID
        ListView lv = findViewById(R.id.list_view);


        //call the Query Utils to map the earthquakes
        ArrayList<EarthQuakes> earthquakes = QueryUtils.extractEarthquakes();

        //use arrayAdapter to bind the list to the list in the layout and pass in simple android layout for the display
        final EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(this,earthquakes);

        lv.setAdapter(earthQuakeAdapter);

        lv.setOnItemClickListener((adapterView, view, position, id) -> {

            //find the current earthquake when clicked
            EarthQuakes currentEarthquake = earthQuakeAdapter.getItem(position);

            // Convert String URL into URI object to pass to intent constructor
            Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

            //create an Intent to open the link
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

            //send the intent to launch new activity
            startActivity(websiteIntent);

        });
        
    }
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<EarthQuakes>> {

        @Override
        protected List<EarthQuakes> doInBackground(String... urls) {

        }

        @Override
        protected void onPostExecute(List<EarthQuakes> data) {

        }
    }
}