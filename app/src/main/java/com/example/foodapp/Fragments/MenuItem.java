package com.example.foodapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Common.CartViewModel;
import com.example.foodapp.Data.Cart;
import com.example.foodapp.Interfaces.OnActionBarListener;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Models.ItemAddedToCart;
import com.example.foodapp.Models.MenuItemModel;
import com.example.foodapp.R;

public class MenuItem extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MenuItemModel menuItemReceived;
    private ImageView foodImageView;
    private TextView nameTextView, priceTextView, countTextView;
    private Button addToCartButton, addButton, removeButton;
    private OnActionBarListener onActionBarListener;


    public MenuItem(MenuItemModel menuItemModel) {
        menuItemReceived=menuItemModel;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity)
            onActionBarListener=(OnActionBarListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_menu_item, container, false);
        foodImageView=view.findViewById(R.id.imageViewFood);
        nameTextView=view.findViewById(R.id.textViewFoodName);
        priceTextView=view.findViewById(R.id.price);
        countTextView=view.findViewById(R.id.count);
        addButton=view.findViewById(R.id.addButton);
        removeButton=view.findViewById(R.id.removeButton);
        addToCartButton=view.findViewById(R.id.addToCart);

        foodImageView.setImageResource(menuItemReceived.getImageId());
        nameTextView.setText(menuItemReceived.getName());
        priceTextView.setText("Rs. "+menuItemReceived.getPrice());

        if(onActionBarListener!=null){
            onActionBarListener.onAddBackButton();
            onActionBarListener.onEnableFloatingCartButton(false);
        }

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=Integer.parseInt(countTextView.getText().toString());

                if(count>=1){
                    count++;
                    countTextView.setText(String.valueOf(count));

                    String text=("Add "+count+" for Rs. "+String.valueOf(count*menuItemReceived.getPrice()));
                    addToCartButton.setText(text);
                }
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=Integer.parseInt(countTextView.getText().toString());

                if(count>1){
                    count--;
                    countTextView.setText(String.valueOf(count));

                    String text="Add "+count+" for Rs. "+String.valueOf(count*menuItemReceived.getPrice());
                    addToCartButton.setText(text);

                }
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart.addItemToCart(menuItemReceived,
                        Integer.parseInt(String.valueOf(countTextView.getText())),
                        menuItemReceived.getRestaurantId());

                Toast.makeText(getContext(), "Successfully Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}