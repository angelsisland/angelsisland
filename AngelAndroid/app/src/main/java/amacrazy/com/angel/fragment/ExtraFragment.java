package amacrazy.com.angel.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import amacrazy.com.angel.R;
import amacrazy.com.angel.model.Comment;
import amacrazy.com.angel.model.User;
import amacrazy.com.angel.model.Writing;
import amacrazy.com.angel.net.HttpHelper;
import amacrazy.com.angel.util.FontUtil;

/**
 * Created by choi on 2015. 1. 23..
 */
public class ExtraFragment extends Fragment implements View.OnClickListener{

    Button testButton;

    public User test_user = null;
    public Comment[] test_comment = null;
    public Writing[] test_writing = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_extra, null);
        FontUtil.setGlobalFont((ViewGroup) v);
        testButton = (Button)v.findViewById(R.id.test_button);
        testButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        test1();
    }

    private void test1() {
        String result = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", "None");
        Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }

    private void uploadWriting() {
        new Thread(new Runnable() {
            Writing writing = new Writing("","","",null);
            @Override
            public void run() {
                new HttpHelper().connect("servletname", "POST", writing, null);
            }
        }).start();
    }

    private void downloadWriting() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test_writing = new HttpHelper().connect("","POST","null", Writing[].class);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void uploadComment() {
        new Thread(new Runnable() {
            Comment comment = new Comment(1, "comment", "writer");
            @Override
            public void run() {
                new HttpHelper().connect("", "POST", comment, null);
            }
        }).start();
    }

    private void downloadComment() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test_comment = new HttpHelper().connect("", "POST", 2, Comment[].class);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void downloadUser() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test_user = new HttpHelper().connect("", "POST", null, User.class);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
