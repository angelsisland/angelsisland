package amacrazy.com.angel.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import amacrazy.com.angel.R;
import amacrazy.com.angel.activity.WritingPraiseActivity;
import amacrazy.com.angel.activity.WritingWorryActivity;

/**
 * Created by choi on 2015. 1. 23..
 */
public class SelectWritingFragment extends Fragment implements View.OnClickListener{

    private Typeface mTypeface;
    ImageView selectIcon1;
    ImageView selectIcon2;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_writing, null);
        context = getActivity().getApplicationContext();
        selectIcon1 = (ImageView)v.findViewById(R.id.select_praise_img);
        selectIcon2 = (ImageView)v.findViewById(R.id.select_proud_img);
        selectIcon1.setOnClickListener(this);
        selectIcon2.setOnClickListener(this);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "NanumGothicExtraBold.ttf.mp3");
        TextView textView1 = (TextView)v.findViewById(R.id.select_praise_guide);
        TextView textView2 = (TextView)v.findViewById(R.id.select_proud_guide);
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
        return v;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch(viewId) {
            case R.id.select_praise_img:
                Intent intent = new Intent(context, WritingPraiseActivity.class);
                startActivity(intent);
                break;
            case R.id.select_proud_img:
                Intent intent2 = new Intent(context, WritingWorryActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
