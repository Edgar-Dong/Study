package com.android.example.bussiness.di.module;

import com.android.example.bussiness.core.GeeksApis;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
public class HttpModule {
    public Retrofit provideGeeksRetrofit() {
        Retrofit.Builder retrofitBuilder = provideRetrofitBuilder();
        OkHttpClient.Builder clientBuilder = provideOkhttpBuilder();
        OkHttpClient client = provideClient(clientBuilder);
        return provideGeeksRetrofit(retrofitBuilder, client);
    }

    Retrofit provideGeeksRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GeeksApis.HOST);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder.baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    OkHttpClient.Builder provideOkhttpBuilder() {
        return new OkHttpClient.Builder();
    }

    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        return builder.build();
    }
}
