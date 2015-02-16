package angel.island.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import angel.island.R;
import angel.island.adapter.RowAdapter;
import angel.island.model.Writing;
import angel.island.net.Proxy;
import angel.island.util.DummyData;
import angel.island.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 1. 26..
 */
public class ReceivedActivity extends FontActionbarActivity implements AdapterView.OnItemClickListener {
    private String category;
    List<Writing> list = new ArrayList<Writing>();
    ListView listView;
    RowAdapter adapter;
    Writing[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);
        category = getIntent().getStringExtra("category");

        if (category.equals("praise"))
            arr = new Proxy().getMyPostList("praise");
        else
            arr = new Proxy().getMyPostList("solace");

        list = Arrays.asList(arr);

        listView = (ListView) findViewById(R.id.receiving_list);
        listView.setOnItemClickListener(this);
        if (category.equals("praise")) {
            list.addAll(DummyData.dummyPraiseWriting);
            adapter = new RowAdapter(this, R.layout.row_writing, list, "mine");
        } else {
            list.addAll(DummyData.dummyWorryWriting);
            adapter = new RowAdapter(this, R.layout.row_writing, list, "mine");
        }
        listView.setAdapter(adapter);

        setLogo();
        setActionBarTitle();
    }

    private void setLogo() {
        String praise = "내가 제일 잘 나가";
        String worry = "수고했어 오늘도!";

        TextView logo = (TextView) findViewById(R.id.receiving_logo);

        if (category.equals("praise"))
            logo.setText(praise);
        else
            logo.setText(worry);
    }

    private void setActionBarTitle() {
        if (category.equals("praise"))
            renameActionBarTitle("내가 받은 칭찬들");
        else
            renameActionBarTitle("내가 받은 위로들");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ReadingReceivingActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("contents", list.get(position).getContents());
        intent.putExtra("id", list.get(position).getId());
        startActivity(intent);
    }
}
