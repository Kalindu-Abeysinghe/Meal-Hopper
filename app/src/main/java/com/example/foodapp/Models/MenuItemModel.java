package com.example.foodapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItemModel implements Parcelable {

    private String name;

    private double price;

    private int imageId;

    private int restaurantId;

    public MenuItemModel(String name, double price, int imageId, int restaurantId) {
        this.name = name;
        this.price = price;
        this.imageId = imageId;
        this.restaurantId=restaurantId;
    }

    protected MenuItemModel(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        imageId = in.readInt();
        restaurantId=in.readInt();
    }

    public static final Creator<MenuItemModel> CREATOR = new Creator<MenuItemModel>() {
        @Override
        public MenuItemModel createFromParcel(Parcel in) {
            return new MenuItemModel(in);
        }

        @Override
        public MenuItemModel[] newArray(int size) {
            return new MenuItemModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeInt(imageId);
        parcel.writeInt(restaurantId);
    }
}
