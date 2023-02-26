package com.example.foodapp.ViewHolders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapters.HistoryItemListAdapter;
import com.example.foodapp.Models.OrderHistoryItem;
import com.example.foodapp.R;

public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {

    private TextView orderId, userEMail, dateTime, price;
    private LinearLayout expandableView;
    private ListView detailListView;

    public OrderHistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        orderId=itemView.findViewById(R.id.order_id);
        userEMail=itemView.findViewById(R.id.user_email);
        dateTime=itemView.findViewById(R.id.date_time);
        price=itemView.findViewById(R.id.price_text);
        expandableView=itemView.findViewById(R.id.order_detail_layout);
        detailListView=itemView.findViewById(R.id.list_view_order_items);

    }

    public void bind(OrderHistoryItem orderHistoryItem){

        orderId.setText("Order ID: "+String.valueOf(orderHistoryItem.getId()));
        userEMail.setText(orderHistoryItem.getUserEmail());
        dateTime.setText(orderHistoryItem.getOrderDate()+" "+orderHistoryItem.getOrderTime());
        price.setText(String.valueOf(orderHistoryItem.getTotalCost()));
        detailListView.setAdapter(new HistoryItemListAdapter(itemView.getContext(), orderHistoryItem.getOrderedItems()));

        boolean isExpanded=orderHistoryItem.getIsExpanded();
        expandableView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);


    }
}
