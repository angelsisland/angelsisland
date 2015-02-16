package angel.island.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquery.AQuery;

import angel.island.R;
import angel.island.activity.MainActivity;

/**
 * Created by choi on 2015. 1. 31..
 */
public class FontActionbarActivity extends ActionBarActivity {

    public AQuery query;
    private View mCustomView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        query = new AQuery(this);

        ActionBar mActionBar = getSupportActionBar();


        LayoutInflater mInflater = LayoutInflater.from(this);
        mCustomView = mInflater.inflate(R.layout.action_bar, null);

        query.id(R.id.actionbar_home_button).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FontActionbarActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setCustomView(mCustomView);

        FontUtil.setFont(mCustomView.findViewById(R.id.actionbar_title_text));

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);
    }

    protected void renameActionBarTitle(String title) {
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.actionbar_title_text);
        mTitleTextView.setText(title);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        FontUtil.setGlobalFont(root);
    }
}
