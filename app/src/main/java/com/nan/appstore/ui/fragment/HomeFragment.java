package com.nan.appstore.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.nan.appstore.App;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.adapter.AppListAdapter;
import com.nan.appstore.model.HomeBean;
import com.nan.appstore.ui.activity.AppDetailActivity;
import com.nan.appstore.ui.fragment.base.BaseFragment;
import com.nan.appstore.widget.ImageSliderView;
import com.nan.appstore.widget.loading.SlackLoadingView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.nan.appstore.Const.STATE_INIT;
import static com.nan.appstore.Const.STATE_LOAD_MORE;
import static com.nan.appstore.Const.STATE_REFRESH;

/**
 * Created by huannan on 2016/11/26.
 */

public class HomeFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    public static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout rl_refresh;

    @BindView(R.id.loading_view)
    SlackLoadingView loading_view;
    @BindView(R.id.ll_loading)
    LinearLayout ll_loading;

    private HomeBean mHomeBean;

    private BGAMeiTuanRefreshViewHolder mRefreshViewHolder;

    //用于记录当前的状态
    private int curState = STATE_INIT;
    private int curPage = 0;

    private AppListAdapter mAdapter;
    private SliderLayout mSliderLayout;
    private PagerIndicator mIndicator;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        ButterKnife.bind(this, mRootView);

        requestData();

    }

    private void requestData() {
        App.getApi().getHomeData(curPage)
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
                .doOnNext(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        if (!loading_view.isShowed) {
                            loading_view.stop();
                            ll_loading.setVisibility(View.GONE);
                        }
                    }
                })
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        mHomeBean = homeBean;
                        showData();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    private void showData() {

        switch (curState) {

            case STATE_INIT:

                initList();
                initBanner();
                break;

            case STATE_REFRESH:

                mAdapter.setNewData(mHomeBean.getList());
                //rv_list.scrollToPosition(0);
                rl_refresh.endRefreshing();

                break;

            case STATE_LOAD_MORE:

                mAdapter.addData(mHomeBean.getList());
                rl_refresh.endLoadingMore();

                break;
        }

    }


    private void initList() {
        // 为BGARefreshLayout设置代理
        rl_refresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        mRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(getActivity(), true);

        mRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        mRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        mRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);

        // 设置下拉刷新和上拉加载更多的风格
        //        rl_refresh.setBackgroundColor(getResources().getColor(R.color.bg));

        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setItemAnimator(new DefaultItemAnimator());
//        rv_list.addItemDecoration(new DefaultListDecoration());

        mAdapter = new AppListAdapter(getActivity(), R.layout.item_list_app, mHomeBean.getList());
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        View header = getActivity().getLayoutInflater().inflate(R.layout.rv_header_home, null);
        mAdapter.addHeaderView(header);

        mSliderLayout = (SliderLayout) header.findViewById(R.id.slider);
        mIndicator = (PagerIndicator) header.findViewById(R.id.custom_indicator);

        rv_list.setAdapter(mAdapter);
        rl_refresh.setRefreshViewHolder(mRefreshViewHolder);

    }


    private void initBanner() {

        mSliderLayout.setSliderTransformDuration(2000, null);
        mSliderLayout.setDuration(5000);
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomIndicator(mIndicator);
        mSliderLayout.stopAutoCycle();

        for (int i = 0; i < mHomeBean.getPicture().size(); i++) {
            //新建三个展示View，并且添加到SliderLayout
            ImageSliderView isv = new ImageSliderView(getActivity());
            isv.image(Const.URL_IMAGE + mHomeBean.getPicture().get(i).getImgUrl());
            final int finalI = i;
            isv.setOnSliderClickListener(new ImageSliderView.OnSliderClickListener() {
                @Override
                public void onclick(ImageView v) {
                    Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                    intent.putExtra(Const.EXTRA_PACKAGE_NAME, mHomeBean.getPicture().get(finalI).getPackageName());
                    startActivity(intent);
                }
            });
            mSliderLayout.addSlider(isv);
        }

        mSliderLayout.stopAutoCycle();
        mSliderLayout.setCurrentPosition(0, false);

        //4秒之后开始自动播放
        Observable.just("")
                .debounce(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mSliderLayout.startAutoCycle();
                    }
                });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        curPage = 0;
        curState = STATE_REFRESH;
        requestData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        curPage += 1;
        curState = STATE_LOAD_MORE;
        requestData();

        return true;
    }


}