package com.returnzero.hellowisconsin;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class RingtoneService extends Service {


    MediaPlayer ring_tone;
    int startId;
    boolean isRunning;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //Fetch the extar string values
        String state= intent.getExtras().getString("extra");




        //Converts extra strings from intent to IDs
        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }


        // If else statement

        //if no music and clicked alarm on.Music should start playing
        if (!this.isRunning && startId == 1){

            ring_tone = MediaPlayer.create(this, R.raw.hello_wisconsin);
            // Ringtone start
            ring_tone.start();

            this.isRunning = true;
            this.startId = 0;

            //Notification
            //Notification services
            NotificationManager notify_manager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);

            //Intent that goes to Main Activity
            Intent intent_main_activity = new Intent(this.getApplicationContext(),MainActivity.class);

            //pending intent
            PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this,
                    0 ,intent_main_activity , 0);

            //Notification Parameter
            Notification notification_popup = new Notification.Builder(this)
                    .setContentTitle("The Alarm is going off")
                    .setContentText("Touch here!")
                    .setContentIntent(pending_intent_main_activity)
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setAutoCancel(true)
                    .build();


            //Notification call command
            notify_manager.notify(0, notification_popup);


        }
        //if music playing and clicked alarm off.Music should stop playing
        else if (this.isRunning && startId == 0){

            //Ringtone
            ring_tone.stop();
            ring_tone.reset();

            this.isRunning = false;
            this.startId = 0;

        }
        // No music and clicked alarm off. Do nothing
        else if (!this.isRunning && startId == 0){

            this.isRunning = false;
            this.startId = 0;

        }
        // Music playing and clicked alarm onn. Do nothing.
        else if (this.isRunning && startId == 1){

            this.isRunning = true;
            this.startId = 1;

        }
        // Debug
        else{
            Log.e("else","Mama piniki");

        }





        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

         super.onDestroy();
        this.isRunning = false;



    }

}





