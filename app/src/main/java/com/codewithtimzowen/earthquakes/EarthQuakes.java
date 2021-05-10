package com.codewithtimzowen.earthquakes;

public class EarthQuakes {

    private String mMagnitude;
    private String mLocation;
    private String mDate;

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
