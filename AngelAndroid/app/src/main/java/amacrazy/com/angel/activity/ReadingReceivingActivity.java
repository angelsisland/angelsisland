package amacrazy.com.angel.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import amacrazy.com.angel.R;
import amacrazy.com.angel.adapter.CommentAdapter;
import amacrazy.com.angel.model.Comment;
import amacrazy.com.angel.util.DummyData;
import amacrazy.com.angel.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 2. 2..
 */
public class ReadingReceivingActivity extends FontActionbarActivity {

    private String category;
    int wid;
    String contents;

    ListView commentListView;
    List<Comment> commentList = new ArrayList<Comment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_reading);
        category = getIntent().getStringExtra("category");
        wid = getIntent().getIntExtra("wid", -1);
        contents = getIntent().getStringExtra("contents");
        ((TextView)findViewById(R.id.receiving_reading_contents)).setText(contents);
        setActionBarTitle();

        commentList = DummyData.comments;
        commentListView = (ListView)findViewById(R.id.comment_list);
        commentListView.setAdapter(new CommentAdapter(this, R.layout.row_comment, commentList));
    }

    private void setActionBarTitle() {
        if(category.equals("praise"))
            getSupportActionBar().setTitle("내가 받은 칭찬들");
        else
            getSupportActionBar().setTitle("내가 받은 위로들");
    }
}
