package com.nan.appstore.net.download;

import com.nan.appstore.R;
import com.nan.appstore.model.AppBean;
import com.nan.appstore.model.DetailBean;
import com.nan.appstore.widget.ProgressButton;
import com.xiaochen.progressroundbutton.AnimDownloadProgressButton;

import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/11/22
 * Time: 15:18
 * FIXME
 */
public class DetailDownloadController {
    private AnimDownloadProgressButton mAction;
    private DownloadState mState;
    private DetailBean mDetailBean;

    public DetailDownloadController(AnimDownloadProgressButton action, DetailBean bean) {
        mAction = action;
        mDetailBean = bean;
        setState(new Normal());
    }

    public void setState(DownloadState state) {
        mState = state;
        mState.setText(mAction);
    }

    public void setEvent(DownloadEvent event) {
        int flag = event.getFlag();
        switch (flag) {
            case DownloadFlag.NORMAL:
                setState(new DetailDownloadController.Normal());
                break;
            case DownloadFlag.WAITING:
                setState(new DetailDownloadController.Waiting());
                break;
            case DownloadFlag.STARTED:
                setState(new DetailDownloadController.Started());
                break;
            case DownloadFlag.PAUSED:
                setState(new DetailDownloadController.Paused());
                break;
            case DownloadFlag.CANCELED:
                setState(new DetailDownloadController.Canceled());
                break;
            case DownloadFlag.COMPLETED:
                setState(new DetailDownloadController.Completed());
                break;
            case DownloadFlag.FAILED:
                setState(new DetailDownloadController.Failed());
                break;
            case DownloadFlag.DELETED:
                setState(new DetailDownloadController.Deleted());
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

        abstract void setText(AnimDownloadProgressButton button);

        abstract void handleClick(Callback callback);
    }

    public static class Normal extends DownloadState {

        @Override
        void setText(AnimDownloadProgressButton button) {
            button.setState(AnimDownloadProgressButton.NORMAL);
            button.setCurrentText("下载");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Waiting extends DownloadState {
        @Override
        void setText(AnimDownloadProgressButton button) {
            button.setState(AnimDownloadProgressButton.INSTALLING);
            button.setCurrentText("等待中");
        }

        @Override
        void handleClick(Callback callback) {
            callback.cancelDownload();
        }
    }

    public static class Started extends DownloadState {
        @Override
        void setText(AnimDownloadProgressButton button) {
            button.setState(AnimDownloadProgressButton.DOWNLOADING);
            button.setCurrentText("下载中");
        }

        @Override
        void handleClick(Callback callback) {
            callback.pauseDownload();
        }
    }

    public static class Paused extends DownloadState {
        @Override
        void setText(AnimDownloadProgressButton button) {
            button.setState(AnimDownloadProgressButton.DOWNLOADING);
            button.setCurrentText("已暂停");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Failed extends DownloadState {
        @Override
        void setText(AnimDownloadProgressButton button) {
            button.setState(AnimDownloadProgressButton.NORMAL);
            button.setCurrentText("下载失败");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Canceled extends DownloadState {
        @Override
        void setText(AnimDownloadProgressButton button) {
            button.setState(AnimDownloadProgressButton.NORMAL);
            button.setCurrentText("已取消");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Completed extends DownloadState {
        @Override
        void setText(AnimDownloadProgressButton button) {
            button.setState(AnimDownloadProgressButton.NORMAL);
            button.setCurrentText("点击安装");
        }

        @Override
        void handleClick(Callback callback) {
            callback.install();
        }
    }

    public static class Deleted extends DownloadState {

        @Override
        void setText(AnimDownloadProgressButton button) {
            button.setState(AnimDownloadProgressButton.NORMAL);
            button.setCurrentText("已取消");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }
}
