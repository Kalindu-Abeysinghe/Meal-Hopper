package com.example.foodapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.foodapp.Adapters.OrderHistoryAdapter;
import com.example.foodapp.Adapters.OrderHistoryListAdapter;
import com.example.foodapp.Common.CheckLoginVM;
import com.example.foodapp.Database.FoodAppDBModel;
import com.example.foodapp.Interfaces.OnActionBarListener;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Models.OrderHistoryItem;
import com.example.foodapp.Models.OrderHistoryMenuItem;
import com.example.foodapp.R;

import java.util.ArrayList;

public class OrderHistory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CheckLoginVM checkLoginVM;
    private ListView listView;
    private FoodAppDBModel foodAppDBModel;
    private OnActionBarListener onActionBarListener;
    private RecyclerView recyclerView;

    public OrderHistory() {
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
            onActionBarListener=(OnActionBarListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_order_history, container, false);
        checkLoginVM=new ViewModelProvider(getActivity(), (ViewModelProvider.Factory)new ViewModelProvider.NewInstanceFactory()).get(CheckLoginVM.class);
//        if(checkLoginVM.getIsLoggedIn().getValue()==true){
//            listView=view.findViewById(R.id.order_history_list_view);
//            foodAppDBModel=new FoodAppDBModel();
//            foodAppDBModel.load(getContext());
//
//            ArrayList<OrderHistoryItem> orderHistoryItems= foodAppDBModel.getOrderItemsByUser(checkLoginVM.getLoggedInUser().getValue().getEmail());
//            OrderHistoryListAdapter orderHistoryListAdapter=new OrderHistoryListAdapter(getContext(), orderHistoryItems);
//            listView.setAdapter(orderHistoryListAdapter);
//        }

        if(checkLoginVM.getIsLoggedIn().getValue()==true){
            recyclerView=view.findViewById(R.id.order_history_recycler);
            foodAppDBModel=new FoodAppDBModel();
            foodAppDBModel.load(getContext());

            recyclerView.setLayoutManager(new LinearLayoutManager(
                    getActivity(),
                    LinearLayoutManager.VERTICAL,
                    false));
            ArrayList<OrderHistoryItem> orderHistoryItems= foodAppDBModel.getOrderItemsByUser(checkLoginVM.getLoggedInUser().getValue().getEmail());
//            ArrayList<OrderHistoryItem> orderHistoryItems= foodAppDBModel.getAllOrderHistoryItems();
            OrderHistoryAdapter orderHistoryAdapter=new OrderHistoryAdapter(orderHistoryItems);
            recyclerView.setAdapter(orderHistoryAdapter);
        }

        if(onActionBarListener!=null){
            onActionBarListener.onSetActionBarText("My Order History");
        }

        return view;
    }
}