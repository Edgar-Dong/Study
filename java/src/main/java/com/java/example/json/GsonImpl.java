package com.java.example.json;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @author:無忌
 * @date:2020/11/13
 * @description:
 */
public class GsonImpl implements IJson {

    private Gson mGson;
    private GsonBuilder mGsonBuilder;

    public GsonImpl() {
        mGsonBuilder = new GsonBuilder();
    }

    public void setGson(Gson gson) {
        this.mGson = gson;
    }

    @Override
    public String toJson(Object src) {
        if (mGson == null) {
            mGson = mGsonBuilder.create();
        }
        return mGson.toJson(src);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        if (mGson == null) {
            mGson = mGsonBuilder.create();
        }
        return mGson.fromJson(json, classOfT);
    }

    @Override
    public <T> T fromJson(String json, Type typeOfT) {
        if (mGson == null) {
            mGson = mGsonBuilder.create();
        }
        return mGson.fromJson(json, typeOfT);
    }

    public static class Builder {
        private GsonImpl gsonImpl;

        public Builder() {
            gsonImpl = new GsonImpl();
        }

        public Builder excludeFieldsWithoutExposeAnnotation() {
            gsonImpl.excludeFieldsWithoutExposeAnnotation();
            return this;
        }

        public GsonImpl build() {
            return gsonImpl;
        }
    }

    private GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        return mGsonBuilder.excludeFieldsWithoutExposeAnnotation();
    }

    private GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy){
        return mGsonBuilder.setFieldNamingStrategy(fieldNamingStrategy);
    }
}
