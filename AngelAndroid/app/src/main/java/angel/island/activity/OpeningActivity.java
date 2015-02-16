package angel.island.activity;

import android.content.Intent;
import android.os.Bundle;

import angel.island.R;
import angel.island.dialog.OpeningDialog;
import angel.island.service.AngelBackgroundSong;
import angel.island.util.FontActivity;

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
