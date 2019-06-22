package com.example.android.loginandsignup;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.ArrayList;

public class StudentComplainList extends AppCompatActivity {

    ListView customListView;
    private ListAdapter customListAdapter1;
    private ListAdapter customListAdapter2;
    public static SQLiteDatabase myDB= Splashscreen.myDB;
    private static ArrayList<String> complains_with_status = new ArrayList<String>();
    private static ArrayList<Integer> complain_ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_complain_list);
        customListView = findViewById(R.id.complainListStudent);

        customListAdapter1 = new CustomAdapterStudent(this, new ArrayList<String>());// Pass the food array to the constructor.
        customListView.setAdapter(customListAdapter1);

//        if(customListAdapter != null) {
//            Log.i("Clear adapter.", "onCreate: ");
//            ((CustomAdapterStudent) customListAdapter).clear();
//        }
//        customListView.setAdapter(null);
        int cms_ID = StudentHomeActivity.getStudent().getStudent_cms_ID();
        Cursor cursor = myDB.rawQuery("SELECT * FROM Complains WHERE Student_cms_ID = "+cms_ID+";",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int complain_no = cursor.getInt(cursor.getColumnIndex("Complain_Number"));
            String prob = cursor.getString(cursor.getColumnIndex("Problem"));
            String stat = cursor.getString(cursor.getColumnIndex("Status"));
            //int student_id = cursor.getInt(cursor.getColumnIndex("Student_cms_ID"));

            complains_with_status.add(stat + "\n"+ prob);
            complain_ids.add(complain_no);
            cursor.moveToNext();
        }

        customListAdapter1 = new CustomAdapterStudent(this, complains_with_status);// Pass the food array to the constructor.
        customListView.setAdapter(customListAdapter1);
    }


    @Override
    public void onPause() {
        super.onPause();
        ((CustomAdapterStudent) customListAdapter1).clear();
    }

    @Override
    public void onResume(){
        super.onResume();
        customListAdapter2 = new CustomAdapterStudent(this, complains_with_status);
        customListView.setAdapter(customListAdapter2);

    }
}




