package com.nan.appstore.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fyales.tagcloud.library.TagBaseAdapter;
import com.fyales.tagcloud.library.TagCloudLayout;
import com.nan.appstore.App;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.adapter.HotAdapter;
import com.nan.appstore.adapter.StellarMapAdapter;
import com.nan.appstore.model.HotBean;
import com.nan.appstore.ui.activity.AppDetailActivity;
import com.nan.appstore.ui.fragment.base.BaseFragment;
import com.nan.appstore.widget.loading.SlackLoadingView;

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

public class HotFragment extends BaseFragment {


    @BindView(R.id.tcl_hot)
    TagCloudLayout tcl_hot;
    @BindView(R.id.loading_view)
    SlackLoadingView loading_view;
    @BindView(R.id.ll_loading)
    LinearLayout ll_loading;

    private List<HotBean> mHotData;
    private HotAdapter mAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        ButterKnife.bind(this, mRootView);

        requestHotData();
    }


    private void requestHotData() {

        App.getApi().getHotData()
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
                .doOnNext(new Consumer<List<HotBean>>() {
                    @Override
                    public void accept(List<HotBean> bean) throws Exception {
                        if (!loading_view.isShowed) {
                            loading_view.stop();
                            ll_loading.setVisibility(View.GONE);
                        }
                    }
                })
                .subscribe(new Consumer<List<HotBean>>() {
                    @Override
                    public void accept(List<HotBean> hotData) throws Exception {
                        mHotData = hotData;
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

        mAdapter = new HotAdapter(getActivity(), mHotData);
        tcl_hot.setAdapter(mAdapter);
        tcl_hot.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                intent.putExtra(Const.EXTRA_PACKAGE_NAME, mHotData.get(position).getPackageName());
                startActivity(intent);

            }
        });
    }

}

