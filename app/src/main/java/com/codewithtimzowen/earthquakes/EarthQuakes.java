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

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmMagnitude(String mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }
}
