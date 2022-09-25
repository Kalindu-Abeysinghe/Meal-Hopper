package com.example.foodapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.Adapters.MenuItemListAdapter;
import com.example.foodapp.Database.FoodAppDBModel;
import com.example.foodapp.Interfaces.OnActionBarListener;
import com.example.foodapp.Interfaces.OnMenuItemClickListener;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Models.MenuItemModel;
import com.example.foodapp.R;

import java.util.List;


public class MenuItemList extends Fragment implements OnMenuItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnActionBarListener onActionBarListener;
    private int restaurantId;
    private List<MenuItemModel> menuItemList;

    public MenuItemList(int restaurantId) {
        // Required empty public constructor
        this.restaurantId=restaurantId;
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
        View view= inflater.inflate(R.layout.fragment_menu_item_list, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.menuRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false
        ));

        FoodAppDBModel foodAppDBModel=new FoodAppDBModel();
        foodAppDBModel.load(getContext());
        menuItemList= foodAppDBModel.getMenuItemListByRestaurant(restaurantId);

        MenuItemListAdapter menuItemListAdapter=new MenuItemListAdapter(
                menuItemList,
                this
        );
        recyclerView.setAdapter(menuItemListAdapter);

        if(onActionBarListener!=null)
        {
            onActionBarListener.onAddBackButton();
            onActionBarListener.onSetActionBarText("Restaurant "+String.valueOf(restaurantId));
            onActionBarListener.onEnableFloatingCartButton(true);
        }



        return view;
    }

    @Override
    public void onMenuItemClicked(View view, int position) {
        onActionBarListener.onReplaceFragment(new MenuItem(menuItemList.get(position)));
    }
}