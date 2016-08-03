package com.returnzero.hellowisconsin;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    //Declaring variables

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context =this;

        // Inatialize time picker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        // Inatialize alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Inatialize text update
        update_text = (TextView) findViewById(R.id.update_text);

        //Instance of a calendar
        final Calendar calendar = Calendar.getInstance();

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
}
