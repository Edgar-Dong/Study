package com.java.example.json;

import java.lang.reflect.Type;

/**
 * @author:無忌
 * @date:2020/11/13
 * @description:
 */
public interface IJson {

    String toJson(Object src);

    <T> T fromJson(String json, Class<T> classOfT);

    <T> T fromJson(String json, Type typeOfT);
}
