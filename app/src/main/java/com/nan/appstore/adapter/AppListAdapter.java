package com.nan.appstore.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.model.AppBean;
import com.nan.appstore.net.download.DownloadController;
import com.nan.appstore.ui.activity.AppDetailActivity;
import com.nan.appstore.utils.CommomUtils;
import com.nan.appstore.utils.StringUtils;
import com.nan.appstore.utils.UIUtils;
import com.nan.appstore.widget.ProgressButton;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadStatus;
import zlc.season.rxdownload2.function.Utils;


/**
 * Created by huannan on 2016/8/25.
 */
public class AppListAdapter extends BaseQuickAdapter<AppBean> {

    private final Context mCtx;

    public AppListAdapter(Context context, int layoutResId, List<AppBean> data) {
        super(layoutResId, data);
        mCtx = context;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final AppBean bean) {

        Picasso.with(mCtx).load(Const.URL_IMAGE_APP + bean.getIconUrl()).into((ImageView) baseViewHolder.getView(R.id.iv_icon));
        baseViewHolder.setText(R.id.tv_title, bean.getName());
        baseViewHolder.setText(R.id.item_bottom, bean.getDes());
        baseViewHolder.setText(R.id.tv_size, StringUtils.formatFileSize(bean.getSize()));

        RatingBar item_rating = baseViewHolder.getView(R.id.rb_rating);
        item_rating.setRating(bean.getStars());

        baseViewHolder.getView(R.id.rl_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, AppDetailActivity.class);
                intent.putExtra(Const.EXTRA_PACKAGE_NAME, bean.getPackageName());
                mCtx.startActivity(intent);
            }
        });


        TextView tv_action = baseViewHolder.getView(R.id.tv_action);
        final ProgressButton btn_download = baseViewHolder.getView(R.id.btn_download);
        final DownloadController controller = new DownloadController(tv_action, btn_download, bean);

        int arcDiameter = UIUtils.dip2px(26, mCtx);
        // 设置圆的直径
        btn_download.setArcDiameter(arcDiameter);
        btn_download.setProgressColor(mCtx.getResources().getColor(R.color.green));

        final RxDownload mRxDownload = RxDownload.getInstance()
                .context(mCtx)
                .autoInstall(true)  // 下载完成自动安装
                .maxDownloadNumber(3);//最大下载数量

//        if (CommomUtils.isAppInstalled(bean.getPackageName())) {
//            tv_action.setText("打开");
//        }

        //设定点击事件以及分发
        baseViewHolder.getView(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (CommomUtils.isAppInstalled(bean.getPackageName())) {
//                    CommomUtils.doStartApplicationWithPackageName(mCtx, bean.getPackageName());
//                } else {
                controller.handleClick(new DownloadController.Callback() {
                    @Override
                    public void startDownload() {
                        start(mRxDownload, bean);
                    }

                    @Override
                    public void pauseDownload() {
                        pause(mRxDownload, bean);
                    }

                    @Override
                    public void cancelDownload() {
                        cancel(mRxDownload, bean);
                    }

                    @Override
                    public void install() {
                        installApk(mRxDownload, bean);
                    }
                });
//                }
            }
        });

        //接收下载事件，并且重新分发事件
        mRxDownload.receiveDownloadStatus(Const.URL_DOWNLOAD + bean.getDownloadUrl())
                .subscribe(new Observer<DownloadEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        /**
                         * important!! 如果有订阅没有取消,则取消订阅!防止ViewHolder复用导致界面显示的BUG!
                         */
                        Utils.dispose((Disposable) baseViewHolder.getView(R.id.tv_title).getTag());
                        baseViewHolder.getView(R.id.tv_title).setTag(d);
                    }

                    @Override
                    public void onNext(DownloadEvent event) {
                        controller.setEvent(event);
                        //更新进度
                        updateProgressStatus(event.getDownloadStatus(), btn_download);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        controller.setState(new DownloadController.Failed());
                    }

                    @Override
                    public void onComplete() {
                        controller.setState(new DownloadController.Completed());
                    }
                });

    }

    private void updateProgressStatus(DownloadStatus status, ProgressButton btn_download) {
        int size = (int) status.getDownloadSize();
        int total = (int) status.getTotalSize();

        Log.e(TAG, "pro size: " + size);
        Log.e(TAG, "pro total: " + total);

        if (total != 0) {
            int progress = (size * 100) / total;

            Log.e(TAG, "pro progress: " + progress);

            float progress1 = (status.getDownloadSize() + 0.0f) / status.getTotalSize();

            btn_download.setProgress(progress1, true);
        }
    }

    private void start(RxDownload rxDownload, AppBean bean) {
        RxPermissions.getInstance(mContext)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            throw new RuntimeException("no permission");
                        }
                    }
                })
                .compose(rxDownload.<Boolean>transformService(Const.URL_DOWNLOAD + bean.getDownloadUrl(), bean.getPackageName() + ".apk", null))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
//                        Toast.makeText(mContext, "下载开始", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void pause(RxDownload rxDownload, AppBean bean) {
        rxDownload.pauseServiceDownload(Const.URL_DOWNLOAD + bean.getDownloadUrl()).subscribe();
    }

    private void cancel(RxDownload rxDownload, AppBean bean) {
        rxDownload.cancelServiceDownload(Const.URL_DOWNLOAD + bean.getDownloadUrl()).subscribe();
    }

    private void installApk(RxDownload rxDownload, AppBean bean) {

        if (CommomUtils.isAppInstalled(bean.getPackageName())) {
            CommomUtils.doStartApplicationWithPackageName(mCtx, bean.getPackageName());
        } else {
            File file = rxDownload.getRealFiles(bean.getPackageName() + ".apk", null)[0];
            if (file != null && file.exists() && file.isFile() && file.length() >= bean.getSize()) {
                Uri uri = Uri.fromFile(file);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                mCtx.startActivity(intent);
            } else {
                //重新下载
                start(rxDownload, bean);
            }
        }
    }

}
