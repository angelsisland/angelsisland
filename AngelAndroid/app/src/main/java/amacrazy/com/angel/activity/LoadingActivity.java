package amacrazy.com.angel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import amacrazy.com.angel.R;

/**
 * Created by choi on 2015. 1. 22..
 */
public class LoadingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }

    private void startLoading() {
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
            }
        }.sendEmptyMessageDelayed(0, 3000);
    }
}
