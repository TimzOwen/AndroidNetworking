package com.codewithtimzowen.earthquakes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuakes> {

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
        tvMag.setText(currentEarthquake.getmMagnitude());

        TextView tvLocation = listItemView.findViewById(R.id.location_offset);
        tvLocation.setText(currentEarthquake.getmLocation());

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


}
