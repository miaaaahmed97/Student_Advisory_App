package com.example.android.loginandsignup;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MarkAttendanceActivity extends AppCompatActivity {

    Date calendar;


    SimpleDateFormat df;
    String curr_date;

    SQLiteDatabase myDB = Splashscreen.myDB;
    public Context c;
    public TableLayout tableLayout;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    private ArrayList<Integer> studentIDs = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);
        c = this;

        calendar = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd/MM/yyyy");
        curr_date = df.format(calendar);

        markAttendance();

    }

    public void markAttendance()
    {

//        ScrollView sv = new ScrollView(this);
//        final LinearLayout ll = new LinearLayout(this);
//        ll.setOrientation(LinearLayout.VERTICAL);
//        sv.addView(ll);
//
//        Button b = new Button(this);
//        b.setText("I don't do anything, but I was added dynamically. :)");
//        ll.addView(b);


        boolean marked = false;
        Cursor cursor = myDB.rawQuery("select Student_cms_ID, Student_name from Student", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            //Log.i("hamnasnewloop","new");

            // Read columns data
            int student_cms_id = cursor.getInt(cursor.getColumnIndex("Student_cms_ID"));

            String student_name = cursor.getString(cursor.getColumnIndex("Student_name"));
            //int Total_Sales = cursor.getInt(cursor.getColumnIndex("Total_Sales"));

            //Log.i("hamnascursorloop", name);
            tableLayout = (TableLayout) findViewById(R.id.list2);
            TableRow row = new TableRow(c);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            String[] colText = {"   " + student_name  +"                                      "};

            for (String text : colText)
            {
                //Log.i("hamnastvview", text);
                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.LEFT);
                tv.setTextSize(20);
                tv.setPadding(5, 5, 5, 5);
                tv.setText(text);
                row.addView(tv);

                CheckBox cb = new CheckBox(getApplicationContext());
                checkBoxes.add(cb);
                studentIDs.add(student_cms_id);
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(5, 5, 40, 5);
                //cb.setText("I'm dynamic!");
                row.addView(cb);



//                b.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                            CheckBox cb = new CheckBox(getApplicationContext());
//                            cb.setText("I'm dynamic!");
//                            ll.addView(cb);

                    //}
                //});

                //CheckBox checkBox = (CheckBox) findViewById(R.id.check);


//                // Check which checkbox was clicked
//                switch(view.getId()) {
//                    case R.id.checkbox_meat:
//                        if (checked)
//                        // Put some meat on the sandwich
//            else
//                        // Remove the meat
//                        break;
            }
            tableLayout.addView(row);
            cursor.moveToNext();
        }

    }


    public void Save(View view) {

        CheckBox checkBox;
        Integer ID;

        for(int i = 0; i < checkBoxes.size(); i++){

            checkBox = checkBoxes.get(i);
            ID = studentIDs.get(i);
            int marked = 0;
            boolean checked = checkBox.isChecked();

            if(checked){
                marked = 1;
                myDB.execSQL("INSERT INTO Attendance(Session_Date,Student_cms_ID,Marked) VALUES('"+curr_date+"','"+ID+"','"+marked+"')");
            }
            else{
                marked = 0;
                myDB.execSQL("INSERT INTO Attendance(Session_Date,Student_cms_ID,Marked) VALUES('"+curr_date+"','"+ID+"','"+marked+"')");
            }

            Log.i("hamnasdate", curr_date);
            Log.i("hamnasdate", String.valueOf(marked));
            Log.i("hamnasdate", String.valueOf(ID));
        }

        Cursor cursor = myDB.rawQuery("select min(Session_Number) from AdvisorySession where Conducted = 0", null);
        cursor.moveToFirst();
        int session_number = cursor.getInt(cursor.getColumnIndex("min(Session_Number)"));

        myDB.execSQL( "UPDATE AdvisorySession\n" +
                "\t\tSET Conducted =  1 \n" +
                "\t\tWHERE Session_Number = '"+session_number+"';");

        startActivity(new Intent(getApplicationContext(),AdvisorySessionActivity.class));
    }
}
