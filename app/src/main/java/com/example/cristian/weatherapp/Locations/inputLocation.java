package com.example.cristian.weatherapp.Locations;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cristian.weatherapp.Database.AppDatabase;
import com.example.cristian.weatherapp.Database.Location;
import com.example.cristian.weatherapp.MainActivity;
import com.example.cristian.weatherapp.R;

public class inputLocation extends AppCompatActivity {

    private static final String TAG = "inputLocation";

    EditText cityName;
    EditText countryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_location);

        cityName = findViewById(R.id.cityName_editText);
        countryID = findViewById(R.id.countryID_editText);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()//Not the best solution but we didn't figure out how to use the database properly in the background.
                .build();


        Button saveLocation_button = (Button)findViewById(R.id.saveLocation_button);
        saveLocation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "saveLocation_button Pressed");
                Location location = new Location(cityName.getText().toString(), countryID.getText().toString());
                db.locationDao().insertAll(location);
                startActivity(new Intent(inputLocation.this, listLocation.class));
            }
        });


        Button seeLocation_button = (Button)findViewById(R.id.seeLocation_button);
        seeLocation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "seeLocation_button Pressed");
                startActivity(new Intent(inputLocation.this, listLocation.class));
            }
        });

    }
}
