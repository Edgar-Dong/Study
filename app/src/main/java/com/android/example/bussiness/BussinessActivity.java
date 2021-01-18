package com.android.example.bussiness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.example.R;
import com.android.example.bussiness.core.GeeksApis;
import com.android.example.bussiness.core.bean.BaseResponse;
import com.android.example.bussiness.core.bean.main.collect.FeedArticleListData;
import com.android.example.bussiness.di.module.HttpModule;
import com.android.example.common.log.Logger;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class BussinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussiness);


        HttpModule httpModule = new HttpModule();
        Retrofit retrofit = httpModule.provideGeeksRetrofit();
        GeeksApis geeksApis = retrofit.create(GeeksApis.class);
        Observable<BaseResponse<FeedArticleListData>> mArticleObservable = geeksApis.getFeedArticleList(0);
        mArticleObservable.subscribeOn(Schedulers.io()).subscribe(new Consumer<BaseResponse<FeedArticleListData>>() {
            @Override
            public void accept(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) throws Exception {
                String jsonString = new Gson().toJson(feedArticleListDataBaseResponse);
                Logger.get().d(BussinessActivity.class.getSimpleName(),"onNext jsonString:" + jsonString);
            }
        });
    }
}