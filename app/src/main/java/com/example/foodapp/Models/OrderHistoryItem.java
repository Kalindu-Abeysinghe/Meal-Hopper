package com.example.foodapp.Models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class OrderHistoryItem {

    private int id;
    private double totalCost;
    private String userEmail;
    private String orderDate;
    private String orderTime;
    private ArrayList<OrderHistoryMenuItem> orderedItems;
    private boolean isExpanded;

    public OrderHistoryItem(int id, double totalCost, String userEmail, String orderDate, String orderTime) {
        this.id = id;
        this.totalCost = totalCost;
        this.userEmail = userEmail;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderedItems = null;
        isExpanded=false;
    }

    public OrderHistoryItem(int id, double totalCost, String userEmail, String orderDate, String orderTime, ArrayList<OrderHistoryMenuItem> orderedItems) {
        this.id = id;
        this.totalCost = totalCost;
        this.userEmail = userEmail;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderedItems = orderedItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public ArrayList<OrderHistoryMenuItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(ArrayList<OrderHistoryMenuItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public boolean getIsExpanded() {
        return isExpanded;
    }

    public void setIsExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
