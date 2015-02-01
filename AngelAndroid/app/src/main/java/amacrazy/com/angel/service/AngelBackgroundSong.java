package amacrazy.com.angel.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import amacrazy.com.angel.R;

/**
 * Created by choi on 2015. 2. 1..
 */
public class AngelBackgroundSong extends Service {

    public MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("song", "started2");
        player = MediaPlayer.create(this, R.raw.sanctus);
        player.setLooping(false);
        player.setVolume(100,100);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("song", "started");
        player.start();
        return 1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
    }

    public void onPause() {

    }
}
