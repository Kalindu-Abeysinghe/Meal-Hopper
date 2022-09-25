package com.example.foodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.foodapp.Database.FoodAppDBSchema.RestaurantTable;
import com.example.foodapp.Database.FoodAppDBSchema.MenuItemTable;
import com.example.foodapp.Database.FoodAppDBSchema.UserTable;
import com.example.foodapp.Database.FoodAppDBSchema.OrderHistoryTable;
import com.example.foodapp.Database.FoodAppDBSchema.OrderMenuItemTable;

import com.example.foodapp.Models.ItemAddedToCart;
import com.example.foodapp.Models.MenuItemModel;
import com.example.foodapp.Models.OrderHistoryItem;
import com.example.foodapp.Models.OrderHistoryMenuItem;
import com.example.foodapp.Models.Restaurant;
import com.example.foodapp.Models.User;

import java.util.ArrayList;

public class FoodAppDBModel {

    private SQLiteDatabase database;

    public void load(Context context){
        this.database=new FoodAppDBHelper(
                context.getApplicationContext()).getWritableDatabase();
    }

    /*Methods to edit Restaurant Table*/
    public void addRestaurant(Restaurant restaurant){

        ContentValues cv=new ContentValues();
        cv.put(RestaurantTable.Cols.ID, restaurant.getId());
        cv.put(RestaurantTable.Cols.IMAGE_ID, restaurant.getImageId());
        cv.put(RestaurantTable.Cols.NAME, restaurant.getName());
        cv.put(RestaurantTable.Cols.ADDRESS, restaurant.getAddress());

        database.insert(RestaurantTable.NAME, null, cv);
    }

    public void deleteRestaurant(Restaurant restaurant){
        String[] whereValue={ String.valueOf(restaurant.getId()) };

        database.delete(RestaurantTable.NAME, RestaurantTable.Cols.ID +" = ?", whereValue);
    }

    public void updateRestaurant(Restaurant restaurant){

        ContentValues cv=new ContentValues();
        cv.put(RestaurantTable.Cols.ID, restaurant.getId());
        cv.put(RestaurantTable.Cols.IMAGE_ID, restaurant.getImageId());
        cv.put(RestaurantTable.Cols.NAME, restaurant.getName());
        cv.put(RestaurantTable.Cols.ADDRESS, restaurant.getAddress());

        String[] whereValue={ String.valueOf(restaurant.getId()) };
        database.update(RestaurantTable.NAME,cv, RestaurantTable.Cols.ID+ " = ?", whereValue);
    }

