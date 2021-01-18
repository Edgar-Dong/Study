package com.android.example.bussiness.core;

import com.android.example.bussiness.core.bean.BaseResponse;
import com.android.example.bussiness.core.bean.main.collect.FeedArticleListData;
import com.android.example.bussiness.core.http.HttpHelper;

import io.reactivex.Observable;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
public class DataManager implements HttpHelper {
    private HttpHelper mHttpHelper;

    public DataManager(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum) {
        return mHttpHelper.getFeedArticleList(pageNum);
    }
}
