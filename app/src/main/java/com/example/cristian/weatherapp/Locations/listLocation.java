package com.example.cristian.weatherapp.Locations;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.cristian.weatherapp.Database.AppDatabase;
import com.example.cristian.weatherapp.Database.Location;
import com.example.cristian.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class listLocation extends AppCompatActivity {

    RecyclerView recyclerView_location;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_location);

        recyclerView_location = findViewById(R.id.recyclerView_Location);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();


        List<Location> locations = db.locationDao().getAllLocations();

        recyclerView_location.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LocationAdapter(locations);
        recyclerView_location.setAdapter(adapter);


    }

}
