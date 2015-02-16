package angel.island.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.androidquery.AQuery;

import java.util.Arrays;
import java.util.List;

import angel.island.R;
import angel.island.adapter.RowAdapter;
import angel.island.model.Writing;
import angel.island.net.Proxy;
import angel.island.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 1. 26..
 */
public class BoardActivity extends FontActionbarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    String category;

    private List<Writing> list;
    Writing[] arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        query = new AQuery(this);

        String logo;
        if (category.equals("praise"))
            logo = "당신의 칭찬을 기다립니다";
        else
            logo = "당신의 손길을 기다립니다";

        RowAdapter adapter = new RowAdapter(this, R.layout.row_writing, list, "writing");

        query.id(R.id.letter_detail_title).text(logo);
        query.id(R.id.board_write).clicked(this);
        query.id(R.id.board_list).adapter(adapter)
                .itemClicked(this);

        if (category.equals("praise"))
            arr = new Proxy().getPraiseNeed();
        else
            arr = new Proxy().getSolaceNeed();
        list = Arrays.asList(arr);
        setTitle();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = new Intent();
        Class clas = null;
        switch (viewId) {
            case R.id.board_write:
                clas = WritingActivity.class;
                break;
        }
        intent.putExtra("category", category);
        intent.setClass(this, clas);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ReadingActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("wid", list.get(position).getId());
        intent.putExtra("contents", list.get(position).getContents());
        startActivity(intent);
    }

    private void setTitle() {
        if (category.equals("praise"))
            renameActionBarTitle("천사들의 이야기");
        else
            renameActionBarTitle("천사들의 손길");
    }
}
