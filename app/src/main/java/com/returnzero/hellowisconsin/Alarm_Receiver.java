package com.returnzero.hellowisconsin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by rayhanislam on 8/5/16.
 */
public class Alarm_Receiver extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("We r doing good", "Hmmmm Nice");

        // Intent to the ringtone service
        Intent service_intent = new Intent(context,RingtoneService.class );

        // Start Ringtone Service
        context.startService(service_intent);

    }
}
