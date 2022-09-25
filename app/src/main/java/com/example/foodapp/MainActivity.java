package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.foodapp.Data.MenuItemData;
import com.example.foodapp.Data.RestaurantData;
import com.example.foodapp.Database.FoodAppDBModel;
import com.example.foodapp.Fragments.CartFragment;
import com.example.foodapp.Fragments.OrderHistory;
import com.example.foodapp.Fragments.RestaurantList;
import com.example.foodapp.Fragments.UserAccount;
import com.example.foodapp.Interfaces.OnActionBarListener;
import com.example.foodapp.Models.MenuItemModel;
import com.example.foodapp.Models.Restaurant;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnActionBarListener {

    MaterialToolbar materialToolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton floatingActionButton;
    FoodAppDBModel foodAppDBModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialToolbar=findViewById(R.id.topAppBar);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        floatingActionButton=findViewById(R.id.floating_action_button);

        //Placing the initial fragment as the Restaurant fragment
        replaceFragment(new RestaurantList());

        //Database
        foodAppDBModel=new FoodAppDBModel();
        foodAppDBModel.load(getApplicationContext());

        //Adding restaurants to database
        List<Restaurant> restaurantsToAdd= RestaurantData.getRestaurantList();
        for(int i=0; i<restaurantsToAdd.size();i++){
            foodAppDBModel.addRestaurant(restaurantsToAdd.get(i));
        }

        //Adding menuItems to database
        for(int i=0; i<restaurantsToAdd.size();i++){

            List<MenuItemModel> menuItemsToAdd= MenuItemData.getMenuItemList();

            for(int j=0; j<menuItemsToAdd.size();j++){
                foodAppDBModel.addMenuItem(menuItemsToAdd.get(j));
            }
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new CartFragment());
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack("frag")
                .commit();

        Log.i("BackStack count", String.valueOf(fragmentManager.getBackStackEntryCount()));
    }

    private void removeBackStackFragments(){

        FragmentManager fragmentManager=getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount()!=0){
            fragmentManager.popBackStackImmediate();
        }
    }

    @Override
    public void onAddBackButton() {

        materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getSupportFragmentManager();
                    fm.popBackStackImmediate();
            }
        });
    }

    @Override
    public void onSetActionBarText(String name) {
        materialToolbar.setTitle(name);
    }

    @Override
    public void onAddNavigationIcon() {
        materialToolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);

                if(id==R.id.home) replaceFragment(new RestaurantList());
                else if(id==R.id.userAccount) replaceFragment(new UserAccount());
                else if(id==R.id.orderHistory) replaceFragment(new OrderHistory());

                return true;
            }
        });
    }

    @Override
    public void onReplaceFragment(Fragment fragment) {
        replaceFragment(fragment);
    }

    @Override
    public void onEnableFloatingCartButton(boolean visibility) {
        if(visibility)
            floatingActionButton.setVisibility(View.VISIBLE);
        else
            floatingActionButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPopBackStack() {
        FragmentManager fm=getSupportFragmentManager();
        fm.popBackStackImmediate();
    }


}