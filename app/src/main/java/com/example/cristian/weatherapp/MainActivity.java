package com.example.cristian.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristian.weatherapp.Locations.inputLocation;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TextView cityField, detailsField, currentTemperatureField, wind_field, humidity_field, pressure_field, sunrise_field, sunset_field, weatherIcon, updatedField;
    Typeface weatherFont;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView) findViewById(R.id.city_field);
        updatedField = (TextView) findViewById(R.id.updated_field);
        detailsField = (TextView) findViewById(R.id.details_field);
        currentTemperatureField = (TextView) findViewById(R.id.current_temperature_field);
        wind_field = (TextView) findViewById(R.id.wind_field);
        humidity_field = (TextView) findViewById(R.id.humidity_field);
        pressure_field = (TextView) findViewById(R.id.pressure_field);
        sunrise_field = (TextView) findViewById(R.id.sunrise_field);
        sunset_field = (TextView) findViewById(R.id.sunset_field);
        weatherIcon = (TextView) findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);

    }



    @Override
    protected void onStart() {
        super.onStart();

        //Creating and setting the INPUT LOCATION
        Button location_button = (Button)findViewById(R.id.location_button);
        location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Location_button Pressed");
                startActivity(new Intent(MainActivity.this, inputLocation.class));
            }
        });

        //Creating and setting the GET CURRENT WEATHER
        Button gps_button = (Button) findViewById(R.id.gps_button);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET}, 123);
        gps_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                GPS use_gps = new GPS(getApplicationContext());
                Location location = use_gps.getLocation();
                if (location != null) {

                    RelativeLayout intro_text = findViewById(R.id.intro_text);
                    intro_text.setVisibility(View.INVISIBLE);


                    //Creating "doubles" with the values of the coordinates
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    Toast.makeText(getApplicationContext(), "Your coordinates are: "+"\n"+"Latitude: "+lat+"\n"+"Longitude: "+lon, Toast.LENGTH_SHORT).show();

                    //Converting the "double" value of the coordinates into strings, so we can
                    //use them for executing the AsyncTask
                    String Latitude = String.valueOf(lat);
                    String Longitude = String.valueOf(lon);


                    //Calling the placeIdTask function from the "Function" java lass
                    Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
                        public void processFinish(String weather_city, String weather_description, String weather_temperature, String wind_speed, String weather_humidity, String weather_pressure, String weather_sunrise, String weather_sunset, String weather_updatedOn, String weather_iconText, String sun_rise) {

                            cityField.setText(weather_city);
                            updatedField.setText(weather_updatedOn);
                            detailsField.setText(weather_description);
                            currentTemperatureField.setText(weather_temperature);
                            wind_field.setText("Wind Speed: " + wind_speed);
                            humidity_field.setText("Humidity: " + weather_humidity);
                            pressure_field.setText("Pressure: " + weather_pressure);
                            sunrise_field.setText("Sunrise: " + weather_sunrise);
                            sunset_field.setText("Sunset: " + weather_sunset);
                            weatherIcon.setText(Html.fromHtml(weather_iconText));


                        }
                    });
                    asyncTask.execute(Latitude, Longitude);

                }
                Log.d(TAG, "GetWeather Button Pressed");
            }
        });

    }
}