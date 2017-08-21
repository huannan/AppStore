package com.nan.appstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.model.AppBean;
import com.nan.appstore.model.SubjectBean;
import com.nan.appstore.ui.activity.AppDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by huannan on 2016/8/25.
 */
public class SubjectListAdapter extends BaseQuickAdapter<SubjectBean> {

    private final Context mCtx;


    public SubjectListAdapter(Context context, int layoutResId, List<SubjectBean> data) {
        super(layoutResId, data);
        mCtx = context;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final SubjectBean bean) {

        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getUrl()).into((ImageView) baseViewHolder.getView(R.id.iv_icon));
        baseViewHolder.setText(R.id.tv_des, bean.getDes());

        baseViewHolder.getView(R.id.ll_subject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getPackageName());
                mCtx.startActivity(intent);
            }
        });
    }


}
