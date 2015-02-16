package angel.island.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import angel.island.R;
import angel.island.adapter.CommentAdapter;
import angel.island.model.Comment;
import angel.island.net.Proxy;
import angel.island.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 2. 2..
 */
public class ReadingReceivingActivity extends FontActionbarActivity {

    private String category;
    int wid;
    String contents;

    ListView commentListView;
    List<Comment> commentList = new ArrayList<Comment>();
    Comment[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_reading);
        category = getIntent().getStringExtra("category");
        wid = getIntent().getIntExtra("id", -1);
        contents = getIntent().getStringExtra("contents");
        ((TextView) findViewById(R.id.receiving_reading_contents)).setText(contents);
        setActionBarTitle();

        arr = new Proxy().getComments(wid);
        commentList = Arrays.asList(arr);

        commentListView = (ListView) findViewById(R.id.comment_list);
        commentListView.setAdapter(new CommentAdapter(this, R.layout.row_comment, commentList));
    }

    private void setActionBarTitle() {
        if (category.equals("praise"))
            renameActionBarTitle("내가 받은 칭찬들");
        else
            renameActionBarTitle("내가 받은 위로들");
    }
}
