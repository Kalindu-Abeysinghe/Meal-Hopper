package com.example.foodapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodapp.Database.FoodAppDBModel;
import com.example.foodapp.Models.OrderHistoryItem;
import com.example.foodapp.Models.OrderHistoryMenuItem;
import com.example.foodapp.R;

import java.util.ArrayList;

public class OrderHistoryListAdapter extends ArrayAdapter<OrderHistoryItem> {

    private TextView orderId, orderUserEmail, totalPrice, orderDataTime;
    private ListView outerListView;
    private ListView orderDetailsListView;
    private LinearLayout orderDetailsLayout;

    public OrderHistoryListAdapter(@NonNull Context context, @NonNull ArrayList<OrderHistoryItem> objects) {
        super(context, R.layout.order_history_item_cell,R.id.textViewNull, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        OrderHistoryItem orderHistoryItem=getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.order_history_item_cell,parent,false);
        }

        orderId=convertView.findViewById(R.id.order_id);
        orderUserEmail=convertView.findViewById(R.id.user_email);
        totalPrice=convertView.findViewById(R.id.price_text);
        orderDataTime=convertView.findViewById(R.id.date_time);
        orderDetailsListView =convertView.findViewById(R.id.list_view_order_items);
        orderDetailsLayout=convertView.findViewById(R.id.order_detail_layout);

        orderId.setText("Order ID: "+String.valueOf(orderHistoryItem.getId()));
        orderUserEmail.setText(orderHistoryItem.getUserEmail());
        totalPrice.setText("Rs. "+String.valueOf(orderHistoryItem.getTotalCost()));
        orderDataTime.setText(orderHistoryItem.getOrderDate()+" "+orderHistoryItem.getOrderTime());

        FoodAppDBModel foodAppDBModel=new FoodAppDBModel();
        foodAppDBModel.load(getContext());

        ArrayList<OrderHistoryMenuItem> menuItemArrayList=foodAppDBModel.getOrderMenuItemsById(orderHistoryItem.getId());

        HistoryItemListAdapter historyItemListAdapter=new HistoryItemListAdapter(getContext(), menuItemArrayList);
        orderDetailsListView.setAdapter(historyItemListAdapter);

        totalPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderDetailsLayout.getVisibility()==View.GONE){
                    orderDetailsLayout.setVisibility(View.VISIBLE);
                }
                else
                    orderDetailsLayout.setVisibility(View.GONE);
            }
        });

        return super.getView(position, convertView, parent);
    }
}
