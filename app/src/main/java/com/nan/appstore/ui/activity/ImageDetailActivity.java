package com.nan.appstore.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.ui.activity.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huannan on 2016/8/25.
 */
public class ImageDetailActivity extends BaseActivity {

    @BindView(R.id.pv_image)
    PhotoView pv_image;
    private String mImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        init();
    }


    public void init() {

        setTranslucent();

        mImageUrl = getIntent().getStringExtra(Const.EXTRA_IMAGE_URL);

        initPhotoView();
    }

    private void initPhotoView() {
        pv_image.enable();
        pv_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //加载图片
        Picasso.with(this).load(mImageUrl).into(pv_image);
    }


}
