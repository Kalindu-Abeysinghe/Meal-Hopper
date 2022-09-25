package com.example.foodapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Interfaces.OnMenuItemClickListener;
import com.example.foodapp.Models.MenuItemModel;
import com.example.foodapp.R;
import com.example.foodapp.ViewHolders.MenuItemListViewHolder;

import java.util.List;

public class MenuItemListAdapter extends RecyclerView.Adapter<MenuItemListViewHolder> {

    private List<MenuItemModel> menuItemList;
    private OnMenuItemClickListener onMenuItemClickListener;

    public MenuItemListAdapter(List<MenuItemModel> menuItemList, OnMenuItemClickListener onMenuItemClickListener) {
        this.menuItemList = menuItemList;
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    @NonNull
    @Override
    public MenuItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.menu_item_list_cell, parent, false);

        MenuItemListViewHolder menuItemListViewHolder=new MenuItemListViewHolder(view, onMenuItemClickListener);

        return menuItemListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemListViewHolder viewHolder, int position) {
        viewHolder.bind(menuItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }
}
