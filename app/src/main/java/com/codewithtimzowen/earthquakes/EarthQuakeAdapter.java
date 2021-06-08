package com.codewithtimzowen.earthquakes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuakes> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthQuakeAdapter(Activity context, ArrayList<EarthQuakes> earthQuakes) {

        super(context, 0,earthQuakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //get the current earthquake;
        EarthQuakes currentEarthquake = getItem(position);

        //create a view to inflate the desired layout on main acctivity
        View listItemView = convertView;

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_layout,parent,false);
        }
        TextView tvMag = listItemView.findViewById(R.id.tvMag);
        //format the magnitude to show the String 1.dp
        String formattedMagnitude = formatMagnitude(currentEarthquake.getmMagnitude());
        //set the textview to display
        tvMag.setText(formattedMagnitude);

        String originalLocation = currentEarthquake.getmLocation();
        String primaryLocation;
        String offsetLocation;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            offsetLocation = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            offsetLocation = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        //set the correct color magnitude
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        //get current Magnitude and pass the int color
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

        // set color on magnitude
        magnitudeCircle.setColor(magnitudeColor);


        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(offsetLocation);


        // Get to format the time and date to the correct formatting time:
        Date dateObject = new Date(currentEarthquake.getmTimeMilliseconds());

        // Find text_view with Time and update the textView
        TextView  tvDate = listItemView.findViewById(R.id.tvDate);

        //format the String to a date format
        String formattedDate = formatDate(dateObject);

        // display the text in the TextView
        tvDate.setText(formattedDate);

        // Implement the same as data but for time now
        TextView tvTime = listItemView.findViewById(R.id.tv_time);
        String formattedTime = formatTime(dateObject);
        tvTime.setText(formattedTime);


        return listItemView;
    }

    //create Method to format String
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd ,yyyy");
        return dateFormat.format(dateObject);
    }

    // create a method to format the Time
    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:a");
        return timeFormat.format(dateObject);
    }

    //create a formatted String to convert the desired text
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    //create a method to get the magnitude color
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
