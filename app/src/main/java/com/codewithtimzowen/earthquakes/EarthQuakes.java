package com.codewithtimzowen.earthquakes;

public class EarthQuakes {

    private final String mMagnitude;
    private final String mLocation;
    private final String mDate;

    public EarthQuakes(String magnitude, String location, String date){
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mDate = date;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }
}
