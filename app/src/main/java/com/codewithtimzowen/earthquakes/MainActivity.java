package com.codewithtimzowen.earthquakes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //call the Query Utils to map the earthquakes
        ArrayList<EarthQuakes> earthquakes = QueryUtils.extractEarthquakes();

        //use arrayAdapter to bind the list to the list in the layout and pass in simple android layout for the display
        EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(this,earthquakes);

        ListView lv = findViewById(R.id.list_view);

        lv.setAdapter(earthQuakeAdapter);
        
    }

    // TODO
    // implement the Do in background Async Task
}