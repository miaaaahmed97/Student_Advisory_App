package com.example.android.loginandsignup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdvisorySessionActivity extends AppCompatActivity {

    SQLiteDatabase myDB = Splashscreen.myDB;
    private int session_num;
    private String date;
    private String time;
    private String venue;
    private int conducted;

    Date calendar;
    SimpleDateFormat df;
    String curr_date;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisory_session);

        calendar = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd/MM/yyyy");
        curr_date = df.format(calendar);

        cardDataDisplay();
    }

    private void cardDataDisplay(){
        TextView textView1 = (TextView) findViewById(R.id.date);
        TextView textView2 = (TextView) findViewById(R.id.time);
        TextView textView3 = (TextView) findViewById(R.id.venue);

        Cursor cur = myDB.rawQuery("select Session_Number from AdvisorySession where Session_Date = '" + curr_date +"'" ,null);
        cur.moveToFirst();
        int session_no = cur.getInt(cur.getColumnIndex("Session_Number"));
        myDB.execSQL( "UPDATE AdvisorySession\n" +
                "\t\tSET Conducted =  1 \n" +
                "\t\tWHERE Session_Number < '"+session_no+"';");


        Cursor cursor = myDB.rawQuery("select Session_Number, Session_Date, Time, Venue, Conducted from AdvisorySession where Session_Number = (select min(Session_Number) from AdvisorySession where Conducted = 0)", null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("hamnasnewloop","new");

            // Read columns data
            session_num = cursor.getInt(cursor.getColumnIndex("Session_Number"));
            date = cursor.getString(cursor.getColumnIndex("Session_Date"));
            time = cursor.getString(cursor.getColumnIndex("Time"));
            venue = cursor.getString(cursor.getColumnIndex("Venue"));
            conducted = cursor.getInt(cursor.getColumnIndex("Conducted"));
            textView1.setText("Date: "+date);
            textView2.setText("Time: "+time);
            textView3.setText("Venue: "+venue);
            cursor.moveToNext();
        }



        if(curr_date.equals(date) ){
            Button attendanceButton = (Button) findViewById(R.id.select);
            attendanceButton.setEnabled(true);
        }
    }


    public void mark(View view) {
        startActivity(new Intent(getApplicationContext(),MarkAttendanceActivity.class));

    }
}
