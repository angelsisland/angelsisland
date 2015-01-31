package amacrazy.com.angel.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.facebook.AppEventsLogger;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import amacrazy.com.angel.R;
import amacrazy.com.angel.fragment.FacebookFragment;

/**
 * Created by choi on 2015. 1. 22..
 */
public class LoadingActivity extends Activity {

    private FacebookFragment facebookFragment;
    LoginButton authButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
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
                    public void call(Session session, SessionState state, Exception exception) {
                        if (session.isOpened()) {

                            Request.newMeRequest(session, new Request.GraphUserCallback() {
                                @Override
                                public void onCompleted(GraphUser user, Response response) {
                                    if (user != null) {
                                        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("id", user.getId());
                                        editor.putString("name", user.getName());
                                        editor.putString("firstName", user.getFirstName());
                                        editor.putString("lastName", user.getLastName());
                                        editor.commit();
                                        Toast.makeText(getApplicationContext(), user.getName().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).executeAsync();
                            String token = session.getAccessToken();
                            //token을 서버로 보내기.
                            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }.sendEmptyMessageDelayed(0,3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
}
