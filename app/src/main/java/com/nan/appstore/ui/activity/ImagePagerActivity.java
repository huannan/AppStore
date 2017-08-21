package com.nan.appstore.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.model.PhotoInfo;
import com.nan.appstore.ui.activity.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by huannan on 2016/8/25.
 */
public class ImagePagerActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;

    private PhotoInfo mPhotoInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        ButterKnife.bind(this);

        init();
    }


    public void init() {

        setTranslucent();

        mPhotoInfo = (PhotoInfo) getIntent().getSerializableExtra(Const.EXTRA_IMAGE_INFOS);

        initPager();
    }

    private void initPager() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 10));
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mPhotoInfo.getmImageUrls().size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(ImagePagerActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.CENTER_CROP);

                //加载图片
                Picasso.with(ImagePagerActivity.this).load(Const.URL_IMAGE_APP + mPhotoInfo.getmImageUrls().get(position)).into(view);

                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        indicator.setViewPager(mPager);

    }

}
