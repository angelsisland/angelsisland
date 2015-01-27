package amacrazy.com.angel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import amacrazy.com.angel.R;
import amacrazy.com.angel.model.Comment;
import amacrazy.com.angel.net.HttpHelper;

/**
 * Created by choi on 2015. 1. 26..
 */
public class CommentActivity extends Activity implements View.OnClickListener{
    EditText commentEdit;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        commentEdit = (EditText)findViewById(R.id.comment_edit);
        button = (Button)findViewById(R.id.comment_add);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch(viewId) {
            case R.id.comment_add:
                int wid = 1;
                //wid 를 그 bundle에서 가져와 주는 작업이 필요하다.
                Comment comment = new Comment(wid, commentEdit.getText().toString());
                new HttpHelper().connect("/api/comment", "POST", comment, null);
                break;
        }
    }
}
