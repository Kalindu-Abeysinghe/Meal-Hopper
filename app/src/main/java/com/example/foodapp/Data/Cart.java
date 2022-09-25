package com.example.foodapp.Data;

import com.example.foodapp.Models.ItemAddedToCart;
import com.example.foodapp.Models.MenuItemModel;

import java.util.ArrayList;

public class Cart {

    private static ArrayList<ItemAddedToCart> itemsInCart=new ArrayList<ItemAddedToCart>();

    public static void addItemToCart(MenuItemModel menuItem, int quantity, int restaurantId){

        ItemAddedToCart itemAddedToCart=new ItemAddedToCart(menuItem.getName(),
                quantity,
                menuItem.getPrice(),
                menuItem.getImageId(),
                String.valueOf(restaurantId));

        itemsInCart.add(itemAddedToCart);
    }

    public static void removeItem(int position){
        itemsInCart.remove(position);
    }

    public static int getItemCount(){
        return itemsInCart.size();
    }

    public static ArrayList<ItemAddedToCart> getItems(){
        return itemsInCart;
    }

    public static void emptyCart(){
        itemsInCart.clear();
    }
}
