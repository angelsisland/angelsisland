package angel.island.activity;

import android.os.Bundle;

import com.androidquery.AQuery;

import angel.island.R;
import angel.island.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 2. 3..
 */
public class LetterDetailActivity extends FontActionbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_detail);

        query = new AQuery(this);
        String title = getIntent().getStringExtra("title");
        String contents = getIntent().getStringExtra("contents");
        query.id(R.id.letter_detail_title).text(title);
        query.id(R.id.letter_contents).text(contents);
        renameActionBarTitle("천사들의 노래");
    }
}
