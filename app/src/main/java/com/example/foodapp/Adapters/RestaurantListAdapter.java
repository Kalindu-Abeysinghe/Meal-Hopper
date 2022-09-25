package com.example.foodapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Interfaces.OnMenuItemClickListener;
import com.example.foodapp.Models.Restaurant;
import com.example.foodapp.R;
import com.example.foodapp.ViewHolders.RestaurantListViewHolder;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListViewHolder> {


    private List<Restaurant> restaurants;
    private OnMenuItemClickListener onMenuItemClickListener;

    public RestaurantListAdapter(List<Restaurant> restaurants, OnMenuItemClickListener onMenuItemClickListener) {
        this.restaurants = restaurants;
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    @NonNull
    @Override
    public RestaurantListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.restaurant_list_cell, parent, false);

        RestaurantListViewHolder restaurantListViewHolder=new RestaurantListViewHolder(view, onMenuItemClickListener);

        return  restaurantListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListViewHolder viewHolder, int position) {
        viewHolder.bind(restaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
