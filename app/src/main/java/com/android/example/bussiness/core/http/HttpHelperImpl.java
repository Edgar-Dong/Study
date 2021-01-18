package com.android.example.bussiness.core.http;

import com.android.example.bussiness.core.GeeksApis;
import com.android.example.bussiness.core.bean.BaseResponse;
import com.android.example.bussiness.core.bean.main.collect.FeedArticleListData;
import com.android.example.bussiness.di.module.HttpModule;

import io.reactivex.Observable;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
public class HttpHelperImpl implements HttpHelper {
    private GeeksApis mGeeksApis;

    public HttpHelperImpl(GeeksApis geeksApis) {
        mGeeksApis = geeksApis;
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum) {
        return mGeeksApis.getFeedArticleList(pageNum);
    }
}
