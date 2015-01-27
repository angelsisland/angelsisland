package amacrazy.com.angel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import amacrazy.com.angel.R;
import amacrazy.com.angel.adapter.WritingAdapter;
import amacrazy.com.angel.model.Writing;

/**
 * Created by choi on 2015. 1. 26..
 */
public class BoardPraiseActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private Button button1;
    private Button button2;
    ListView listView;
    WritingAdapter adapter;
    List<Writing> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        button1 = (Button)findViewById(R.id.board_writing);
        button2 = (Button)findViewById(R.id.board_received_praise);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        listView = (ListView)findViewById(R.id.board_list);
        list = new ArrayList<Writing>();
        adapter = new WritingAdapter(this, R.layout.list_praise, list);
        adapter.add(new Writing("praise", "오늘 착한 일!!dummy", "dummy data", null));
        adapter.add(new Writing("praise", "오늘 착한 일 둘 째 !!dummy", "dummy data", null));
        adapter.add(new Writing("praise", "오늘 착한 일 세엣 째!!dummy", "dummy data", null));
        adapter.add(new Writing("praise", "오늘 착한 일 넷 째애!!dummy", "dummy data", null));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch(viewId) {
            case R.id.board_writing:
                Intent intent = new Intent(getApplicationContext(), WritingPraiseActivity.class);
                startActivity(intent);
                break;
            case R.id.board_received_praise:
                Intent intent2 = new Intent(getApplicationContext(), ReceivedPraiseActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
