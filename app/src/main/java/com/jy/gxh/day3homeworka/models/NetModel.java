package com.jy.gxh.day3homeworka.models;

import android.util.Log;

import com.google.gson.Gson;
import com.jy.gxh.day3homeworka.ApiService;
import com.jy.gxh.day3homeworka.beans.DatasBean;
import com.jy.gxh.day3homeworka.beans.MyBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GXH on 2019/9/20.
 */

public class NetModel {
    private MyCallBack myCallBack;

    public NetModel(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    private static final String TAG = "NetModel";

    public void setDatas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.DATA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getbean().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyBean myBean) {
                        Log.i(TAG, "onNext: " + myBean.toString());
                        ArrayList<DatasBean> datas = (ArrayList<DatasBean>) myBean.getDatas();
                        myCallBack.onSucceed(datas);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                        myCallBack.onField("请求错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface MyCallBack {
        void onSucceed(ArrayList<DatasBean> datasBeans);

        void onField(String string);
    }
}
