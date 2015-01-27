package amacrazy.com.angel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import amacrazy.com.angel.R;

/**
 * Created by choi on 2015. 1. 26..
 */
public class BoardWorryActivity extends ActionBarActivity implements View.OnClickListener{
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        button1 = (Button)findViewById(R.id.board_writing);
        button2 = (Button)findViewById(R.id.board_received_praise);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
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
}
