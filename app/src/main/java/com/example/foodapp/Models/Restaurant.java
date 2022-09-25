package com.example.foodapp.Models;

public class Restaurant {

    private int id;

    private String name;

    private String address;

    private int imageId;

    public Restaurant(int id, String name, String address, int imageId) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
