package amacrazy.com.angel.util;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by choi on 2015. 1. 31..
 */
public class FontActivity extends Activity {

    private Typeface mTypeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        mTypeface = Typeface.createFromAsset(getAssets(), "NanumGothicExtraBold.ttf.mp3");
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        setGlobalFont(root);
    }

    void setGlobalFont(ViewGroup root) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView) {
                ((TextView) child).setTypeface(mTypeface);
            }
            else if (child instanceof ViewGroup)
                setGlobalFont((ViewGroup)child);
        }
    }
}
