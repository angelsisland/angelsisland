package amacrazy.com.angel.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import amacrazy.com.angel.R;
import amacrazy.com.angel.model.Writing;
import amacrazy.com.angel.net.HttpHelper;

/**
 * Created by choi on 2015. 1. 25..
 */
public class WritingWorryActivity extends ActionBarActivity implements View.OnClickListener{

    Button button1;
    Button button2;

    EditText titleEdit;
    EditText bodyEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_worry);
        button1 = (Button)findViewById(R.id.writing_worry_add_picture);
        button2 = (Button)findViewById(R.id.writing_worry_commit);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        titleEdit = (EditText)findViewById(R.id.writing_worry_title_edit);
        bodyEdit = (EditText)findViewById(R.id.writing_worry_body_edit);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.writing_worry_add_picture:
                Toast.makeText(getApplicationContext(), "사진 개발 중", Toast.LENGTH_SHORT).show();
                break;
            case R.id.writing_worry_commit:
                Toast.makeText(getApplicationContext(), "오늘도 수고했어^^", Toast.LENGTH_SHORT).show();
                //commit();
                finish();
                break;
        }
    }

    private void commit() {
        String category = "praise";
        String title = titleEdit.getText().toString();
        String contents = bodyEdit.getText().toString();
        String photo = null;
        Writing writing = new Writing(category, title, contents, photo);
        new HttpHelper().connect("/api/write", "POST", writing, null);
    }
}
