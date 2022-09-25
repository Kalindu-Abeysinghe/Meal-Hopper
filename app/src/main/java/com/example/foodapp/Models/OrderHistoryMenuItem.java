package com.example.foodapp.Models;

public class OrderHistoryMenuItem {

    private int orderId;
    private String foodName;
    private String restaurantName;
    private double cost;
    private int quantity;

    public OrderHistoryMenuItem(int orderId, String foodName, String restaurantName, double cost, int quantity) {
        this.orderId = orderId;
        this.foodName = foodName;
        this.restaurantName = restaurantName;
        this.cost = cost;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
