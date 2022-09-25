package com.example.foodapp.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Common.CartViewModel;
import com.example.foodapp.Common.CheckLoginVM;
import com.example.foodapp.Data.Cart;
import com.example.foodapp.Interfaces.OnMenuItemClickListener;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Models.ItemAddedToCart;
import com.example.foodapp.R;
import com.example.foodapp.ViewHolders.CartViewHolder;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private CartViewModel cartViewModel;

    public CartAdapter(){
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.cart_item_cell,parent,false);
        CartViewHolder cartViewHolder=new CartViewHolder(view);


        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int position) {
        cartViewHolder.bind(Cart.getItems().get(position));

        double itemPrice=Cart.getItems().get(position).getItemPrice();
        Button reduceButton=cartViewHolder.itemView.findViewById(R.id.reduce_button);
        Button increaseButton=cartViewHolder.itemView.findViewById(R.id.increase_button);
        Button deleteButton=cartViewHolder.itemView.findViewById(R.id.delete_button);
        TextView quantity=cartViewHolder.itemView.findViewById(R.id.quantity_view);
        TextView totalPrice=cartViewHolder.itemView.findViewById(R.id.total_price);


        reduceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=Integer.parseInt(String.valueOf(quantity.getText()));
                if(count>1){
                    count--;
                    quantity.setText(String.valueOf(count));
                    totalPrice.setText(String.valueOf(count*itemPrice));
                    Cart.getItems().get(cartViewHolder.getAdapterPosition()).setQuantity(count);
                }
            }
        });

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=Integer.parseInt(String.valueOf(quantity.getText()));
                count++;
                quantity.setText(String.valueOf(count));
                totalPrice.setText(String.valueOf(count*itemPrice));
                Cart.getItems().get(cartViewHolder.getAdapterPosition()).setQuantity(count);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart.removeItem(cartViewHolder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Cart.getItemCount();
    }
}
