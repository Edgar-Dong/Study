package com.java.example.json.test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.java.example.json.GsonImpl;
import com.java.example.json.IJson;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:無忌
 * @date:2020/11/14
 * @description:
 */
public class GsonImplTest {
    IJson json = null;

    @Before
    public void before() {
        json = new GsonImpl.Builder().build();
    }

    @Test
    public void test01() {
        List<RestaurantMenuItem> menu = new ArrayList<>();
        menu.add(new RestaurantMenuItem("Spaghetti", 7.99f));
        menu.add(new RestaurantMenuItem("Steak", 12.99f));
        menu.add(new RestaurantMenuItem("Salad", 5.99f));

        RestaurantWithMenu restaurant =
                new RestaurantWithMenu("Future Studio Steak House", menu);

        System.out.println(json.toJson(restaurant));
    }

    @Test
    public void test02() {
        String founderJson = "[{'name': 'Christian','flowerCount': 1}, {'name': 'Marcus', 'flowerCount': 3}, {'name': 'Norman', 'flowerCount': 2}]";
        //Founder[] founderArray = GsonImpl.getInstance().fromJson(founderJson,Founder[].class);
        List<Founder> founderList = json.fromJson(founderJson, new TypeToken<List<Founder>>() {
        }.getType());
    }

    @Test
    public void test03() {
        String generalInfoJson = "{'name': 'Future Studio Dev Team', 'website': 'https://futurestud.io', 'founders': [{'name': 'Christian', 'flowerCount': 1 }, {'name': 'Marcus','flowerCount': 3 }, {'name': 'Norman','flowerCount': 2 }]}";
        GeneralInfo generalInfo = json.fromJson(generalInfoJson, GeneralInfo.class);
    }

    @Test
    public void test04() {
        Map<String, List<String>> employees = new HashMap<>();
        employees.put("A", Arrays.asList("Andreas", "Arnold", "Aden"));
        employees.put("B", Arrays.asList("Christian", "Carter"));
        employees.put("C", Arrays.asList("Marcus", "Mary"));

        System.out.println(json.toJson(employees));
    }

    @Test
    public void test05() {
        String dollarJson = "{ '1$': { 'amount': 1, 'currency': 'Dollar'}, '2$': { 'amount': 2, 'currency': 'Dollar'}, '3€': { 'amount': 3, 'currency': 'Euro'} }";
        Map<String, AmountWithCurrency> amountCurrency = json.fromJson(dollarJson, new TypeToken<HashMap<String, AmountWithCurrency>>() {
        }.getType());
    }

    @Test
    public void test06() {
        UserSimple userObject = new UserSimple(
                "Norman",
                "norman@futurestud.io",
                26,
                true
        );

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        ((GsonImpl) json).setGson(gson);
        System.out.println(json.toJson(userObject));
    }

    @Test
    public void test07() {
        String userJson = "{'age':26,'email':'norman@futurestud.io','isDeveloper':true,'userName':'Norman'}";
        UserSimple userSimple = json.fromJson(userJson, UserSimple.class);
    }

    @Test
    public void test08() {
        UserSimple userObject = new UserSimple(
                "Norman",
                "norman@futurestud.io",
                26,
                true
        );

        Gson gson = new GsonBuilder().setFieldNamingStrategy(new FieldNamingStrategy() {
            @Override
            public String translateName(Field f) {
                return f.getName().toUpperCase();
            }
        }).create();
        ((GsonImpl) json).setGson(gson);

        System.out.println(json.toJson(userObject));
    }

    @Test
    public void test09() {
        String userJson = "{'reviewer_name': 'Marcus'}";

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        ((GsonImpl) json).setGson(gson);
        PostReviewer postReviewer = json.fromJson(userJson, PostReviewer.class);
    }
}