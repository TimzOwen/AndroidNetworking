package com.codewithtimzowen.earthquakes;

public class EarthQuakes {

    private final double mMagnitude;
    private final String mLocation;
    private String mUrl;
    // Time for the earthquake
    private final long mTimeMilliseconds;

    public EarthQuakes(double magnitude, String location, long timeMilliseconds, String url){
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeMilliseconds = timeMilliseconds;
        this.mUrl = url;
    }

    public long getmTimeMilliseconds() {
        return mTimeMilliseconds;
    }

    public String getmLocation() {
        return mLocation;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getUrl(){return mUrl;}
}
