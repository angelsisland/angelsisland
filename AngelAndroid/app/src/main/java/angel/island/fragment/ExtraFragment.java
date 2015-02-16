package angel.island.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import angel.island.R;
import angel.island.model.Comment;
import angel.island.model.User;
import angel.island.model.Writing;
import angel.island.net.Proxy;
import angel.island.util.FontUtil;

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

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                 test_writing = new Proxy().getPraiseNeed();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String testString = new Gson().toJson(test_writing);
        Log.e("JSON", testString);
    }

    /*
    private void uploadWriting() {
        new Thread(new Runnable() {
            Writing writing = new Writing("","","",null);
            @Override
            public void run() {
                new HttpHelper().connect("servletname", "POST", writing, null);
            }
        }).start();
    }
    */

    private void downloadWriting() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test_writing = new Proxy().connect("","POST","null", Writing[].class);
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
                new Proxy().connect("", "POST", comment, null);
            }
        }).start();
    }

    private void downloadComment() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test_comment = new Proxy().connect("", "POST", 2, Comment[].class);
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
                test_user = new Proxy().connect("", "POST", null, User.class);
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
