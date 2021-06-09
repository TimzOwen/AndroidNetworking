package com.codewithtimzowen.earthquakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    private EarthQuakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the list view ID
        ListView earthquakeListView = findViewById(R.id.list_view);

        //use arrayAdapter to bind the list to the list in the layout and pass in simple android layout for the display
        mAdapter = new EarthQuakeAdapter(this,new ArrayList<EarthQuakes>());

        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener((adapterView, view, position, id) -> {

            //find the current earthquake when clicked
            EarthQuakes currentEarthquake = mAdapter.getItem(position);

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
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }

        }
    }
}