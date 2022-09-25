package com.example.foodapp.Models;

public class ItemAddedToCart {

    private String foodName;

    private String restaurantName;

    private int quantity;

    private double itemPrice;

    private double totalPrice;

    private int itemImageId;

    public ItemAddedToCart(String name, int quantity, double itemPrice, int itemImageId, String restaurantName) {
        this.foodName = name;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.totalPrice = quantity*itemPrice;
        this.itemImageId = itemImageId;
        this.restaurantName=restaurantName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemImageId() {
        return itemImageId;
    }

    public void setItemImageId(int itemImageId) {
        this.itemImageId = itemImageId;
    }
}
