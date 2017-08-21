package com.nan.appstore.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nan.appstore.R;
import com.nan.appstore.ui.activity.ScanActivity;
import com.nan.appstore.ui.fragment.base.BaseFragment;
import com.nan.appstore.widget.AppToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huannan on 2016/11/26.
 */

public class AppsFragment extends BaseFragment {

    @BindView(R.id.tl_tab)
    TabLayout tl_tab;
    @BindView(R.id.vp_content)
    ViewPager vp_content;
    @BindView(R.id.toolbar)
    AppToolbar toolbar;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_apps, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        ButterKnife.bind(this, mRootView);

        initToolbar();

        initTabs();
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

    private void initTabs() {
        HomeFragment homeFragment = new HomeFragment();
        AppFragment appFragment = new AppFragment();
        SubjectFragment subjectFragment = new SubjectFragment();
        RecommendFragment recommendFragment = new RecommendFragment();
        HotFragment hotFragment = new HotFragment();
        Fragment[] tabFragments = new Fragment[]{homeFragment, appFragment, subjectFragment, recommendFragment, hotFragment};


        tl_tab.addTab(tl_tab.newTab(), true);
        tl_tab.addTab(tl_tab.newTab(), false);
        tl_tab.addTab(tl_tab.newTab(), false);
        tl_tab.addTab(tl_tab.newTab(), false);
        tl_tab.addTab(tl_tab.newTab(), false);

        AppsFragmentPagerAdapter appsFragmentPagerAdapter = new AppsFragmentPagerAdapter(
                getFragmentManager(),
                tabFragments
        );

        vp_content.setOffscreenPageLimit(5);
        vp_content.setAdapter(appsFragmentPagerAdapter);

        tl_tab.setupWithViewPager(vp_content);
    }


    class AppsFragmentPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] mFragments;

        public AppsFragmentPagerAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);

            mFragments = fragments;

        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab1_home);
                case 1:
                    return getString(R.string.tab1_app);
                case 2:
                    return getString(R.string.tab1_subject);
                case 3:
                    return getString(R.string.tab1_recommend);
                case 4:
                    return getString(R.string.tab1_hot);
                default:
                    return super.getPageTitle(position);
            }
        }
    }
}

