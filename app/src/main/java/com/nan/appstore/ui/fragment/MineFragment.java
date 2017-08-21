package com.nan.appstore.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nan.appstore.App;
import com.nan.appstore.Const;
import com.nan.appstore.R;
import com.nan.appstore.model.UserBean;
import com.nan.appstore.ui.fragment.base.BaseFragment;
import com.nan.appstore.utils.PreferenceUtils;
import com.nan.appstore.widget.AppToolbar;
import com.nan.appstore.widget.RoundImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huannan on 2016/11/26.
 */

public class MineFragment extends BaseFragment {

    public static final String TAG = MineFragment.class.getSimpleName();

    @BindView(R.id.toolbar)
    AppToolbar toolbar;
    @BindView(R.id.iv_blur)
    ImageView iv_blur;
    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;
    @BindView(R.id.iv_gender)
    ImageView iv_gender;
    @BindView(R.id.tv_school)
    TextView tv_school;
    @BindView(R.id.tv_name)
    TextView tv_name;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void initToolbar() {
        toolbar.setOnRightButtonClickListener(new AppToolbar.OnRightButtonClickListener() {
            @Override
            public void onClick() {
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.input_ip)
                        .content(R.string.input_content_ip)
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input(getString(R.string.input_hint_ip), PreferenceUtils.getInstance(getActivity()).getServerIP(), new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence sequence) {
                                //保存并且重新设置IP地址
                                String ip = sequence.toString();
                                PreferenceUtils.getInstance(getActivity()).setServerIP(ip);
                                App.initRetrofit(ip);
                            }
                        }).show();
            }
        });
    }

    private void init() {
        ButterKnife.bind(this, mRootView);
        initToolbar();
        requestUserData();
    }

    private void requestUserData() {

        App.getApi().getUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean user) throws Exception {
                        showData(user);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

    }

    private void showData(UserBean user) {

        Picasso.with(getActivity()).load(Const.URL_IMAGE + user.getAvatar()).into(iv_avatar);
        Picasso.with(getActivity()).load(Const.URL_IMAGE + user.getAvatarBlur()).into(iv_blur);

        tv_school.setText(user.getSchool());
        tv_name.setText(user.getName());

        if (user.getSex().equals("男")) {
            iv_gender.setImageResource(R.drawable.icon_boy);
        } else {
            iv_gender.setImageResource(R.drawable.icon_girl);
        }

    }


    @OnClick({R.id.rl_update, R.id.rl_about, R.id.rl_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_update:
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.updates)
                        .content(R.string.update_info)
                        .positiveText(R.string.positive_button)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        }).show();

                break;
            case R.id.rl_about:
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.about)
                        .content(getString(R.string.about_msg_1) + "\n" + getString(R.string.about_msg_2) + "\n" + getString(R.string.about_msg_3))
                        .positiveText(R.string.positive_button)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        }).show();
                break;
            case R.id.rl_exit:

                new MaterialDialog.Builder(getActivity())
                        .title(R.string.exit)
                        .content(R.string.exit_msg)
                        .positiveText(R.string.positive_button)
                        .negativeText(R.string.negative_button)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                System.exit(0);
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

                break;
        }
    }
}

