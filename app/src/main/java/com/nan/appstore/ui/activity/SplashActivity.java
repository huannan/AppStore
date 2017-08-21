package com.nan.appstore.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nan.appstore.R;
import com.nan.appstore.ui.activity.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }


}
