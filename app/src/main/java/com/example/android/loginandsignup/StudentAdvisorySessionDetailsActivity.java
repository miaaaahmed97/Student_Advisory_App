
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

public class StudentAdvisorySessionDetailsActivity extends AppCompatActivity {

    SQLiteDatabase myDB = Splashscreen.myDB;
    private int session_num;
    private String date;
    private String time;
    private String venue;
    private int conducted;
    private int temp_cms_id;


    Date calendar;
    SimpleDateFormat df;
    String curr_date;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_advisory_session_details);

        calendar = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd/MM/yyyy");
        curr_date = df.format(calendar);

        temp_cms_id = StudentHomeActivity.getStudent().getStudent_cms_ID();
        Log.i("waleeeedrn",String.valueOf(temp_cms_id));


        cardDataDisplay();

        attendanceDataDisplay();
    }

    private void attendanceDataDisplay() {

        TextView attendance = (TextView) findViewById(R.id.attendance);
        Cursor cursor = myDB.rawQuery("select Session_Date, Marked from Attendance where Student_cms_ID='"+temp_cms_id+"'",null);

        String session_date;
        int marked;

        String attendanceData = "";
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Log.i("hamnag","new");

            Log.i("hamnas","new");

            // Read columns data
            marked = cursor.getInt(cursor.getColumnIndex("Marked"));
            session_date = cursor.getString(cursor.getColumnIndex("Session_Date"));

            Log.i("attendanceDataDisplay: ", String.valueOf(marked) );
            Log.i("attendanceDataDisplay: ", session_date);

            attendanceData+=session_date;
            attendanceData+=" ";

            Log.i("attendanceDataDisplay: ", attendanceData);
            if(marked == 0){
                attendanceData+="Absent\n";
            }
            else if(marked ==1){
                attendanceData+="Present\n";
            }
            //Log.i("attendance data ",attendanceData);
            cursor.moveToNext();
        }
        Log.i("attendanceDataDisplay: ", attendanceData);
        attendance.setText(attendanceData);
    }

    private void cardDataDisplay(){
        TextView textView1 = (TextView) findViewById(R.id.date);
        TextView textView2 = (TextView) findViewById(R.id.time);
        TextView textView3 = (TextView) findViewById(R.id.venue);
        //TextView textView4 = (TextView) findViewById(R.id.StudentStatus);
        //TextView textView5 = (TextView) findViewById(R.id.StudentAddress);

        Cursor cursor = myDB.rawQuery("select Session_Number, Session_Date, Time, Venue, Conducted from AdvisorySession where Session_Number = (select min(Session_Number) from AdvisorySession where Conducted = 0)", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //Log.i("hamnasnewloop","new");

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

    }
}

