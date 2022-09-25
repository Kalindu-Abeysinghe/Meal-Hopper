package com.example.foodapp.Database;

public class FoodAppDBSchema {

    public static class RestaurantTable{

        public static final String NAME="restaurants";
        public static class Cols{
            public static final String ID="restaurant_id";
            public static final String NAME="name";
            public static final String ADDRESS="address";
            public static final String IMAGE_ID="image_id";
        }
    }

    public static class MenuItemTable{

        public static final String NAME="menu_item";
        public static class Cols{
            public static final String RESTAURANT_ID="restaurant_id";
            public static final String NAME="food_name";
            public static final String PRICE="price";
            public static final String IMAGE_ID="image_id";
        }
    }

    public static class UserTable{

        public static final String NAME="user";
        public static class Cols{
            public static final String EMAIL="email";
            public static final String FIRST_NAME="first_name";
            public static final String LAST_NAME="last_name";
            public static final String PASSWORD="password";
            public static final String ADDRESS="address";
            public static final String TELEPHONE="telephone";
        }
    }

    public static class OrderHistoryTable{

        public static final String NAME="order_history";
        public static class Cols{
            public static final String ID="id";
            public static final String TOTAL_COST="total_cost";
            public static final String USER_EMAIL="user_email";
            public static final String ORDER_DATE="order_date";
            public static final String ORDER_TIME="order_time";
        }
    }

    public static class OrderMenuItemTable{

        public static final String NAME="order_items";
        public static class Cols{
            public static final String ORDER_ID="order_id";
            public static final String QUANTITY="quantity";
            public static final String ITEM_NAME="food_name";
            public static final String IMAGE_ID="image_id";
            public static final String RESTAURANT_NAME="restaurant_name";
            public static final String COST="cost";
        }
    }
}
