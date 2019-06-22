package com.example.android.loginandsignup;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ContactAdvisorActivity extends AppCompatActivity {

    public Context con;
    SQLiteDatabase myDB = Splashscreen.myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_advisor);
        con = this;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = myDB.rawQuery("select Advisor_Email from Advisor", null);
                cursor.moveToFirst();
                String advisor_email = cursor.getString(cursor.getColumnIndex("Advisor_Email"));
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + advisor_email));
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    //TODO: Handle case where no email app is available
                }


                //Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });

        advisorDataDisplay();

    }

    public void advisorDataDisplay(){
        TextView textView1 = (TextView) findViewById(R.id.AdvisorName);
//        TextView textView2 = (TextView) findViewById(R.id.StudentEmail);
        TextView textView3 = (TextView) findViewById(R.id.AdvisorContact);
        TextView textView4 = (TextView) findViewById(R.id.AdvisorOfficeHours);
        TextView textView5 = (TextView) findViewById(R.id.AdvisorOfficeLocation);

        Cursor cursor = myDB.rawQuery("select Advisor_name, Advisor_Contact_Number, Advisor_Meeting_Hours, Advisor_Office_Location from Advisor", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("hamnasnewloop","new");

            // Read columns data
            String advisor_name = cursor.getString(cursor.getColumnIndex("Advisor_name"));
            //String student_email = cursor.getString(cursor.getColumnIndex("Email"));
            int Advisor_Contact_Number = cursor.getInt(cursor.getColumnIndex("Advisor_Contact_Number"));
            String advisor_Contact = String.valueOf(Advisor_Contact_Number);
            String advisor_meeting_hours = cursor.getString(cursor.getColumnIndex("Advisor_Meeting_Hours"));
            String advisor_office_location = cursor.getString(cursor.getColumnIndex("Advisor_Office_Location"));
            //int quantity = cursor.getInt(cursor.getColumnIndex("Quantity"));

            textView1.setText(advisor_name);
//            textView2.setText("Email: "+student_email);
            textView3.setText("Ph#: "+advisor_Contact);
            textView4.setText("Meeting Hours: "+advisor_meeting_hours);
            textView5.setText("Office Location: "+advisor_office_location);
            cursor.moveToNext();

        }
    }


}
