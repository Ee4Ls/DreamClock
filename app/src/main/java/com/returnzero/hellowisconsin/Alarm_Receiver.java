package com.returnzero.hellowisconsin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class Alarm_Receiver extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("We r doing good", "Hmmmm Nice");

        //Fetch extra string to the ringtone service
        String extra_string = intent.getExtras().getString("extra");


        Log.e("What is your key?", extra_string);

        // Intent to the ringtone service
        Intent service_intent = new Intent(context,RingtoneService.class );

        //Pass the extra strings from main to RingtoneServices
        service_intent.putExtra("extra",extra_string );

        // Start Ringtone Service
        context.startService(service_intent);

    }
}
