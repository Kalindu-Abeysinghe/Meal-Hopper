package com.example.foodapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Interfaces.OnMenuItemClickListener;
import com.example.foodapp.Models.Restaurant;
import com.example.foodapp.R;

public class RestaurantListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView restaurantImageView;
    private TextView restaurantName, restaurantAddress;
    private OnMenuItemClickListener onMenuItemClickListener;

    public RestaurantListViewHolder(@NonNull View itemView, OnMenuItemClickListener onMenuItemClickListener) {
        super(itemView);
        restaurantImageView=itemView.findViewById(R.id.imageViewRestaurant);
        restaurantName=itemView.findViewById(R.id.textViewName);
        restaurantAddress=itemView.findViewById(R.id.resturantName);
        this.onMenuItemClickListener=onMenuItemClickListener;

        itemView.setOnClickListener(this);
    }

    public void bind(Restaurant restaurant){
            restaurantName.setText(restaurant.getName());
            restaurantAddress.setText(restaurant.getAddress());
            restaurantImageView.setImageResource(restaurant.getImageId());
    }

    @Override
    public void onClick(View view) {
        onMenuItemClickListener.onMenuItemClicked(view, getAdapterPosition());
    }
}