    public ArrayList<Restaurant> getAllRestaurants(){
        ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
        Cursor cursor=database.query(RestaurantTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        FoodAppDBCursor foodAppCursor=new FoodAppDBCursor(cursor);

        try {
            foodAppCursor.moveToFirst();
            while(!foodAppCursor.isAfterLast()){
                restaurants.add(foodAppCursor.getRestaurant());
                foodAppCursor.moveToNext();
            }
        }
        finally {
            foodAppCursor.close();
        }

        return restaurants;
    }

    public void addMenuItem(MenuItemModel menuItemModel){

        ContentValues cv=new ContentValues();
        cv.put(MenuItemTable.Cols.RESTAURANT_ID, menuItemModel.getRestaurantId());
        cv.put(MenuItemTable.Cols.NAME, menuItemModel.getName());
        cv.put(MenuItemTable.Cols.IMAGE_ID, menuItemModel.getImageId());
        cv.put(MenuItemTable.Cols.PRICE, menuItemModel.getPrice());

        database.insert(MenuItemTable.NAME, null, cv);
    }

    public void deleteMenuItem(MenuItemModel menuItemModel){
        String[] whereValue={menuItemModel.getName()};

        database.delete(MenuItemTable.NAME, MenuItemTable.Cols.NAME+" = ?", whereValue);
    }

    public void updateMenuItem(MenuItemModel menuItemModel){

        ContentValues cv=new ContentValues();
        cv.put(MenuItemTable.Cols.RESTAURANT_ID, menuItemModel.getRestaurantId());
        cv.put(MenuItemTable.Cols.NAME, menuItemModel.getName());
        cv.put(MenuItemTable.Cols.IMAGE_ID, menuItemModel.getImageId());
        cv.put(MenuItemTable.Cols.PRICE, menuItemModel.getPrice());

        String[] whereValue={menuItemModel.getName()};
        database.update(MenuItemTable.NAME,cv, MenuItemTable.Cols.NAME+" = ?", whereValue);
    }

    public ArrayList<MenuItemModel> getAllMenuItems(){
        ArrayList<MenuItemModel> menuItems=new ArrayList<MenuItemModel>();
        Cursor cursor=database.query(MenuItemTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        FoodAppDBCursor foodAppCursor=new FoodAppDBCursor(cursor);

        try {
            foodAppCursor.moveToFirst();
            while(!foodAppCursor.isAfterLast()){
                menuItems.add(foodAppCursor.getMenuItem());
                foodAppCursor.moveToNext();
            }
        }
        finally {
            foodAppCursor.close();
        }

        return menuItems;
    }

    public ArrayList<MenuItemModel> getMenuItemListByRestaurant(int restaurantId){
        ArrayList<MenuItemModel> menuItemList=getAllMenuItems();
        ArrayList<MenuItemModel> menuItemsToReturn=new ArrayList<MenuItemModel>();

        for(int i=0;i<menuItemList.size();i++){

            MenuItemModel menuItemModel=menuItemList.get(i);
            if(menuItemModel.getRestaurantId()==restaurantId)
                menuItemsToReturn.add(menuItemModel);
        }
        return menuItemsToReturn;
    }

    public void addUser(User user){

        ContentValues cv=new ContentValues();
        cv.put(UserTable.Cols.FIRST_NAME, user.getFirstName());
        cv.put(UserTable.Cols.LAST_NAME, user.getLastName());
        cv.put(UserTable.Cols.EMAIL, user.getEmail());
        cv.put(UserTable.Cols.PASSWORD, user.getPassword());
        cv.put(UserTable.Cols.ADDRESS, user.getAddress());
        cv.put(UserTable.Cols.TELEPHONE, user.getTeleNumber());

        database.insert(UserTable.NAME, null,cv);
    }

    public ArrayList<User> getAllUsers(){

        ArrayList<User> usersList=new ArrayList<User>();
        Cursor cursor=database.query(UserTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        FoodAppDBCursor foodAppDBCursor=new FoodAppDBCursor(cursor);
        foodAppDBCursor.moveToFirst();

        try {
            while (!foodAppDBCursor.isAfterLast()){
                usersList.add(foodAppDBCursor.getUser());
                foodAppDBCursor.moveToNext();
            }
        }
        finally {
            foodAppDBCursor.close();
        }

        return usersList;
    }

    public User getUserByEmail(String email){

        ArrayList<User> userArrayList=getAllUsers();
        User user=null;

        for(int i=0; i<userArrayList.size(); i++){
            if(userArrayList.get(i).getEmail().equals(email))
                user=userArrayList.get(i);
        }
        return user;
    }

    public boolean isUserValid(String email, String password){

        boolean returnVal=false;
        ArrayList<User> userArrayList=getAllUsers();

        for(int i=0; i<userArrayList.size();i++){
            User tempUser=userArrayList.get(i);
            if(tempUser.getEmail().equals(email) && tempUser.getPassword().equals(password))
                returnVal=true;
        }

        return returnVal;
    }


    public void addOrderHistoryItem(OrderHistoryItem orderHistoryItem){

        //Adding items to OrderHistoryTable
        ContentValues cv=new ContentValues();
        cv.put(OrderHistoryTable.Cols.ID, orderHistoryItem.getId());
        Log.i("Insert_email_table", orderHistoryItem.getOrderDate());
        cv.put(OrderHistoryTable.Cols.USER_EMAIL, orderHistoryItem.getUserEmail());
        Log.i("Insert_email_table", orderHistoryItem.getUserEmail());
        cv.put(OrderHistoryTable.Cols.TOTAL_COST, orderHistoryItem.getTotalCost());
        cv.put(OrderHistoryTable.Cols.ORDER_DATE,orderHistoryItem.getOrderDate());
        cv.put(OrderHistoryTable.Cols.ORDER_TIME,orderHistoryItem.getOrderTime());

        database.insert(OrderHistoryTable.NAME,null,cv);

        //Adding menuItems of this order to OrderMenuITemTable
       if(orderHistoryItem.getOrderedItems()!=null){
           ArrayList<OrderHistoryMenuItem> menuItems=orderHistoryItem.getOrderedItems();
           for(int i=0; i<menuItems.size();i++){
               OrderHistoryMenuItem menuItem=menuItems.get(i);
               ContentValues contentV=new ContentValues();
               cv.put(OrderMenuItemTable.Cols.ORDER_ID, menuItem.getOrderId());
               cv.put(OrderMenuItemTable.Cols.ITEM_NAME, menuItem.getFoodName());
               cv.put(OrderMenuItemTable.Cols.RESTAURANT_NAME,menuItem.getRestaurantName());
               cv.put(OrderMenuItemTable.Cols.QUANTITY, menuItem.getQuantity());
               cv.put(OrderMenuItemTable.Cols.COST, menuItem.getCost());

               database.insert(OrderMenuItemTable.NAME, null, contentV);
           }
       }
    }

    public ArrayList<OrderHistoryItem> getAllOrderHistoryItems(){

        ArrayList<OrderHistoryItem> orderHistoryItems=new ArrayList<OrderHistoryItem>();
        Cursor cursor=database.query(OrderHistoryTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        FoodAppDBCursor foodAppDBCursor=new FoodAppDBCursor(cursor);

        try {
            foodAppDBCursor.moveToFirst();
            while (!foodAppDBCursor.isAfterLast()){
                orderHistoryItems.add(foodAppDBCursor.getOrderHistoryItem());
                foodAppDBCursor.moveToNext();
            }
        }
        finally {
            foodAppDBCursor.close();
        }

        return orderHistoryItems;
    }

    public boolean checkOrderIdExists(int id){
        ArrayList<OrderHistoryItem> orderHistoryItems=getAllOrderHistoryItems();
        boolean returnVal=false;

        for (int i=0;i<orderHistoryItems.size();i++){
            if(orderHistoryItems.get(i).getId()==id)
                returnVal=true;
        }

        return returnVal;
    }

    public int getLastOrderId(){
        int returnVal;
        ArrayList<OrderHistoryItem> orderHistoryItems=getAllOrderHistoryItems();

        if(orderHistoryItems.size()==0)
            returnVal=0;
        else
            returnVal=orderHistoryItems.get(orderHistoryItems.size()-1).getId();

        return returnVal;
    }


    public ArrayList<OrderHistoryItem> getOrderItemsByUser(String userEmail){

        ArrayList<OrderHistoryItem> orderHistoryItems=getAllOrderHistoryItems();
        ArrayList<OrderHistoryItem> itemsToReturn=new ArrayList<OrderHistoryItem>();

        for(int i=0;i<orderHistoryItems.size();i++){
            if(orderHistoryItems.get(i).getUserEmail().equals(userEmail))
                itemsToReturn.add(orderHistoryItems.get(i));
        }
        return itemsToReturn;
    }

    public void addOrderHistoryMenuItem(OrderHistoryMenuItem orderHistoryMenuItem){

        ContentValues cv=new ContentValues();

        cv.put(OrderMenuItemTable.Cols.ORDER_ID, orderHistoryMenuItem.getOrderId());
        cv.put(OrderMenuItemTable.Cols.COST, orderHistoryMenuItem.getCost());
        cv.put(OrderMenuItemTable.Cols.QUANTITY, orderHistoryMenuItem.getQuantity());
        cv.put(OrderMenuItemTable.Cols.ITEM_NAME, orderHistoryMenuItem.getFoodName());
        cv.put(OrderMenuItemTable.Cols.RESTAURANT_NAME, orderHistoryMenuItem.getRestaurantName());

        database.insert(OrderMenuItemTable.NAME,null,cv);
    }

    public ArrayList<OrderHistoryMenuItem> getAllOrderMenuItems(){

        ArrayList<OrderHistoryMenuItem> menuItemsOrdered=new ArrayList<OrderHistoryMenuItem>();
        Cursor cursor=database.query(FoodAppDBSchema.OrderMenuItemTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        FoodAppDBCursor foodAppDBCursor=new FoodAppDBCursor(cursor);

        try {
            foodAppDBCursor.moveToFirst();
            while (!foodAppDBCursor.isAfterLast()){
                menuItemsOrdered.add(foodAppDBCursor.getOrderMenuItem());
                foodAppDBCursor.moveToNext();
            }
        }
        finally {
            foodAppDBCursor.close();
        }

        return menuItemsOrdered;
    }

    public ArrayList<OrderHistoryMenuItem> getOrderMenuItemsById(int id) {

        ArrayList<OrderHistoryMenuItem> orderItems=getAllOrderMenuItems();
        ArrayList<OrderHistoryMenuItem> itemsToReturn=new ArrayList<OrderHistoryMenuItem>();

        for (int i=0;i<orderItems.size();i++){
            OrderHistoryMenuItem item=orderItems.get(i);
//            Log.i("order_id",item.getOrderId()+"  given ID: "+id);
            if(item.getOrderId()==id)
                itemsToReturn.add(item);
        }
        return itemsToReturn;
    }


}
