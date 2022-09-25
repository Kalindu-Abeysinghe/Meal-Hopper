package com.example.foodapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;

import com.example.foodapp.Database.FoodAppDBSchema.RestaurantTable;
import com.example.foodapp.Database.FoodAppDBSchema.MenuItemTable;
import com.example.foodapp.Database.FoodAppDBSchema.UserTable;
import com.example.foodapp.Database.FoodAppDBSchema.OrderHistoryTable;
import com.example.foodapp.Database.FoodAppDBSchema.OrderMenuItemTable;
import com.example.foodapp.Models.User;

import androidx.annotation.Nullable;

public class FoodAppDBHelper extends SQLiteOpenHelper {

    private static final int VERSION=1;
    private static final String DATABASE_NAME="foodApp.db";

    public FoodAppDBHelper(@Nullable Context context) {
    super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ RestaurantTable.NAME+" ("+
                RestaurantTable.Cols.ID+ " INTEGER PRIMARY KEY, "+
                RestaurantTable.Cols.IMAGE_ID+ " INTEGER, "+
                RestaurantTable.Cols.NAME+ " TEXT, "+
                RestaurantTable.Cols.ADDRESS+" TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE "+ MenuItemTable.NAME+" ("+
                MenuItemTable.Cols.RESTAURANT_ID+" INTEGER, "+
                MenuItemTable.Cols.NAME+" TEXT, "+
                MenuItemTable.Cols.IMAGE_ID+" INTEGER, "+
                MenuItemTable.Cols.PRICE+" REAL,"+
                "FOREIGN KEY ("+MenuItemTable.Cols.RESTAURANT_ID+") REFERENCES "+
                RestaurantTable.NAME+"("+RestaurantTable.Cols.ID+"));");

        sqLiteDatabase.execSQL("CREATE TABLE "+UserTable.NAME+" ("+
                UserTable.Cols.FIRST_NAME+" TEXT, "+
                UserTable.Cols.LAST_NAME+" TEXT, "+
                UserTable.Cols.EMAIL+" TEXT PRIMARY KEY, "+
                UserTable.Cols.ADDRESS+" TEXT, "+
                UserTable.Cols.PASSWORD+" TEXT, "+
                UserTable.Cols.TELEPHONE+" INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE "+OrderHistoryTable.NAME+" ("+
                OrderHistoryTable.Cols.ID+" INTEGER PRIMARY KEY, "+
                OrderHistoryTable.Cols.USER_EMAIL+" TEXT, "+
                OrderHistoryTable.Cols.TOTAL_COST+" REAL, "+
                OrderHistoryTable.Cols.ORDER_DATE+" TEXT, "+
                OrderHistoryTable.Cols.ORDER_TIME+" TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE "+OrderMenuItemTable.NAME+" ("+
                OrderMenuItemTable.Cols.ORDER_ID+" INTEGER, "+
                OrderMenuItemTable.Cols.ITEM_NAME+" TEXT, "+
                OrderMenuItemTable.Cols.QUANTITY+" INTEGER, "+
                OrderMenuItemTable.Cols.IMAGE_ID+" INTEGER, "+
                OrderMenuItemTable.Cols.RESTAURANT_NAME+" TEXT, "+
                OrderMenuItemTable.Cols.COST+" REAL, "+
                "FOREIGN KEY ("+OrderMenuItemTable.Cols.ORDER_ID+") REFERENCES "+
                OrderHistoryTable.NAME+"("+OrderHistoryTable.Cols.ID+"));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
