package com.codewithtimzowen.earthquakes;

public class EarthQuakes {

    private final double mMagnitude;
    private final String mLocation;
    // Time for the earthquake
    private long mTimeMilliseconds;

    public EarthQuakes(double magnitude, String location, long timeMilliseconds){
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeMilliseconds = timeMilliseconds;
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
}
