package com.nan.appstore.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nan.appstore.App;
import com.nan.appstore.R;
import com.nan.appstore.adapter.StellarMapAdapter;
import com.nan.appstore.model.RecommendBean;
import com.nan.appstore.model.SubjectBean;
import com.nan.appstore.ui.fragment.base.BaseFragment;
import com.nan.appstore.widget.loading.SlackLoadingView;
import com.nan.appstore.widget.randomLayout.StellarMap;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huannan on 2016/11/26.
 */

public class RecommendFragment extends BaseFragment {


    @BindView(R.id.sm_recommend)
    StellarMap smRecommend;
    @BindView(R.id.loading_view)
    SlackLoadingView loading_view;
    @BindView(R.id.ll_loading)
    LinearLayout ll_loading;

    private List<RecommendBean> mRecommendData;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        ButterKnife.bind(this, mRootView);

        requestRecommendData();
    }

    private void requestRecommendData() {

        App.getApi().getRecommendData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (!loading_view.isShowed) {
                            ll_loading.setVisibility(View.VISIBLE);
                            loading_view.start();
                        }
                    }
                })
                .doOnNext(new Consumer<List<RecommendBean>>() {
                    @Override
                    public void accept(List<RecommendBean> bean) throws Exception {
                        if (!loading_view.isShowed) {
                            loading_view.stop();
                            ll_loading.setVisibility(View.GONE);
                        }
                    }
                })
                .subscribe(new Consumer<List<RecommendBean>>() {
                    @Override
                    public void accept(List<RecommendBean> recommendData) throws Exception {
                        mRecommendData = recommendData;
                        initStellarMap();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    private void initStellarMap() {

        smRecommend.setRegularity(6, 9);
        smRecommend.setAdapter(new StellarMapAdapter(getActivity(), mRecommendData));
        //从第0组开始加载。设置动画
        smRecommend.setGroup(0, true);


    }

}

