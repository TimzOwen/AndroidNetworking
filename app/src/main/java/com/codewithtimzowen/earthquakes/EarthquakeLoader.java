package com.codewithtimzowen.earthquakes;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

// Laod list of earthquakes by using AsyncTask to perform network request
public class EarthquakeLoader extends AsyncTaskLoader<List<EarthQuakes>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<EarthQuakes> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<EarthQuakes> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }





}
