package com.example.foodapp.Data;

import com.example.foodapp.Models.MenuItemModel;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuItemData {

    private static String[] firstName=new String[]{
            "Big", "Small", "Double", "Crispy", "Grilled", "Soft", "Tripple"
    };

    private static String[] secondName=new String[] {
            "Chicken", "Masala", "Pepporoni", "Veggie", "Cheese", "Beef", "Supreme", "All meat"
    };

    private static String[] thirdName=new String[] {
            "Rice", "Burger", "Taco", "Pizza", "Biriyani"
    };

    private static int[] pizzaImages=new int[]{
        R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4,
            R.drawable.pizza5,R.drawable.pizza
    };

    private static int[] burgerImages=new int[]{
        R.drawable.burger2, R.drawable.burger3, R.drawable.burger4,
            R.drawable.burger5
    };

    private static int[] riceImages=new int[] {
             R.drawable.rice2, R.drawable.rice3, R.drawable.rice4,
            R.drawable.rice5
    };

    private static int[] tacoImages=new int[]{
            R.drawable.taco1, R.drawable.taco3, R.drawable.taco4,
            R.drawable.taco5
    };

    private static int[] biriyaniImages=new int[]{
            R.drawable.biriyani1, R.drawable.biriyani2, R.drawable.biriyani3,
    };

    private static String getMenuItemName(){
        Random random=new Random();

        String string=firstName[random.nextInt(firstName.length)]
                +" "+secondName[random.nextInt(secondName.length)]
                +" "+thirdName[random.nextInt(thirdName.length)];

        return string;
    }

    private static double getMenuItemPrice(){
        Random random=new Random();
        return random.nextInt(1500);
    }

    private static int getFoodImageId(String foodType){

        int id= R.drawable.ic_launcher_background;
        Random random=new Random();

        if(foodType.contains("Rice"))
            id=riceImages[random.nextInt(riceImages.length)];
        else if(foodType.contains("Pizza"))
            id=pizzaImages[random.nextInt(pizzaImages.length)];
        else if(foodType.contains("Taco"))
            id=tacoImages[random.nextInt(tacoImages.length)];
        else if(foodType.contains("Burger"))
            id=burgerImages[random.nextInt(burgerImages.length)];
        else if(foodType.contains("Biriyani"))
            id=biriyaniImages[random.nextInt(biriyaniImages.length)];

        return id;
    }

    public static List<MenuItemModel> getMenuItemList(){

        ArrayList<MenuItemModel> menuItems=new ArrayList<MenuItemModel>();

        for(int i=0; i<RestaurantData.NUMBER_OF_RESTAURANTS; i++){
            String name=getMenuItemName();
            menuItems.add(new MenuItemModel(name, getMenuItemPrice(), getFoodImageId(name), i+1));
        }

        return menuItems;
    }
}
