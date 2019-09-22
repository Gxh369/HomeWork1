package com.jy.gxh.day3homeworka;

import com.jy.gxh.day3homeworka.beans.MyBean;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by GXH on 2019/9/20.
 */

public interface ApiService {
    String DATA_URL="http://static.owspace.com/";
    @GET("?c=api&a=getList&page_id=0")
    Observable<MyBean> getbean();

}
