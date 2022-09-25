package com.example.foodapp.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.foodapp.Models.ItemAddedToCart;
import com.example.foodapp.Models.MenuItemModel;
import com.example.foodapp.Models.OrderHistoryItem;
import com.example.foodapp.Models.OrderHistoryMenuItem;
import com.example.foodapp.Models.Restaurant;
import com.example.foodapp.Database.FoodAppDBSchema.RestaurantTable;
import com.example.foodapp.Database.FoodAppDBSchema.MenuItemTable;
import com.example.foodapp.Database.FoodAppDBSchema.UserTable;
import com.example.foodapp.Database.FoodAppDBSchema.OrderHistoryTable;
import com.example.foodapp.Database.FoodAppDBSchema.OrderMenuItemTable;
import com.example.foodapp.Models.User;


public class FoodAppDBCursor extends CursorWrapper {
    public FoodAppDBCursor(Cursor cursor) {
        super(cursor);
    }

    public Restaurant getRestaurant(){
        int id=getInt(getColumnIndex(RestaurantTable.Cols.ID));
        String name=getString(getColumnIndex(RestaurantTable.Cols.NAME));
        int imageId=getInt(getColumnIndex(RestaurantTable.Cols.IMAGE_ID));
        String address=getString(getColumnIndex(RestaurantTable.Cols.ADDRESS));

        return new Restaurant(id, name,address,imageId);
    }

    public MenuItemModel getMenuItem(){
        String name=getString(getColumnIndex(MenuItemTable.Cols.NAME));
        int restaurantId=getInt(getColumnIndex(MenuItemTable.Cols.RESTAURANT_ID));
        int imageId=getInt(getColumnIndex(MenuItemTable.Cols.IMAGE_ID));
        double price=getDouble(getColumnIndex(MenuItemTable.Cols.PRICE));

        return new MenuItemModel(name, price, imageId, restaurantId);
    }

    public User getUser(){
        String firstName=getString(getColumnIndex(UserTable.Cols.FIRST_NAME));
        String lastName=getString(getColumnIndex(UserTable.Cols.LAST_NAME));
        String email=getString(getColumnIndex(UserTable.Cols.EMAIL));
        String password=getString(getColumnIndex(UserTable.Cols.PASSWORD));
        String address=getString(getColumnIndex(UserTable.Cols.ADDRESS));
        int telephone=getInt(getColumnIndex(UserTable.Cols.TELEPHONE));

        return new User(email, firstName,lastName,password,address,telephone);
    }

    public OrderHistoryItem getOrderHistoryItem(){
        int orderId=getInt(getColumnIndex(OrderHistoryTable.Cols.ID));
        String userEmail=getString(getColumnIndex(OrderHistoryTable.Cols.USER_EMAIL));
        String orderDate=getString(getColumnIndex(OrderHistoryTable.Cols.ORDER_DATE));
        String orderTime=getString(getColumnIndex(OrderHistoryTable.Cols.ORDER_TIME));
        double totalCost=getDouble(getColumnIndex(OrderHistoryTable.Cols.TOTAL_COST));

        return new OrderHistoryItem(orderId,totalCost,userEmail,orderDate,orderTime);
    }

    public OrderHistoryMenuItem getOrderMenuItem(){

        int id=getInt(getColumnIndex(OrderMenuItemTable.Cols.ORDER_ID));
        int imageId=getInt(getColumnIndex(OrderMenuItemTable.Cols.IMAGE_ID));
        int quantity=getInt(getColumnIndex(OrderMenuItemTable.Cols.QUANTITY));
        double cost=getDouble(getColumnIndex(OrderMenuItemTable.Cols.COST));
        String foodName=getString(getColumnIndex(OrderMenuItemTable.Cols.ITEM_NAME));
        String restaurant=getString(getColumnIndex(OrderMenuItemTable.Cols.RESTAURANT_NAME));

        return new OrderHistoryMenuItem(id,foodName,restaurant,cost,quantity);
    }
}
