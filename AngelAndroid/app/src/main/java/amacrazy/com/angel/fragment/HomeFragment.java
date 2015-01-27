package amacrazy.com.angel.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import amacrazy.com.angel.activity.BoardPraiseActivity;
import amacrazy.com.angel.R;
import amacrazy.com.angel.activity.BoardWorryActivity;
import amacrazy.com.angel.activity.WarmLetterActivity;

/**
 * Created by choi on 2015. 1. 23..
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    ImageView imgView1;
    ImageView imgView2;
    ImageView imgView3;
    Context context;

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch(viewId) {
            case R.id.main_praise1:
                Intent intent = new Intent(context, BoardPraiseActivity.class);
                startActivity(intent);
                break;
            case R.id.main_praise2:
                Intent intent2 = new Intent(context, BoardWorryActivity.class);
                startActivity(intent2);
                break;
            case R.id.main_praise3:
                Intent intent3 = new Intent(context, WarmLetterActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        imgView1 = (ImageView)v.findViewById(R.id.main_praise1);
        imgView2 = (ImageView)v.findViewById(R.id.main_praise2);
        imgView3 = (ImageView)v.findViewById(R.id.main_praise3);
        imgView1.setOnClickListener(this);
        imgView2.setOnClickListener(this);
        imgView3.setOnClickListener(this);
        context = getActivity().getApplicationContext();
        return v;
    }
}
