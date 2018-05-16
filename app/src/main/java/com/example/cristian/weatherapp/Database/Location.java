package com.example.cristian.weatherapp.Database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//The Entity creates a table within our database
@Entity
public class Location {


    //Creating a collection of strings for needed data that
    //will be inserted into the database
    public Location(String cityName, String countryID) {
        this.cityName = cityName;
        this.countryID = countryID;
    }

    //Autogenerating ID for entity.
    @PrimaryKey(autoGenerate = true)
    private int id;


    //Defining Column Names in the "database table"
    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "country_ID")
    private String countryID;




    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }
}
