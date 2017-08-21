package com.nan.appstore.net.download;

import android.widget.TextView;

import com.nan.appstore.R;
import com.nan.appstore.model.AppBean;
import com.nan.appstore.utils.CommomUtils;
import com.nan.appstore.widget.ProgressButton;

import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/11/22
 * Time: 15:18
 * FIXME
 */
public class DownloadController {
    private TextView mStatus;
    private ProgressButton mAction;
    private DownloadState mState;
    private AppBean mAppBean;

    public DownloadController(TextView status, ProgressButton action, AppBean bean) {
        mStatus = status;
        mAction = action;
        mAppBean = bean;

        status.setTag(mAppBean);

        setState(new Normal());
    }

    public void setState(DownloadState state) {
        mState = state;
        mState.setText(mStatus, mAction);
    }

    public void setEvent(DownloadEvent event) {
        int flag = event.getFlag();
        switch (flag) {
            case DownloadFlag.NORMAL:
                setState(new DownloadController.Normal());
                break;
            case DownloadFlag.WAITING:
                setState(new DownloadController.Waiting());
                break;
            case DownloadFlag.STARTED:
                setState(new DownloadController.Started());
                break;
            case DownloadFlag.PAUSED:
                setState(new DownloadController.Paused());
                break;
            case DownloadFlag.CANCELED:
                setState(new DownloadController.Canceled());
                break;
            case DownloadFlag.COMPLETED:
                setState(new DownloadController.Completed());
                break;
            case DownloadFlag.FAILED:
                setState(new DownloadController.Failed());
                break;
            case DownloadFlag.DELETED:
                setState(new DownloadController.Deleted());
                break;
        }
    }

    public void handleClick(Callback callback) {
        mState.handleClick(callback);
    }

    public interface Callback {
        void startDownload();

        void pauseDownload();

        void cancelDownload();

        void install();
    }

    static abstract class DownloadState {

        abstract void setText(TextView status, ProgressButton button);

        abstract void handleClick(Callback callback);
    }

    public static class Normal extends DownloadState {

        @Override
        void setText(TextView status, ProgressButton button) {
            button.seForegroundResource(R.drawable.ic_download);
            button.setStyle(ProgressButton.PROGRESS_STYLE_NO_PROGRESS);
            status.setText("下载");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Waiting extends DownloadState {
        @Override
        void setText(TextView status, ProgressButton button) {
            button.seForegroundResource(R.drawable.ic_pause);
            button.setStyle(ProgressButton.PROGRESS_STYLE_WAITING);
            status.setText("等待中...");
        }

        @Override
        void handleClick(Callback callback) {
            callback.cancelDownload();
        }
    }

    public static class Started extends DownloadState {
        @Override
        void setText(TextView status, ProgressButton button) {
            button.seForegroundResource(R.drawable.ic_pause);
            button.setStyle(ProgressButton.PROGRESS_STYLE_DOWNLOADING);
            status.setText("下载中...");
        }

        @Override
        void handleClick(Callback callback) {
            callback.pauseDownload();
        }
    }

    public static class Paused extends DownloadState {
        @Override
        void setText(TextView status, ProgressButton button) {
            button.seForegroundResource(R.drawable.ic_resume);
            button.setStyle(ProgressButton.PROGRESS_STYLE_NO_PROGRESS);
            status.setText("已暂停");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Failed extends DownloadState {
        @Override
        void setText(TextView status, ProgressButton button) {
            button.seForegroundResource(R.drawable.ic_redownload);
            button.setStyle(ProgressButton.PROGRESS_STYLE_NO_PROGRESS);
            status.setText("下载失败");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Canceled extends DownloadState {
        @Override
        void setText(TextView status, ProgressButton button) {
            button.seForegroundResource(R.drawable.ic_download);
            button.setStyle(ProgressButton.PROGRESS_STYLE_NO_PROGRESS);
            status.setText("已取消");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Completed extends DownloadState {
        @Override
        void setText(TextView status, ProgressButton button) {
            button.seForegroundResource(R.drawable.ic_install);
            button.setStyle(ProgressButton.PROGRESS_STYLE_NO_PROGRESS);
            status.setText("点击安装");

            AppBean bean = (AppBean) status.getTag();

//            if (CommomUtils.isAppInstalled(bean.getPackageName())) {
//                status.setText("打开");
//            } else {
//                status.setText("点击安装");
//            }
        }

        @Override
        void handleClick(Callback callback) {
            callback.install();
        }
    }

    public static class Deleted extends DownloadState {

        @Override
        void setText(TextView status, ProgressButton button) {
            button.seForegroundResource(R.drawable.ic_download);
            button.setStyle(ProgressButton.PROGRESS_STYLE_NO_PROGRESS);
            status.setText("下载已取消");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }
}
