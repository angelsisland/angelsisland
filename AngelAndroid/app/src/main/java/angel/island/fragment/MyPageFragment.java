package angel.island.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import angel.island.R;
import angel.island.activity.LevelActivity;
import angel.island.activity.ReceivedActivity;
import angel.island.util.FontUtil;

/**
 * Created by choi on 2015. 1. 23..
 */
public class MyPageFragment extends Fragment implements View.OnClickListener {

    Button button1;
    Button button2;
    Context context;

    ImageView waveView1;
    ImageView waveView2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_page, null);
        FontUtil.setGlobalFont((ViewGroup) v);
        context = getActivity().getApplicationContext();
        button1 = (Button) v.findViewById(R.id.my_page_received_praise);
        button2 = (Button) v.findViewById(R.id.my_page_received_comfort);
        ((ImageView) v.findViewById(R.id.my_page_profile_img)).setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        SharedPreferences psf = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        ((TextView) v.findViewById(R.id.my_page_status_nick)).setText(psf.getString("nickname", "Angel"));

        waveView1 = (ImageView) v.findViewById(R.id.my_page_background_wave);
        waveView2 = (ImageView) v.findViewById(R.id.my_page_background_wave2);
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        anim.setDuration(20000);
        anim.setRepeatCount(-1);
        waveView1.startAnimation(anim);
        waveView2.startAnimation(anim);

        return v;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = new Intent();
        Class clas = null;
        switch (viewId) {
            case R.id.my_page_received_praise:
                clas = ReceivedActivity.class;
                intent.putExtra("category", "praise");
                break;
            case R.id.my_page_received_comfort:
                clas = ReceivedActivity.class;
                intent.putExtra("category", "worry");
                break;
            case R.id.my_page_profile_img:
                clas = LevelActivity.class;
                //getSharedPreference!! level 꺼내오기
                intent.putExtra("level", 5);
                break;
        }
        intent.setClass(context, clas);
        startActivity(intent);
    }
}
