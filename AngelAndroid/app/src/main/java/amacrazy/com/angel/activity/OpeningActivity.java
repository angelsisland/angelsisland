package amacrazy.com.angel.activity;

import android.content.Intent;
import android.os.Bundle;

import amacrazy.com.angel.R;
import amacrazy.com.angel.dialog.OpeningDialog;
import amacrazy.com.angel.service.AngelBackgroundSong;
import amacrazy.com.angel.util.FontActivity;

/**
 * Created by choi on 2015. 2. 1..
 */
public class OpeningActivity extends FontActivity {

    public static Intent service;

    OpeningDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        startService(new Intent(this, AngelBackgroundSong.class));
        dialog = new OpeningDialog(this);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.white_box));
        dialog.setOwnerActivity(this);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(dialog != null) {
            dialog.dismiss();
        }
    }
}
