package com.example.test.api;

import com.example.test.bean.GankAndroidBean;
import com.example.test.bean.GankMeiziBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 干货集中营API
 * Created by liu on 2016/10/14.
 */
public interface GankApi {
    @GET("data/Android/{count}/{page}")
    Observable<GankAndroidBean> getGankAndroid(@Path("count") int count, @Path("page") int page);
    @GET("data/福利/{count}/{page}")
    Observable<GankMeiziBean> getGankMeizi(@Path("count") int count, @Path("page") int page);
    @GET("data/Android/{count}/{page}")
    Call<GankAndroidBean> getGankAndroidNew(@Path("count") int count, @Path("page") int page);
}
