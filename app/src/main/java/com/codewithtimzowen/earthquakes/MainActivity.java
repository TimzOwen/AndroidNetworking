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

        //create an ArrayList to be displayed on th User Interface
        ArrayList<EarthQuakes> earthquakes = new ArrayList<>();

        earthquakes.add(new EarthQuakes("5.3","Kenya","25-05-20"));
        earthquakes.add(new EarthQuakes("4.0","Uganda","4-5-12"));
        earthquakes.add(new EarthQuakes("3.3","Nigeria","25-05-20"));
        earthquakes.add(new EarthQuakes("2.0","South Africa","4-5-12"));
        earthquakes.add(new EarthQuakes("7.3","United States","25-05-20"));
        earthquakes.add(new EarthQuakes("4.0","Congo","4-5-12"));
        earthquakes.add(new EarthQuakes("8.3","Liberia","25-05-20"));
        earthquakes.add(new EarthQuakes("9.0","Somalia","4-5-12"));
        earthquakes.add(new EarthQuakes("1.3","Eritrea","25-05-20"));
        earthquakes.add(new EarthQuakes("3.0","Rwanda","4-5-12"));
        earthquakes.add(new EarthQuakes("5.3","Kenya","25-05-20"));
        earthquakes.add(new EarthQuakes("4.0","Uganda","4-5-12"));
        earthquakes.add(new EarthQuakes("3.3","Nigeria","25-05-20"));
        earthquakes.add(new EarthQuakes("2.0","South Africa","4-5-12"));
        earthquakes.add(new EarthQuakes("7.3","United States","25-05-20"));
        earthquakes.add(new EarthQuakes("4.0","Congo","4-5-12"));
        earthquakes.add(new EarthQuakes("8.3","Liberia","25-05-20"));
        earthquakes.add(new EarthQuakes("9.0","Somalia","4-5-12"));
        earthquakes.add(new EarthQuakes("1.3","Eritrea","25-05-20"));
        earthquakes.add(new EarthQuakes("3.0","Rwanda","4-5-12"));

        //use arrayAdapter to bind the list to the list in the layout and pass in simple android layout for the display
        EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(this,earthquakes);

        ListView lv = findViewById(R.id.list_view);

        lv.setAdapter(earthQuakeAdapter);
        
    }
}