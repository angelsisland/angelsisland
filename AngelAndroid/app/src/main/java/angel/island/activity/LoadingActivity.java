package angel.island.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.facebook.AppEventsLogger;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import java.net.CookieHandler;
import java.net.CookieManager;

import angel.island.R;
import angel.island.net.Proxy;
import angel.island.util.FontActivity;
import angel.island.util.FontUtil;

/**
 * Created by choi on 2015. 1. 22..
 */
public class LoadingActivity extends FontActivity {

    String id;
    CookieManager cookieManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        prepare();
        startLoading();
    }

    private void prepare() {
        FontUtil.extraBold = Typeface.createFromAsset(getAssets(), "NanumGothicExtraBold.ttf.mp3");
        cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    private Session.StatusCallback statusCallback = new SessionStatusCallback();

    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            // Respond to session state changes, ex: updating the view
        }
    }

    private void startLoading() {

        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Session.openActiveSession(LoadingActivity.this, true, new Session.StatusCallback() {
                    // callback when session changes state
                    @Override
                    public void call(final Session session, SessionState state, Exception exception) {
                        if (session.isOpened()) {
                            Request.newMeRequest(session, new Request.GraphUserCallback() {
                                @Override
                                public void onCompleted(GraphUser user, Response response) {
                                    if (user != null) {
                                        id = user.getId();
                                        Log.e("UID", id);
                                        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("name", user.getName());
                                        editor.putString("firstName", user.getFirstName());
                                        editor.putString("lastName", user.getLastName());
                                        editor.commit();
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                new Proxy().sendId(id, session.getAccessToken());
                                            }
                                        }).start();
                                    }
                                }
                            }).executeAsync();


                            //String token = session.getAccessToken();

                            //token을 서버로 보내기.

                            /*
                            true일 경우 user정보를 가져와 저장한 후 MainActivity 실행
                            false일 경우 일단 user정보 넘기고 opening activity를 실행
                            */
                            Intent intent = new Intent();

                            Boolean isFirst = getSharedPreferences("first", MODE_PRIVATE).getBoolean("first", true);

                            if (isFirst) {
                                intent.setClass(LoadingActivity.this, OpeningActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                intent.setClass(LoadingActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
            }
        }.sendEmptyMessageDelayed(0, 500);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
}
