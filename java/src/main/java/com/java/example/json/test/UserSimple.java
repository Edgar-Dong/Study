package com.java.example.json.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author:無忌
 * @date:2020/11/16
 * @description:
 */
public class UserSimple {
    //    @Expose()//test06
    //    @SerializedName(value = "fullName", alternate = {"userName"})//test07
    String name;

    //    @Expose(serialize = false, deserialize = false)//test06
    String email;

    //    @Expose(serialize = false)//test06
    int age;

    //    @Expose(deserialize = false)//test06
    boolean isDeveloper;

    public UserSimple(String name, String email, int age, boolean isDeveloper) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isDeveloper = isDeveloper;
    }
}
