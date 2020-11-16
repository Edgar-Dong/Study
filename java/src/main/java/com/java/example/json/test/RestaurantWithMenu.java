package com.java.example.json.test;

import java.util.List;

/**
 * @author:無忌
 * @date:2020/11/16
 * @description:
 */
class RestaurantWithMenu {
    String name;
    List<RestaurantMenuItem> menu;

    public RestaurantWithMenu(String name, List<RestaurantMenuItem> menu) {
        this.name = name;
        this.menu = menu;
    }
}
