package angel.island.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import angel.island.R;
import angel.island.model.Comment;
import angel.island.net.Proxy;
import angel.island.util.FontActionbarActivity;
import angel.island.util.Util;

/**
 * Created by choi on 2015. 2. 2..
 */
public class ReadingActivity extends FontActionbarActivity {
    private String category;

    EditText edit;
    int wid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getIntent().getStringExtra("category");
        setContentView(R.layout.activity_reading);
        setActionBarTitle();
        Intent intent = getIntent();
        wid = intent.getIntExtra("wid", -1);
        int uid = intent.getIntExtra("uid", -1);
        String contents = intent.getStringExtra("contents");
        ((TextView)findViewById(R.id.reading_text_body)).setText(contents);

        edit = (EditText)findViewById(R.id.reading_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            }
        });

        RelativeLayout button = (RelativeLayout)findViewById(R.id.reading_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.hideSoftKeyboard(ReadingActivity.this);
                edit.setText("");
                new Proxy().sendComment(new Comment(wid, edit.getText().toString()));
            }
        });

        //wid -> comment list
        //uid -> level, nick name 가져오기  --> level에 따른 프로필 이미지 보여주기
        //content -> 보여주기
    }

    private void setActionBarTitle() {
        if(category.equals("praise"))
            renameActionBarTitle("자랑 글읽기");
        else
            renameActionBarTitle("위로 글읽기");
    }

}
