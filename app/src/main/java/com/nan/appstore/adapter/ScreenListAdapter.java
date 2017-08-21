package com.nan.appstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.model.PhotoInfo;
import com.nan.appstore.ui.activity.ImagePagerActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by huannan on 2016/8/25.
 */
public class ScreenListAdapter extends BaseQuickAdapter<String> {

    private final Context mCtx;
    List<String> mImageUrls;

    public ScreenListAdapter(Context context, int layoutResId, List<String> urls) {
        super(layoutResId, urls);
        mCtx = context;
        mImageUrls = urls;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final String imgUrl) {

        Picasso.with(mCtx).load(Const.URL_IMAGE_APP + imgUrl).into((ImageView) baseViewHolder.getView(R.id.iv_screen));
        baseViewHolder.getView(R.id.iv_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, ImagePagerActivity.class);
                intent.putExtra(Const.EXTRA_IMAGE_INFOS, new PhotoInfo(mImageUrls));
                mCtx.startActivity(intent);
            }
        });

    }

}
