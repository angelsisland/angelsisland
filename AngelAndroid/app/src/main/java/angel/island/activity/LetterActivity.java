package angel.island.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import angel.island.R;
import angel.island.adapter.LetterAdapter;
import angel.island.model.Letter;
import angel.island.net.Proxy;
import angel.island.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 1. 26..
 */
public class LetterActivity extends FontActionbarActivity implements AdapterView.OnItemClickListener {

    List<Letter> letters = new ArrayList<Letter>();
    Letter[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);
        query = new AQuery(this);
        query.id(R.id.letter_list).adapter(new LetterAdapter(this, R.layout.row_letter, letters))
                                  .itemClicked(this);
        renameActionBarTitle("천사들의 노래");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                arr = new Proxy().getLetters();
            }
        });
        thread.start();
        try {
            thread.join();
            letters = Arrays.asList(arr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "눌러짐", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LetterDetailActivity.class);
        intent.putExtra("title", letters.get(position).getTitle());
        intent.putExtra("contents", letters.get(position).getContents());
        startActivity(intent);
    }
}
