package amacrazy.com.angel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import amacrazy.com.angel.R;
import amacrazy.com.angel.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 2. 2..
 */
public class SelectWritingActivity extends FontActionbarActivity implements View.OnClickListener{

    ImageView selectIcon1;
    ImageView selectIcon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_writing);
        selectIcon1 = (ImageView)findViewById(R.id.select_praise_img);
        selectIcon2 = (ImageView)findViewById(R.id.select_proud_img);
        selectIcon1.setOnClickListener(this);
        selectIcon2.setOnClickListener(this);
        getSupportActionBar().setTitle("글쓰기 선택");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.navigation_back));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = new Intent(this, WritingActivity.class);
        switch(viewId) {
            case R.id.select_praise_img:
                intent.putExtra("category", "praise");
                break;
            case R.id.select_proud_img:
                intent.putExtra("category", "worry");
                break;
        }
        startActivity(intent);
    }
}
