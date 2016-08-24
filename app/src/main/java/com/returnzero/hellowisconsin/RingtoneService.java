package com.returnzero.hellowisconsin;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by rayhanislam on 8/5/16.
 */
public class RingtoneService extends Service {


    MediaPlayer ring_tone;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);


        ring_tone = MediaPlayer.create(this, R.raw.hello_wisconsin);
        ring_tone.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this, "On Destroyed Called", Toast.LENGTH_SHORT).show();


    }

}





