package com.example.android.loginandsignup;

        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import java.util.ArrayList;

public class ComplainDetailsAdvisorActivity extends AppCompatActivity {

    public static SQLiteDatabase myDB = Splashscreen.myDB;
    private static ArrayList<String> complains = new ArrayList<String>();
    private static ArrayList<Integer> complain_ids = new ArrayList<>();
    private ListAdapter customListAdapter1;
    private ListAdapter customListAdapter2;
    private ListView customListView;

    public static void remove_complain(int position){
        int complain_id = complain_ids.get(position);
        myDB.execSQL("UPDATE Complains SET Status = 'RESOLVED' WHERE Complain_Number = " + complain_id + ";");
        complain_ids.remove(position);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_details_advisor);



        Cursor cursor = myDB.rawQuery("SELECT * FROM Complains WHERE Status = 'UNRESOLVED'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int complain_no = cursor.getInt(cursor.getColumnIndex("Complain_Number"));
            String prob = cursor.getString(cursor.getColumnIndex("Problem"));
            String stat = cursor.getString(cursor.getColumnIndex("Status"));
            int student_id = cursor.getInt(cursor.getColumnIndex("Student_cms_ID"));

            complains.add(prob);
            complain_ids.add(complain_no);
            cursor.moveToNext();
        }

        customListAdapter1 = new CustomAdapter(this,complains);// Pass the food array to the constructor.
        customListView = (ListView) findViewById(R.id.complainList);
        customListView.setAdapter(customListAdapter1);

    }

    @Override
    public void onPause() {
        super.onPause();
        ((CustomAdapter) customListAdapter1).clear();
    }

    @Override
    public void onResume(){
        super.onResume();
        customListView.setAdapter(customListAdapter1);

    }

}
