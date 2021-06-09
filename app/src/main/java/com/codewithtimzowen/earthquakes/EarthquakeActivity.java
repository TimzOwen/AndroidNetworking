package com.codewithtimzowen.earthquakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<EarthQuakes>> {

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    private EarthQuakeAdapter mAdapter;
    private static final String LOG_TAG = "MainActivity";

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Test: OnCreated Method Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyStateTextView = findViewById(R.id.empty_view);

        //find the list view ID
        ListView earthquakeListView = findViewById(R.id.list_view);

        earthquakeListView.setEmptyView(mEmptyStateTextView);

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

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        
    }
    @Override
    public Loader<List<EarthQuakes>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }
    @Override
    public void onLoadFinished(Loader<List<EarthQuakes>> loader, List<EarthQuakes> earthquakes) {

        //progress bar
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_earthquakes);

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<EarthQuakes>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

}