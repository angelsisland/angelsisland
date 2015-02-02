package amacrazy.com.angel.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import amacrazy.com.angel.R;
import amacrazy.com.angel.activity.BoardActivity;
import amacrazy.com.angel.activity.LetterActivity;
import amacrazy.com.angel.util.FontUtil;

/**
 * Created by choi on 2015. 1. 23..
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    ImageView imgView1;
    ImageView imgView2;
    ImageView imgView3;
    Context context;

    ImageView waveView1;
    ImageView waveView2;

    ImageView cloudLeft;
    ImageView cloudRight;
    ImageView cloudMiddle;


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Class activityClass = null;
        Intent intent = new Intent();
        switch(viewId) {
            case R.id.main_praise1:
                activityClass = BoardActivity.class;
                intent.putExtra("category", "praise");
                break;
            case R.id.main_praise2:
                activityClass = BoardActivity.class;
                intent.putExtra("category", "worry");
                break;
            case R.id.main_praise3:
                activityClass = LetterActivity.class;
                break;
        }
        intent.setClass(context, activityClass);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        FontUtil.setGlobalFont((ViewGroup)v);
        imgView1 = (ImageView)v.findViewById(R.id.main_praise1);
        imgView2 = (ImageView)v.findViewById(R.id.main_praise2);
        imgView3 = (ImageView)v.findViewById(R.id.main_praise3);
        imgView1.setOnClickListener(this);
        imgView2.setOnClickListener(this);
        imgView3.setOnClickListener(this);
        context = getActivity().getApplicationContext();

        waveView1 = (ImageView)v.findViewById(R.id.white_wave);
        waveView2 = (ImageView)v.findViewById(R.id.white_wave2);
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        anim.setDuration(20000);
        anim.setRepeatCount(-1);
        waveView1.startAnimation(anim);
        waveView2.startAnimation(anim);

        Animation animL = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_SELF, 2.45f,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        Animation animM = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_PARENT, -0.6f,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        Animation animR = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_PARENT, -0.55f,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);

        animL.setDuration(20000);
        animL.setRepeatCount(-1);

        animM.setDuration(20000);
        animM.setRepeatCount(-1);

        animR.setDuration(20000);
        animR.setRepeatCount(-1);

        cloudLeft = (ImageView)v.findViewById(R.id.cloud_left);
        cloudMiddle = (ImageView)v.findViewById(R.id.cloud_middle);
        cloudRight = (ImageView)v.findViewById(R.id.cloud_right);
        cloudLeft.startAnimation(animL);
        cloudRight.startAnimation(animR);
        cloudMiddle.startAnimation(animM);

        return v;
    }
}
