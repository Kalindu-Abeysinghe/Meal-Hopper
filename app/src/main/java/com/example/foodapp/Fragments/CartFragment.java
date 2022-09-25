package com.example.foodapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.Adapters.CartAdapter;
import com.example.foodapp.Common.CheckLoginVM;
import com.example.foodapp.Data.Cart;
import com.example.foodapp.Database.FoodAppDBModel;
import com.example.foodapp.Interfaces.OnActionBarListener;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Models.ItemAddedToCart;
import com.example.foodapp.Models.OrderHistoryItem;
import com.example.foodapp.Models.OrderHistoryMenuItem;
import com.example.foodapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class CartFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private OnActionBarListener onActionBarListener;
    private Button checkoutButton;
    private FoodAppDBModel foodAppDBModel;
    private CheckLoginVM checkLoginVM;
    private String loggedInEmail;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity)
            onActionBarListener=(OnActionBarListener)context;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);

        foodAppDBModel=new FoodAppDBModel();
        foodAppDBModel.load(getContext());

        checkoutButton=view.findViewById(R.id.checkout_button);
        recyclerView=view.findViewById(R.id.cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false
        ));

        CartAdapter cartAdapter=new CartAdapter();
        recyclerView.setAdapter(cartAdapter);

        if(onActionBarListener!=null)
        {
            onActionBarListener.onAddBackButton();
            onActionBarListener.onSetActionBarText("My Cart");
            onActionBarListener.onEnableFloatingCartButton(false);
        }

        checkLoginVM=new ViewModelProvider(getActivity(),(ViewModelProvider.Factory)new ViewModelProvider.NewInstanceFactory()).get(CheckLoginVM.class);
        if(checkLoginVM.getIsLoggedIn().getValue()){
            loggedInEmail=checkLoginVM.getLoggedInUser().getValue().getEmail();
            Log.i("user email",loggedInEmail);
        }


        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    OrderHistoryItem orderHistoryItem=getBasket();
                    if(orderHistoryItem.getOrderedItems().size()==0){
                        Toast toast=Toast.makeText(getContext(), "No Items selected to checkout!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        foodAppDBModel.addOrderHistoryItem(getBasket());
                        Cart.emptyCart();
                        cartAdapter.notifyDataSetChanged();
                        Toast toast=Toast.makeText(getContext(), "Successfully placed the order", Toast.LENGTH_LONG);
                        toast.show();
                    }

                }
                catch (Exception e){
                    Toast toast=Toast.makeText(getContext(), "Unsuccessful"+e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return view;
    }

    public OrderHistoryItem getBasket(){
        ArrayList<ItemAddedToCart> basketItems=Cart.getItems();
        ArrayList<OrderHistoryMenuItem> menuItems=new ArrayList<OrderHistoryMenuItem>();

        int orderId=foodAppDBModel.getLastOrderId()+1;
        double totalCost=0;

        for(int i=0; i<basketItems.size(); i++){
            ItemAddedToCart basketItem=basketItems.get(i);
            String foodName=basketItem.getFoodName();
            String restaurant=basketItem.getRestaurantName();
            int quantity=basketItem.getQuantity();
            double price=basketItem.getItemPrice();
            totalCost+=basketItem.getTotalPrice()*basketItem.getQuantity();

            OrderHistoryMenuItem orderHistoryMenuItem=new OrderHistoryMenuItem(orderId,foodName,restaurant,price,quantity);
            menuItems.add(orderHistoryMenuItem);
            foodAppDBModel.addOrderHistoryMenuItem(orderHistoryMenuItem);
        }

        return new OrderHistoryItem(orderId,
                totalCost,
                loggedInEmail,
                getCurrentDate(),
                " ",
                menuItems);
    }

    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date=new Date();

        return dateFormat.format(date);
    }

}