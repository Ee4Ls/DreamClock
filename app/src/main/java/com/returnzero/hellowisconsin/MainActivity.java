package com.returnzero.hellowisconsin;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //Declaring variables

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;

    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //fab

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        this.context =this;





        // Inatialize time picker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        // Inatialize alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Inatialize text update
        update_text = (TextView) findViewById(R.id.update_text);

        //Instance of a calendar
        final Calendar calendar = Calendar.getInstance();

        //nternt to receiver class
        final Intent my_intent = new Intent(this.context, Alarm_Receiver.class);

        //Inatialize Buttons
        //Button on
        Button alarm_on = (Button) findViewById(R.id.alarm_on);

        //OnClick on
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                //Setting time
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timepicker.getMinute());

                //Getting the int value of he and min
                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();


                 // Convert int to srting
                String hr = String.valueOf(hour);
                String min = String.valueOf(minute);

                // Convert 24 hrs to 12 hrs

                if (hour > 12) {
                    hr = String.valueOf(hour - 12);

                 if(minute<10){
                     min =  "0" +String.valueOf(minute);
                 }
                }
                //Method to update text box
                set_alarm_text (" Alarm set to "   +hr +":"+ min);


                //Pressing extra alarm on button
                my_intent.putExtra("extra","alarm on");


                //Create pending intent to delay intent till wanted time
                 pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0,
                        my_intent,PendingIntent.FLAG_UPDATE_CURRENT);

                //Setting alarm manager
               alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                       pending_intent);
            }
        });

        //Button off
        Button alarm_off = (Button) findViewById(R.id.alarm_off);

        // OnClick off
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Method to update text box
                 set_alarm_text ("Hello Wisconsin!!");

                //Cancell the alarm
                 alarm_manager.cancel(pending_intent);


                //Puting extra string that alarm clock button has been clicked
                my_intent.putExtra("extra","alarf off");

                //Stop the ringtone
                sendBroadcast(my_intent);
            }
        });










    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }



    //fab

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:


                break;
            case R.id.fab2:


                break;
        }
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;


        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;


        }
    }
}

