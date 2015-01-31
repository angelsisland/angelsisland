package amacrazy.com.angel.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import amacrazy.com.angel.R;

/**
 * Created by choi on 2015. 1. 25..
 */
public class WritingWorryActivity extends WritingPraiseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imgView = (ImageView)findViewById(R.id.writing_praise_profile_image);
        imgView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cheerup_icon));
    }
}
