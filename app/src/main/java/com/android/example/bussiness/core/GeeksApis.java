package com.android.example.bussiness.core;

import com.android.example.bussiness.core.bean.BaseResponse;
import com.android.example.bussiness.core.bean.main.collect.FeedArticleListData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
public interface GeeksApis {
    String HOST = "https://www.wanandroid.com/";

    @GET("article/list/{num}/json")
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(@Path("num") int num);
}
