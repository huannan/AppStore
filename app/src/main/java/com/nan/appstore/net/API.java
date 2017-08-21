package com.nan.appstore.net;

import com.nan.appstore.model.AppBean;
import com.nan.appstore.model.CategoryBean;
import com.nan.appstore.model.DetailBean;
import com.nan.appstore.model.HomeBean;
import com.nan.appstore.model.HotBean;
import com.nan.appstore.model.RecommendBean;
import com.nan.appstore.model.SubjectBean;
import com.nan.appstore.model.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by huannan on 2016/11/26.
 */

public interface API {

    @GET("home")
    Observable<HomeBean> getHomeData(@Query("index") int index);

    @GET("game")
    Observable<List<AppBean>> getGameData(@Query("index") int index);

    @GET("app")
    Observable<List<AppBean>> getAppData(@Query("index") int index);

    @GET("category")
    Observable<List<CategoryBean>> getCategoryData();

    @GET("subject")
    Observable<List<SubjectBean>> getSubjectData(@Query("index") int index);

    @GET("recommend")
    Observable<List<RecommendBean>> getRecommendData();

    @GET("hot")
    Observable<List<HotBean>> getHotData();

    @GET("detail")
    Observable<DetailBean> getDetailData(@Query("packageName") String packageName);

    @GET("user")
    Observable<UserBean> getUserData();
}
