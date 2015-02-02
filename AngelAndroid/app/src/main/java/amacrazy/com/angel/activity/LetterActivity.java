package amacrazy.com.angel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import amacrazy.com.angel.R;
import amacrazy.com.angel.adapter.LetterAdapter;
import amacrazy.com.angel.util.DummyData;
import amacrazy.com.angel.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 1. 26..
 */
public class LetterActivity extends FontActionbarActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);
        ListView listView = (ListView)findViewById(R.id.letter_list);
        listView.setAdapter(new LetterAdapter(this, R.layout.row_letter, DummyData.letters));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "눌러짐", Toast.LENGTH_SHORT).show();
    }
}
