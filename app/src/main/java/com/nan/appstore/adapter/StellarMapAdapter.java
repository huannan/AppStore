package com.nan.appstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nan.appstore.Const;
import com.nan.appstore.model.RecommendBean;
import com.nan.appstore.ui.activity.AppDetailActivity;
import com.nan.appstore.widget.randomLayout.StellarMap;

import java.util.List;
import java.util.Random;

/**
 * Created by huannan on 2016/11/27.
 */
public class StellarMapAdapter implements StellarMap.Adapter {

    private Context mCtx;
    private Random mRandom;
    private List<RecommendBean> mDatas;

    public StellarMapAdapter(Context context, List<RecommendBean> datas) {
        mRandom = new Random();
        mCtx = context;
        mDatas = datas;
    }

    /**
     * 一共有多少组数据
     */
    @Override
    public int getGroupCount() {
        return 2;
    }

    /**
     * 每一组有多少数据
     */
    @Override
    public int getCount(int group) {
        return 15;
    }

    @Override
    public View getView(int group, final int position, View convertView) {

        TextView textView = new TextView(mCtx);

        int red = 20 + mRandom.nextInt(220);
        int green = 20 + mRandom.nextInt(220);
        int blue = 20 + mRandom.nextInt(220);
        //合成一种新的颜色
        int color = Color.rgb(red, green, blue);

        //设置字体颜色
        textView.setTextColor(color);
        //设置字体大小
        textView.setTextSize(10 + mRandom.nextInt(15));

        textView.setText(mDatas.get(position).getName());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                intent.putExtra(Const.EXTRA_PACKAGE_NAME, mDatas.get(position).getPackageName());
                mCtx.startActivity(intent);
            }
        });

        return textView;
    }

    @Override
    public int getNextGroupOnPan(int group, float degree) {
        return (group + 1) % 2;
    }

    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
        return (group + 1) % 2;
    }

}