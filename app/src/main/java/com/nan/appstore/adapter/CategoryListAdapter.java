package com.nan.appstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.model.AppBean;
import com.nan.appstore.model.CategoryBean;
import com.nan.appstore.ui.activity.AppDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by huannan on 2016/8/25.
 */
public class CategoryListAdapter extends BaseQuickAdapter<CategoryBean> {

    private final Context mCtx;


    public CategoryListAdapter(Context context, int layoutResId, List<CategoryBean> data) {
        super(layoutResId, data);
        mCtx = context;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final CategoryBean bean) {

        baseViewHolder.setText(R.id.tv_title, bean.getTitle());

        for (int i = 0; i < 6; i++) {

            if (i < bean.getInfos().size()) {
                switch (i) {
                    case 0:
                        baseViewHolder.getView(R.id.rl_1).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_2).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_3).setVisibility(View.VISIBLE);
                        baseViewHolder.setText(R.id.tv_1, bean.getInfos().get(i).getName1());
                        baseViewHolder.setText(R.id.tv_2, bean.getInfos().get(i).getName2());
                        baseViewHolder.setText(R.id.tv_3, bean.getInfos().get(i).getName3());
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl1()).into((ImageView) baseViewHolder.getView(R.id.iv_1));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl2()).into((ImageView) baseViewHolder.getView(R.id.iv_2));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl3()).into((ImageView) baseViewHolder.getView(R.id.iv_3));

                        final int index0 = i;
                        baseViewHolder.getView(R.id.rl_1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index0).getPackageName1());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index0).getPackageName2());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_3).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index0).getPackageName3());
                                mCtx.startActivity(intent);
                            }
                        });
                        break;
                    case 1:
                        baseViewHolder.setText(R.id.tv_4, bean.getInfos().get(i).getName1());
                        baseViewHolder.setText(R.id.tv_5, bean.getInfos().get(i).getName2());
                        baseViewHolder.setText(R.id.tv_6, bean.getInfos().get(i).getName3());
                        baseViewHolder.getView(R.id.rl_4).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_5).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_6).setVisibility(View.VISIBLE);
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl1()).into((ImageView) baseViewHolder.getView(R.id.iv_4));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl3()).into((ImageView) baseViewHolder.getView(R.id.iv_5));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl3()).into((ImageView) baseViewHolder.getView(R.id.iv_6));

                        final int index1 = i;
                        baseViewHolder.getView(R.id.rl_4).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index1).getPackageName1());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_5).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index1).getPackageName2());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_6).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index1).getPackageName3());
                                mCtx.startActivity(intent);
                            }
                        });

                        break;
                    case 2:
                        baseViewHolder.setText(R.id.tv_7, bean.getInfos().get(i).getName1());
                        baseViewHolder.setText(R.id.tv_8, bean.getInfos().get(i).getName2());
                        baseViewHolder.setText(R.id.tv_9, bean.getInfos().get(i).getName3());
                        baseViewHolder.getView(R.id.rl_7).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_8).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_9).setVisibility(View.VISIBLE);
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl1()).into((ImageView) baseViewHolder.getView(R.id.iv_7));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl2()).into((ImageView) baseViewHolder.getView(R.id.iv_8));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl3()).into((ImageView) baseViewHolder.getView(R.id.iv_9));

                        final int index2 = i;
                        baseViewHolder.getView(R.id.rl_7).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index2).getPackageName1());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_8).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index2).getPackageName2());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_9).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index2).getPackageName3());
                                mCtx.startActivity(intent);
                            }
                        });

                        break;
                    case 3:
                        baseViewHolder.setText(R.id.tv_10, bean.getInfos().get(i).getName1());
                        baseViewHolder.setText(R.id.tv_11, bean.getInfos().get(i).getName2());
                        baseViewHolder.setText(R.id.tv_12, bean.getInfos().get(i).getName3());
                        baseViewHolder.getView(R.id.rl_10).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_11).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_12).setVisibility(View.VISIBLE);
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl1()).into((ImageView) baseViewHolder.getView(R.id.iv_10));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl2()).into((ImageView) baseViewHolder.getView(R.id.iv_11));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl3()).into((ImageView) baseViewHolder.getView(R.id.iv_12));

                        final int index3 = i;
                        baseViewHolder.getView(R.id.rl_10).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index3).getPackageName1());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_11).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index3).getPackageName2());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_12).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (!TextUtils.isEmpty(bean.getInfos().get(index3).getPackageName3())) {
                                    Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                    intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index3).getPackageName3());
                                    mCtx.startActivity(intent);
                                }
                            }
                        });

                        break;
                    case 4:
                        baseViewHolder.setText(R.id.tv_13, bean.getInfos().get(i).getName1());
                        baseViewHolder.setText(R.id.tv_14, bean.getInfos().get(i).getName2());
                        baseViewHolder.setText(R.id.tv_15, bean.getInfos().get(i).getName3());
                        baseViewHolder.getView(R.id.rl_13).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_14).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_15).setVisibility(View.VISIBLE);
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl1()).into((ImageView) baseViewHolder.getView(R.id.iv_13));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl2()).into((ImageView) baseViewHolder.getView(R.id.iv_14));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl3()).into((ImageView) baseViewHolder.getView(R.id.iv_15));

                        final int index4 = i;
                        baseViewHolder.getView(R.id.rl_13).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index4).getPackageName1());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_14).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index4).getPackageName2());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_15).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index4).getPackageName3());
                                mCtx.startActivity(intent);
                            }
                        });

                        break;
                    case 5:
                        baseViewHolder.setText(R.id.tv_16, bean.getInfos().get(i).getName1());
                        baseViewHolder.setText(R.id.tv_17, bean.getInfos().get(i).getName2());
                        baseViewHolder.setText(R.id.tv_18, bean.getInfos().get(i).getName3());
                        baseViewHolder.getView(R.id.rl_16).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_17).setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.rl_18).setVisibility(View.VISIBLE);
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl1()).into((ImageView) baseViewHolder.getView(R.id.iv_16));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl2()).into((ImageView) baseViewHolder.getView(R.id.iv_17));
                        Picasso.with(mCtx).load(Const.URL_IMAGE + bean.getInfos().get(i).getUrl3()).into((ImageView) baseViewHolder.getView(R.id.iv_18));

                        final int index5 = i;
                        baseViewHolder.getView(R.id.rl_16).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index5).getPackageName1());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_17).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index5).getPackageName2());
                                mCtx.startActivity(intent);
                            }
                        });
                        baseViewHolder.getView(R.id.rl_18).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getInfos().get(index5).getPackageName3());
                                mCtx.startActivity(intent);
                            }
                        });

                        break;
                }
            } else {

                switch (i) {

                    case 0:
                        baseViewHolder.getView(R.id.rl_1).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_2).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_3).setVisibility(View.GONE);
                        break;
                    case 1:
                        baseViewHolder.getView(R.id.rl_4).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_5).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_6).setVisibility(View.GONE);
                        break;
                    case 2:
                        baseViewHolder.getView(R.id.rl_7).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_8).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_9).setVisibility(View.GONE);
                        break;
                    case 3:
                        baseViewHolder.getView(R.id.rl_10).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_11).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_12).setVisibility(View.GONE);
                        break;
                    case 4:
                        baseViewHolder.getView(R.id.rl_13).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_14).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_15).setVisibility(View.GONE);
                        break;
                    case 5:
                        baseViewHolder.getView(R.id.rl_16).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_17).setVisibility(View.GONE);
                        baseViewHolder.getView(R.id.rl_18).setVisibility(View.GONE);
                        break;
                }

            }

        }
    }


}
