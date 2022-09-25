package com.example.foodapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Interfaces.OnMenuItemClickListener;
import com.example.foodapp.Models.MenuItemModel;
import com.example.foodapp.R;

public class MenuItemListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView foodImageView;
    private TextView foodNameTextView;
    private TextView foodPriceTextView;
    private OnMenuItemClickListener onMenuItemClickListener;

    public MenuItemListViewHolder(@NonNull View itemView, OnMenuItemClickListener onMenuItemClickListener) {
        super(itemView);
        foodImageView=itemView.findViewById(R.id.imageViewFood);
        foodNameTextView=itemView.findViewById(R.id.textViewFoodName);
        foodPriceTextView=itemView.findViewById(R.id.textViewPrice);
        this.onMenuItemClickListener=onMenuItemClickListener;

        itemView.setOnClickListener(this);
    }

    public void bind(MenuItemModel menuItem){
        foodImageView.setImageResource(menuItem.getImageId());
        foodNameTextView.setText(menuItem.getName());
        foodPriceTextView.setText(String.valueOf(menuItem.getPrice()));
    }

    @Override
    public void onClick(View view) {
        onMenuItemClickListener.onMenuItemClicked(view, getAdapterPosition());
    }
}
