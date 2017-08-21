package com.nan.appstore.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nan.appstore.App;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.adapter.ScreenListAdapter;
import com.nan.appstore.model.DetailBean;
import com.nan.appstore.net.download.DetailDownloadController;
import com.nan.appstore.utils.CommomUtils;
import com.nan.appstore.utils.StringUtils;
import com.nan.appstore.widget.AppToolbar;
import com.nan.appstore.widget.loading.SlackLoadingView;
import com.nan.appstore.widget.swipeback.app.SwipeBackActivity;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xiaochen.progressroundbutton.AnimDownloadProgressButton;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.chensir.expandabletextview.ExpandableTextView;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadStatus;
import zlc.season.rxdownload2.function.Utils;

/**
 * Created by huannan on 2016/11/27.
 */
public class AppDetailActivity extends SwipeBackActivity {

    private static final String TAG = AppDetailActivity.class.getSimpleName();
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rb_rating)
    RatingBar rb_rating;
    @BindView(R.id.tv_download)
    TextView tv_download;
    @BindView(R.id.tv_version)
    TextView tv_version;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_size)
    TextView tv_size;
    @BindView(R.id.loading_view)
    SlackLoadingView loading_view;
    @BindView(R.id.tv_des)
    TextView tv_des;
    @BindView(R.id.tv_app_name)
    TextView tv_app_name;
    @BindView(R.id.ll_loading)
    LinearLayout ll_loading;
    @BindView(R.id.rv_screen)
    RecyclerView rv_screen;
    @BindView(R.id.iv_safe_0)
    ImageView iv_safe0;
    @BindView(R.id.iv_safe_1)
    ImageView iv_safe1;
    @BindView(R.id.iv_safe_2)
    ImageView iv_safe2;
    @BindView(R.id.etv_content)
    ExpandableTextView etv_content;
    @BindView(R.id.toolbar)
    AppToolbar toolbar;
    @BindView(R.id.btn_progress)
    AnimDownloadProgressButton btn_progress;
    @BindView(R.id.iv_fav)
    ImageView iv_fav;
    @BindView(R.id.iv_share)
    ImageView iv_share;

    private DetailDownloadController controller;
    private String mPackageName;
    private DetailBean mDetailBean;
    private ScreenListAdapter mAdapter;
    private RxDownload mRxDownload;
    private Disposable mDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);
        ButterKnife.bind(this);

        init();

    }


    private void init() {

        setTranslucent();

        mPackageName = getIntent().getStringExtra(Const.EXTRA_PACKAGE_NAME);

        initToolbar();
        requestDetailData();

    }

    private void initDownload() {

        // 下载完成自动安装
        //最大下载数量
        mRxDownload = RxDownload.getInstance()
                .context(this)
                .autoInstall(true)  // 下载完成自动安装
                .maxDownloadNumber(3);


        controller = new DetailDownloadController(btn_progress, mDetailBean);

        btn_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分发事件
                controller.handleClick(new DetailDownloadController.Callback() {
                    @Override
                    public void startDownload() {
                        start();
                    }

                    @Override
                    public void pauseDownload() {
                        pause();
                    }

                    @Override
                    public void cancelDownload() {
                        cancel();
                    }

                    @Override
                    public void install() {
                        installApk();
                    }
                });
            }

        });


        //接收下载事件，并且重新分发事件
        mRxDownload.receiveDownloadStatus(Const.URL_DOWNLOAD + mDetailBean.getDownloadUrl())
                .subscribe(new Observer<DownloadEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Utils.dispose(mDisposable);
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(DownloadEvent event) {
                        controller.setEvent(event);
                        //更新进度
                        updateProgressStatus(event.getDownloadStatus());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        controller.setState(new DetailDownloadController.Failed());
                    }

                    @Override
                    public void onComplete() {
                        controller.setState(new DetailDownloadController.Completed());
                    }
                });
    }

    private void initToolbar() {

        toolbar.setOnLeftButtonClickListener(new AppToolbar.OnLeftButtonClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });

    }

    private void requestDetailData() {

        App.getApi().getDetailData(mPackageName)
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
                .doOnNext(new Consumer<DetailBean>() {
                    @Override
                    public void accept(DetailBean detailBean) throws Exception {
                        if (!loading_view.isShowed) {
                            loading_view.stop();
                            ll_loading.setVisibility(View.GONE);
                        }
                    }
                })
                .subscribe(new Consumer<DetailBean>() {
                    @Override
                    public void accept(DetailBean detailBean) throws Exception {
                        mDetailBean = detailBean;
                        initView();
                        initDownload();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });


    }

    @OnClick({R.id.iv_icon, R.id.iv_fav, R.id.iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
                if (mDetailBean != null) {
                    Intent intent = new Intent(this, ImageDetailActivity.class);
                    intent.putExtra(Const.EXTRA_IMAGE_URL, Const.URL_IMAGE_APP + mDetailBean.getIconUrl());
                    startActivity(intent);
                }
                break;

            case R.id.iv_fav:
                if (mDetailBean != null) {
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.iv_share:
                if (mDetailBean != null) {
                    showShare();
                }
                break;

        }
    }


    private void initView() {

        tv_title.setText(mDetailBean.getName());
        tv_app_name.setText(mDetailBean.getName());
        tv_download.setText(mDetailBean.getDownloadNum().replace("+", "") + getString(R.string.title_download));
        tv_date.setText(getString(R.string.title_date) + mDetailBean.getDate());
        tv_version.setText(getString(R.string.title_version) + mDetailBean.getVersion());
        tv_size.setText(StringUtils.formatFileSize(mDetailBean.getSize()));
        rb_rating.setRating(mDetailBean.getStars());
        Picasso.with(this).load(Const.URL_IMAGE_APP + mDetailBean.getIconUrl()).into(iv_icon);
        etv_content.setText(mDetailBean.getDes());

        //安全
        for (int i = 0; i < mDetailBean.getSafe().size(); i++) {
            if (i == 0) {
                Picasso.with(this).load(Const.URL_IMAGE_APP + mDetailBean.getSafe().get(i).getSafeUrl()).into(iv_safe0);
                iv_safe0.setVisibility(View.VISIBLE);
            } else if (i == 1) {
                Picasso.with(this).load(Const.URL_IMAGE_APP + mDetailBean.getSafe().get(i).getSafeUrl()).into(iv_safe1);
                iv_safe1.setVisibility(View.VISIBLE);
            } else if (i == 2) {
                Picasso.with(this).load(Const.URL_IMAGE_APP + mDetailBean.getSafe().get(i).getSafeUrl()).into(iv_safe2);
                iv_safe2.setVisibility(View.VISIBLE);
            }
        }

        initScreen();

    }

    private void initScreen() {

        mAdapter = new ScreenListAdapter(this, R.layout.item_list_screen, mDetailBean.getScreen());

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_screen.setLayoutManager(lm);
        rv_screen.setAdapter(mAdapter);
    }


    private void updateProgressStatus(DownloadStatus status) {
        int size = (int) status.getDownloadSize();
        int total = (int) status.getTotalSize();
        if (total != 0) {
            btn_progress.setMaxProgress(total);
            btn_progress.setProgress(size);
        }
    }

    private void start() {
        RxPermissions.getInstance(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            throw new RuntimeException("no permission");
                        }
                    }
                })
                .compose(mRxDownload.<Boolean>transformService(Const.URL_DOWNLOAD + mDetailBean.getDownloadUrl(), mDetailBean.getPackageName() + ".apk", null))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
//                        Toast.makeText(AppDetailActivity.this, "下载开始", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void pause() {
        mRxDownload.pauseServiceDownload(Const.URL_DOWNLOAD + mDetailBean.getDownloadUrl()).subscribe();
    }

    private void cancel() {
        mRxDownload.cancelServiceDownload(Const.URL_DOWNLOAD + mDetailBean.getDownloadUrl()).subscribe();
    }

    private void installApk() {

        if (CommomUtils.isAppInstalled(mDetailBean.getPackageName())) {
            CommomUtils.doStartApplicationWithPackageName(this, mDetailBean.getPackageName());
        } else {
            File file = mRxDownload.getRealFiles(mDetailBean.getPackageName() + ".apk", null)[0];
            if (file != null && file.exists() && file.isFile() && file.length() >= mDetailBean.getSize()) {
                Uri uri = Uri.fromFile(file);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                startActivity(intent);
            } else {
                //重新下载
                start();
            }
        }
    }


    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("来自AppStore的分享");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我正在通过AppStore使用" + mDetailBean.getName() + "，分享给你吧");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("好好用");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("AppStore");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

}
