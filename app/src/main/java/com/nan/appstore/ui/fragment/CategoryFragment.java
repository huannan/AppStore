package com.nan.appstore.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.nan.appstore.adapter.CategoryListAdapter;
import com.nan.appstore.model.AppBean;
import com.nan.appstore.model.CategoryBean;
import com.nan.appstore.ui.activity.ScanActivity;
import com.nan.appstore.ui.fragment.base.BaseFragment;
import com.nan.appstore.widget.AppToolbar;
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

public class CategoryFragment extends BaseFragment {

    public static final String TAG = CategoryFragment.class.getSimpleName();

    @BindView(R.id.rv_category_list)
    RecyclerView rv_category_list;

    @BindView(R.id.loading_view)
    SlackLoadingView loading_view;
    @BindView(R.id.ll_loading)
    LinearLayout ll_loading;
    @BindView(R.id.toolbar)
    AppToolbar toolbar;

    private int curPage = 0;

    private List<CategoryBean> mCategoryBeans;

    private CategoryListAdapter mAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        ButterKnife.bind(this, mRootView);
        initToolbar();
        requestData();
    }


    private void initToolbar() {

        toolbar.setScanBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScanActivity.class);
                startActivity(intent);
            }
        });

    }

    private void requestData() {
        App.getApi().getCategoryData()
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
                .doOnNext(new Consumer<List<CategoryBean>>() {
                    @Override
                    public void accept(List<CategoryBean> bean) throws Exception {
                        if (!loading_view.isShowed) {
                            loading_view.stop();
                            ll_loading.setVisibility(View.GONE);
                        }
                    }
                })
                .subscribe(new Consumer<List<CategoryBean>>() {
                    @Override
                    public void accept(List<CategoryBean> categoryBeen) throws Exception {
                        mCategoryBeans = categoryBeen;
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
        initList();
    }


    private void initList() {
        mAdapter = new CategoryListAdapter(getActivity(), R.layout.item_list_categoey, mCategoryBeans);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        rv_category_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_category_list.setItemAnimator(new DefaultItemAnimator());

        rv_category_list.setAdapter(mAdapter);
    }


}

