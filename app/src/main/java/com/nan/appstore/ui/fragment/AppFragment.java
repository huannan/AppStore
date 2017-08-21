package com.nan.appstore.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nan.appstore.App;
import com.nan.appstore.R;
import com.nan.appstore.adapter.AppListAdapter;
import com.nan.appstore.model.AppBean;
import com.nan.appstore.model.HomeBean;
import com.nan.appstore.ui.fragment.base.BaseFragment;
import com.nan.appstore.widget.loading.SlackLoadingView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
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

public class AppFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    public static final String TAG = AppFragment.class.getSimpleName();

    @BindView(R.id.rv_app_list)
    RecyclerView rv_app_list;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout rl_refresh;
    @BindView(R.id.loading_view)
    SlackLoadingView loading_view;
    @BindView(R.id.ll_loading)
    LinearLayout ll_loading;

    private int curPage = 0;

    private List<AppBean> mAppBeanList;

    private BGAMeiTuanRefreshViewHolder mRefreshViewHolder;

    //用于记录当前的状态
    private int curState = STATE_INIT;
    private AppListAdapter mAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app, container, false);
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
        App.getApi().getAppData(curPage)
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
                .doOnNext(new Consumer<List<AppBean>>() {
                    @Override
                    public void accept(List<AppBean> appBeen) throws Exception {
                        if (!loading_view.isShowed) {
                            loading_view.stop();
                            ll_loading.setVisibility(View.GONE);
                        }
                    }
                })
                .subscribe(new Consumer<List<AppBean>>() {
                    @Override
                    public void accept(List<AppBean> appBeanList) throws Exception {
                        mAppBeanList = appBeanList;
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
                break;

            case STATE_REFRESH:

                mAdapter.setNewData(mAppBeanList);
                //rv_list.scrollToPosition(0);
                rl_refresh.endRefreshing();

                break;

            case STATE_LOAD_MORE:

                mAdapter.addData(mAppBeanList);
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

        rv_app_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_app_list.setItemAnimator(new DefaultItemAnimator());
//        rv_list.addItemDecoration(new DefaultListDecoration());

        mAdapter = new AppListAdapter(getActivity(), R.layout.item_list_app, mAppBeanList);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        rv_app_list.setAdapter(mAdapter);
        rl_refresh.setRefreshViewHolder(mRefreshViewHolder);
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

