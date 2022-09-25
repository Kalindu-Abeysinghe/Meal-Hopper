package com.example.foodapp.Common;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodapp.Models.ItemAddedToCart;

import java.util.ArrayList;

public class CartViewModel extends ViewModel {

    private MutableLiveData<ItemAddedToCart> item;
    private ArrayList<ItemAddedToCart> cartItems;
    private MutableLiveData<ArrayList<ItemAddedToCart>> liveCartItems;

    public CartViewModel(){
        item=new MutableLiveData<ItemAddedToCart>();
        cartItems=new ArrayList<ItemAddedToCart>();
        liveCartItems=new MutableLiveData<ArrayList<ItemAddedToCart>>();

        item.setValue(null);
        liveCartItems.setValue(null);
    }

    public void addItemToCart(ItemAddedToCart itemAddedToCart){
        this.item.setValue(itemAddedToCart);
        cartItems.add(item.getValue());
        liveCartItems.setValue(cartItems);
    }

    public void removeItemFromCart(int itemIndex){
        cartItems.remove(itemIndex);
        liveCartItems.setValue(cartItems);
    }

    public MutableLiveData<ArrayList<ItemAddedToCart>> getLiveCartItems(){
        return this.liveCartItems;
    }
}
