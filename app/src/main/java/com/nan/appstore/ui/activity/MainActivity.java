package com.nan.appstore.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.brioal.bottomtab.entity.TabEntity;
import com.brioal.bottomtab.interfaces.OnTabSelectedListener;
import com.brioal.bottomtab.view.BottomLayout;
import com.nan.appstore.R;
import com.nan.appstore.ui.activity.base.BaseActivity;
import com.nan.appstore.ui.fragment.AppsFragment;
import com.nan.appstore.ui.fragment.CategoryFragment;
import com.nan.appstore.ui.fragment.GameFragment;
import com.nan.appstore.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huannan on 2016/11/27.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.bl_tab)
    BottomLayout bl_tab;

    @BindString(R.string.tab_apps)
    String string_tab_apps;
    @BindString(R.string.tab_games)
    String string_tab_games;
    @BindString(R.string.tab_class)
    String string_tab_class;
    @BindString(R.string.tab_mine)
    String string_tab_mine;
    private AppsFragment appsFragment;
    private GameFragment gameFragment;
    private CategoryFragment categoryFragment;
    private MineFragment mineFragment;
    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setTranslucent();
        initFragments();
        initTabs();
    }

    private void initFragments() {

        appsFragment = new AppsFragment();
        gameFragment = new GameFragment();
        categoryFragment = new CategoryFragment();
        mineFragment = new MineFragment();
        mFragments = new Fragment[]{appsFragment, gameFragment, categoryFragment, mineFragment};

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, mFragments[0], mFragments[0].getClass().getSimpleName())
                .add(R.id.fl_content, mFragments[1], mFragments[1].getClass().getSimpleName())
                .add(R.id.fl_content, mFragments[2], mFragments[2].getClass().getSimpleName())
                .add(R.id.fl_content, mFragments[3], mFragments[3].getClass().getSimpleName())
                .hide(mFragments[1])
                .hide(mFragments[2])
                .hide(mFragments[3])
                .show(mFragments[0])
                .commit();

    }


    private void initTabs() {

        List<TabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity(R.drawable.icon_tab_apps, string_tab_apps));
        tabEntities.add(new TabEntity(R.drawable.icon_tab_game, string_tab_games));
        tabEntities.add(new TabEntity(R.drawable.icon_tab_recommend, string_tab_class));
        tabEntities.add(new TabEntity(R.drawable.icon_tab_mine, string_tab_mine));

        bl_tab.setList(tabEntities);
        bl_tab.setSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onSelected(int position) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                for (int i = 0; i < mFragments.length; i++) {
                    if (i == position) {
                        ft.show(mFragments[i]);
                    } else {
                        ft.hide(mFragments[i]);
                    }
                }
                ft.commit();

            }
        });
    }


}
