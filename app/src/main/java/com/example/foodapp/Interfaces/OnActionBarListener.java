package com.example.foodapp.Interfaces;

import android.content.Context;

import androidx.fragment.app.Fragment;

public interface OnActionBarListener {
    void onAddBackButton();
    void onSetActionBarText(String name);
    void onAddNavigationIcon();
    void onReplaceFragment(Fragment fragment);
    void onEnableFloatingCartButton(boolean visibility);
    void onPopBackStack();
}

