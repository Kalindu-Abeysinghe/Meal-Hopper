package com.example.foodapp.Models;

public class User {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private String address;

    private int teleNumber;

    public User(String email, String firstName, String lastName, String password, String address, int teleNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.teleNumber = teleNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTeleNumber() {
        return teleNumber;
    }

    public void setTeleNumber(int teleNumber) {
        this.teleNumber = teleNumber;
    }
}
