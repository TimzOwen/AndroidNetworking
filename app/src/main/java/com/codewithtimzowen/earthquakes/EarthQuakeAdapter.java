package com.codewithtimzowen.earthquakes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

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

        TextView tvLocation = listItemView.findViewById(R.id.tvLocation);
        tvLocation.setText(currentEarthquake.getmLocation());

        TextView  tvDate = listItemView.findViewById(R.id.tvDate);
        tvDate.setText(currentEarthquake.getmDate());

        return listItemView;
    }
}
