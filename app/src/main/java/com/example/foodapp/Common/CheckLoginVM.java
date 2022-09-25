package com.example.foodapp.Common;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodapp.Models.User;

public class CheckLoginVM extends ViewModel {

    private MutableLiveData<Boolean> isLoggedIn;
    private MutableLiveData<User> loggedInUser;

    public CheckLoginVM(){
        isLoggedIn=new MutableLiveData<Boolean>();
        isLoggedIn.setValue(false);

        loggedInUser=new MutableLiveData<User>();
        loggedInUser.setValue(null);
    }

    public MutableLiveData<Boolean> getIsLoggedIn(){
        return this.isLoggedIn;
    }

    public void setIsLoggedIn(boolean state){
        this.isLoggedIn.setValue(state);
    }


    public MutableLiveData<User> getLoggedInUser(){
        return this.loggedInUser;
    }

    public void setLoggedInUser(User user){
        this.loggedInUser.setValue(user);
    }
}
