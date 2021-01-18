package com.android.example.bussiness.core.http;

import com.android.example.bussiness.core.bean.BaseResponse;
import com.android.example.bussiness.core.bean.main.collect.FeedArticleListData;

import io.reactivex.Observable;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
public interface HttpHelper {
    /**
     * 获取feed文章列表
     *
     * @param pageNum 页数
     * @return feed文章列表数据
     */
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum);
}
