package com.example.foodapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Database.FoodAppDBModel;
import com.example.foodapp.Models.OrderHistoryItem;
import com.example.foodapp.Models.OrderHistoryMenuItem;
import com.example.foodapp.R;
import com.example.foodapp.ViewHolders.OrderHistoryViewHolder;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryViewHolder> {

    private ArrayList<OrderHistoryItem> orderHistoryItems;
    private FoodAppDBModel foodAppDBModel;

    public OrderHistoryAdapter(ArrayList<OrderHistoryItem> orderHistoryItems){
        this.orderHistoryItems=orderHistoryItems;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        foodAppDBModel=new FoodAppDBModel();
        foodAppDBModel.load(parent.getContext());

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.order_history_item_cell, parent, false);

        OrderHistoryViewHolder orderHistoryViewHolder=new OrderHistoryViewHolder(view);

        return orderHistoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {

        OrderHistoryItem itemToBind=orderHistoryItems.get(position);
        ArrayList<OrderHistoryMenuItem> menuItems=foodAppDBModel.getOrderMenuItemsById(itemToBind.getId());
        itemToBind.setOrderedItems(menuItems);
        holder.bind(itemToBind);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderHistoryItem orderHistoryItem=orderHistoryItems.get(holder.getAdapterPosition());
                orderHistoryItem.setIsExpanded(!orderHistoryItem.getIsExpanded());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderHistoryItems.size();
    }
}
