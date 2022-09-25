package com.example.foodapp.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodapp.Models.ItemAddedToCart;
import com.example.foodapp.Models.OrderHistoryMenuItem;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryItemListAdapter extends ArrayAdapter<OrderHistoryMenuItem> {

    private TextView foodName, restaurantName, itemCount, itemPrice;

    public HistoryItemListAdapter(@NonNull Context context, @NonNull ArrayList<OrderHistoryMenuItem> objects) {

        super(context, R.layout.order_history_detail_cell,R.id.textViewNull2, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        OrderHistoryMenuItem orderHistoryMenuItem=getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.order_history_detail_cell,parent,false);
        }
        foodName=convertView.findViewById(R.id.food_name_orderDetail);
        restaurantName=convertView.findViewById(R.id.restaurant_name_order);
        itemCount=convertView.findViewById(R.id.count_order_items);
        itemPrice=convertView.findViewById(R.id.items_price);

        foodName.setText(orderHistoryMenuItem.getFoodName());
        restaurantName.setText("from Restaurant "+orderHistoryMenuItem.getRestaurantName());
//        restaurantName.setText("from "+String.valueOf(orderHistoryMenuItem.getOrderId()));
        itemCount.setText("X  "+String.valueOf(orderHistoryMenuItem.getQuantity()));
        itemPrice.setText("Rs. "+String.valueOf(orderHistoryMenuItem.getCost()));

        return super.getView(position, convertView, parent);
    }
}
