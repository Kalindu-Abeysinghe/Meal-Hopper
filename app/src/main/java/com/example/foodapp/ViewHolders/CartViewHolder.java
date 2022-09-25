package com.example.foodapp.ViewHolders;

import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapters.CartAdapter;
import com.example.foodapp.Interfaces.OnMenuItemClickListener;
import com.example.foodapp.Models.ItemAddedToCart;
import com.example.foodapp.R;

public class CartViewHolder extends RecyclerView.ViewHolder{

    private TextView foodName, restaurantName, quantity, totalPrice;
    private Button reduceButton, increaseButton, deleteButton;
    private ImageView foodImage;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        foodName=itemView.findViewById(R.id.food_name);
        restaurantName=itemView.findViewById(R.id.restaurant_name);
        quantity=itemView.findViewById(R.id.quantity_view);
        totalPrice=itemView.findViewById(R.id.total_price);
        reduceButton=itemView.findViewById(R.id.reduce_button);
        increaseButton=itemView.findViewById(R.id.increase_button);
        deleteButton=itemView.findViewById(R.id.delete_button);
        foodImage=itemView.findViewById(R.id.food_image);

    }

    public void bind(ItemAddedToCart itemAddedToCart){
        foodName.setText(itemAddedToCart.getFoodName());
        restaurantName.setText("Restaurant "+itemAddedToCart.getRestaurantName());
        quantity.setText(String.valueOf(itemAddedToCart.getQuantity()));
        totalPrice.setText(String.valueOf(itemAddedToCart.getTotalPrice()));
        foodImage.setImageResource(itemAddedToCart.getItemImageId());

//        reduceButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int count=Integer.parseInt(String.valueOf(quantity.getText()));
//                if(count>0){
//                    count--;
//                    foodName.setText(String.valueOf(count));
//                }
//            }
//        });
//
//        increaseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int count=Integer.parseInt(String.valueOf(quantity.getText()));
//                count++;
//                foodName.setText(String.valueOf(count));
//
//            }
//        });
//
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

}
