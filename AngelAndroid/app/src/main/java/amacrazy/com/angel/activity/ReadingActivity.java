package amacrazy.com.angel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import amacrazy.com.angel.R;
import amacrazy.com.angel.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 2. 2..
 */
public class ReadingActivity extends FontActionbarActivity {
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getIntent().getStringExtra("category");

        setContentView(R.layout.activity_reading);
        setActionBarTitle();
        Intent intent = getIntent();
        int wid = intent.getIntExtra("wid", -1);
        int uid = intent.getIntExtra("uid", -1);
        String contents = intent.getStringExtra("contents");

        //wid -> comment list
        //uid -> level, nick name 가져오기  --> level에 따른 프로필 이미지 보여주기
        //content -> 보여주기

        ((TextView)findViewById(R.id.reading_text_body)).setText(contents);
    }

    private void setActionBarTitle() {
        if(category.equals("praise"))
            getSupportActionBar().setTitle("자랑 글읽기");
        else
            getSupportActionBar().setTitle("위로 글읽기");
    }
}
