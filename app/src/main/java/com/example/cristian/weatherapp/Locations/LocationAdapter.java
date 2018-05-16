package com.example.cristian.weatherapp.Locations;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cristian.weatherapp.Database.Location;
import com.example.cristian.weatherapp.R;
import java.util.ArrayList;
import java.util.List;

class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    List<Location> locations;

    public LocationAdapter(List<Location> locations){
        this.locations = locations;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cityName_row.setText(locations.get(position).getCityName());
        holder.countryID_row.setText(locations.get(position).getCountryID());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }


    //Setting the the rows into the viewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView cityName_row;
        public TextView countryID_row;
        public ViewHolder(View itemView) {
            super(itemView);
            cityName_row = itemView.findViewById(R.id.cityName_row);
            countryID_row = itemView.findViewById(R.id.countryID_row);
        }
    }

}
