package amacrazy.com.angel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import amacrazy.com.angel.R;
import amacrazy.com.angel.adapter.RowAdapter;
import amacrazy.com.angel.model.Writing;
import amacrazy.com.angel.util.DummyData;
import amacrazy.com.angel.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 1. 26..
 */
public class ReceivedActivity extends FontActionbarActivity implements AdapterView.OnItemClickListener{
    private String category;
    List<Writing> list;
    List<Integer> clist;
    ListView listView;
    RowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);
        category = getIntent().getStringExtra("category");
        list = new ArrayList<>();
        clist = new ArrayList<>();
        listView = (ListView)findViewById(R.id.receiving_list);
        listView.setOnItemClickListener(this);
        clist.addAll(DummyData.integers);
        if(category.equals("praise")) {
            list.addAll(DummyData.dummyPraiseWriting);
            adapter = new RowAdapter(this, R.layout.row_writing, list, "mine",clist);
        }
        else {
            list.addAll(DummyData.dummyWorryWriting);
            adapter = new RowAdapter(this, R.layout.row_writing, list, "mine", clist);
        }
        listView.setAdapter(adapter);

        //Network 으로 받아오기, writing, integer 리스트 받아오기
        List<Integer> commentNum = new ArrayList<>();

        setLogo();
        setActionBarTitle();
    }

    private void setLogo() {
        String praise = "내가 제일 잘 나가";
        String worry = "수고했어 오늘도!";

        TextView logo = (TextView)findViewById(R.id.receiving_logo);

        if(category.equals("praise"))
            logo.setText(praise);
        else
            logo.setText(worry);
    }

    private void setActionBarTitle() {
        if(category.equals("praise"))
            getSupportActionBar().setTitle("내가 받은 칭찬들");
        else
            getSupportActionBar().setTitle("내가 받은 위로들");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ReadingReceivingActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("contents", list.get(position).getContents());
        intent.putExtra("wid", list.get(position).getWid());
        startActivity(intent);
    }
}
