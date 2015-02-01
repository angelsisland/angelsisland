package amacrazy.com.angel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import amacrazy.com.angel.R;
import amacrazy.com.angel.adapter.RowAdapter;
import amacrazy.com.angel.model.Writing;
import amacrazy.com.angel.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 1. 26..
 */
public class BoardActivity extends FontActionbarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private ImageView button1;
    private ImageView button2;
    private String category;

    ListView listView;
    RowAdapter adapter;
    List<Writing> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        category = getIntent().getStringExtra("category");

        String logo;
        if(category.equals("praise"))
            logo = "당신의 칭찬을 기다립니다";
        else
            logo = "당신의 손길을 기다립니다";

        ((TextView)findViewById(R.id.board_logo)).setText(logo);
        button1 = (ImageView)findViewById(R.id.board_write);
        button2 = (ImageView)findViewById(R.id.board_received);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        listView = (ListView)findViewById(R.id.board_list);
        list = new ArrayList<Writing>();
        adapter = new RowAdapter(this, R.layout.row_writing, list, "writing", null);
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
        Intent intent = new Intent();
        Class clas = null;
        switch(viewId) {
            case R.id.board_write:
                clas = WritingActivity.class;
                break;
            case R.id.board_received:
                clas = ReceivedActivity.class;
                intent.putExtra("category", category);
                break;
        }
        intent.setClass(this, clas);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
