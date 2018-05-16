package com.example.cristian.weatherapp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

//Data Access Object defines the database interaction.
@Dao
public interface LocationDao {

    //The Query will return the Entity. In our case the "Location class"
    @Query("SELECT * FROM location")
    List<Location> getAllLocations();

    //"@Insert" will insert its parameters into the database.
    //In our case the Strings from Location
    @Insert
    void insertAll(Location... locations);
}
