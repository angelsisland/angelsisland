package amacrazy.com.angel.activity;

import android.os.Bundle;
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
public class ReceivedActivity extends FontActionbarActivity {
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);
        category = getIntent().getStringExtra("category");
        List<Writing> writingList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        ListView listView = (ListView)findViewById(R.id.receiving_list);
        RowAdapter adapter;
        countList.addAll(DummyData.integers);
        if(category.equals("praise")) {
            writingList.addAll(DummyData.dummyPraiseWriting);
            adapter = new RowAdapter(this, R.layout.row_writing, writingList, "mine",countList);
        }
        else {
            writingList.addAll(DummyData.dummyWorryWriting);
            adapter = new RowAdapter(this, R.layout.row_writing, writingList, "mine", countList);
        }
        listView.setAdapter(adapter);

        //Network 으로 받아오기
        List<Integer> commentNum = new ArrayList<>();

        setLogo();
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
}
