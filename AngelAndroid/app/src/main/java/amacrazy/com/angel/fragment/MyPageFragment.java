package amacrazy.com.angel.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import amacrazy.com.angel.R;
import amacrazy.com.angel.activity.ReceivedComfortActivity;
import amacrazy.com.angel.activity.ReceivedPraiseActivity;

/**
 * Created by choi on 2015. 1. 23..
 */
public class MyPageFragment extends Fragment implements View.OnClickListener{

    Button button1;
    Button button2;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_page, null);
        context = getActivity().getApplicationContext();
        button1 = (Button)v.findViewById(R.id.my_page_received_praise);
        button2 = (Button)v.findViewById(R.id.my_page_received_comfort);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch(viewId) {
            case R.id.my_page_received_praise:
                Intent intent = new Intent(context, ReceivedPraiseActivity.class);
                startActivity(intent);
                break;
            case R.id.my_page_received_comfort:
                Intent intent2 = new Intent(context, ReceivedComfortActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
