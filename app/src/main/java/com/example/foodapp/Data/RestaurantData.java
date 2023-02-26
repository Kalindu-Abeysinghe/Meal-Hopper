package com.example.foodapp.Data;

import com.example.foodapp.Models.Restaurant;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RestaurantData {

    public static final int NUMBER_OF_RESTAURANTS=12;
    private static ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();;

    public static String[] streetNames=new String[]{
            "George", "William", "Church", "High","King","Short",
            "Elizabeth", "Victoria", "John", "Abatoir", "Yoga", "Zorro",
            "Kaizer", "Zepelin", "Abba", "Hill"
    };

    public static String[] townNames=new String[] {
            "Babinda", "Bacchus Marsh", "Badgingarra", "Bairnsdale",
            "Balaklava", "Armidale", "Arno Bay", "Arthur River", "Ashford",
            "Atherton", "Charlton", "Charters Towers", "Chewton", "Childers",
            "Chillagoe", "Falls Creek", "Falmouth", "Faraway Bay", "Faulconbridge",
            "Ferntree Gully",
    };

    public static String[] stateCodes=new String[]{
            "QLD", "TAS","SA", "WA", "NSW", "VIC","NT"
    };

    public static int[] restaurantImages=new int[]{
            R.drawable.restaurant_17, R.drawable.restaurant_16, R.drawable.restaurant_15,
            R.drawable.restaurant_14, R.drawable.restaurant_13, R.drawable.restaurant_12,
            R.drawable.restaurant_11, R.drawable.restaurant_10, R.drawable.restaurant_9,
            R.drawable.restaurant_8, R.drawable.restaurant_7, R.drawable.restaurant_6,
            R.drawable.restaurant_1, R.drawable.restaurant_2, R.drawable.restaurant_3,
            R.drawable.restaurant_4, R.drawable.restaurant_5
    };

    public static String getRandomAddress(){

        Random random=new Random();

        String string=String.valueOf(random.nextInt(100))+", "+
                streetNames[random.nextInt(streetNames.length)]+" street, "+
                townNames[random.nextInt(townNames.length)]+", "+
                stateCodes[random.nextInt(stateCodes.length)];

        return string;
    }

    public static List<Restaurant> getRestaurantList(){

        for(int i=0;i<NUMBER_OF_RESTAURANTS;i++){
            restaurants.add(new Restaurant(i+1,"Restaurant "+String.valueOf(i+1),
                            getRandomAddress(),
                    restaurantImages[i]));
        }

        restaurants.add(new Restaurant(NUMBER_OF_RESTAURANTS+1, "Test Add",
                getRandomAddress(),
                restaurantImages[NUMBER_OF_RESTAURANTS+1]));

        return restaurants;
    }

    public static void addNewRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }

}
