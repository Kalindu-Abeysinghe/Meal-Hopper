package com.example.foodapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.Adapters.RestaurantListAdapter;
import com.example.foodapp.Adapters.TopPicksAdapter;
import com.example.foodapp.Data.TopPicksData;
import com.example.foodapp.Database.FoodAppDBModel;
import com.example.foodapp.Interfaces.OnActionBarListener;
import com.example.foodapp.Interfaces.OnMenuItemClickListener;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Models.MenuItemModel;
import com.example.foodapp.Models.Restaurant;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.Random;

public class RestaurantList extends Fragment implements OnMenuItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Restaurant> restaurantList;
    private OnActionBarListener onActionBarListener;

    public RestaurantList() {
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

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            onActionBarListener=(OnActionBarListener)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        FragmentManager fm=getParentFragmentManager();
        fm.beginTransaction()
                .replace(R.id.top_pick_fragment_container, new TopPickFragment())
                .commit();

        RecyclerView restaurantRecyclerView=view.findViewById(R.id.restaurantRecyclerView);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(
                    getActivity(),
                    LinearLayoutManager.VERTICAL,
                    false
        ));


        if(onActionBarListener!=null)
        {
            onActionBarListener.onSetActionBarText("Meal Hopper");
            onActionBarListener.onAddNavigationIcon();
            onActionBarListener.onEnableFloatingCartButton(true);
        }

        FoodAppDBModel foodAppDBModel=new FoodAppDBModel();
        foodAppDBModel.load(this.getContext());
        restaurantList=foodAppDBModel.getAllRestaurants();

        RestaurantListAdapter restaurantListAdapter=new RestaurantListAdapter(
                 restaurantList,
                this);
        restaurantRecyclerView.setAdapter(restaurantListAdapter);

        return view;
    }

    @Override
    public void onMenuItemClicked(View view, int position) {
        onActionBarListener.onReplaceFragment(new MenuItemList(restaurantList.get(position).getId()));
    }


}