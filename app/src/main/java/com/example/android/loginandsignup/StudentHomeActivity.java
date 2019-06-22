package com.example.android.loginandsignup;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class StudentHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static Student student;
    private static String loginEmail;
    private static int regNum;
    public Context cont;
    SQLiteDatabase myDB = Splashscreen.myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        cont = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cardDataDisplay();
    }
    public static void setStudent(Student student_object){student = student_object;}

    public static Student getStudent() { return student; }
    /*
    public static void setLoginEmail(String email){
        loginEmail = email;
    }

    public static int getRegNum(){
        Log.i("hamnacheck",String.valueOf(regNum));
        return regNum;
    }
*/

    public void cardDataDisplay(){
        TextView textView1 = (TextView) findViewById(R.id.StudentName);
        TextView textView2 = (TextView) findViewById(R.id.StudentEmail);
        TextView textView3 = (TextView) findViewById(R.id.StudentCGPA);
        TextView textView4 = (TextView) findViewById(R.id.StudentStatus);
        TextView textView5 = (TextView) findViewById(R.id.StudentAddress);

        textView1.setText(student.getStudent_name());
        textView2.setText("Email: "+student.getStudent_email());
        textView3.setText("CGPA: "+ student.getStudent_cgpa());
        textView4.setText("Status: "+student.getStudent_status());
        textView5.setText("Address: "+ student.getStudent_address());
/*
        Cursor cursor = myDB.rawQuery("select Student_cms_ID, Student_name, Email, cgpa, Status, Address from Student where Email = '" + loginEmail +"'", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("hamnasnewloop","new");

            // Read columns data
            String student_name = cursor.getString(cursor.getColumnIndex("Student_name"));
            String student_email = cursor.getString(cursor.getColumnIndex("Email"));
            double student_cgpa = cursor.getDouble(cursor.getColumnIndex("cgpa"));
            String cgpa = String.valueOf(student_cgpa);
            String student_status = cursor.getString(cursor.getColumnIndex("Status"));
            String student_address = cursor.getString(cursor.getColumnIndex("Address"));
            regNum = cursor.getInt(cursor.getColumnIndex("Student_cms_ID"));
            //int quantity = cursor.getInt(cursor.getColumnIndex("Quantity"));

//            textView1.setText(student_name);
//            textView2.setText("Email: "+student_email);
//            textView3.setText("CGPA: "+cgpa);
//            textView4.setText("Status: "+student_status);
//            textView5.setText("Address: "+student_address);
            cursor.moveToNext();
        }
 */
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_home, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_new_complain) {

            startActivity(new Intent(getApplicationContext(),RegisterComplains.class));


        } else if (id == R.id.nav_view_complain) {

            startActivity(new Intent(getApplicationContext(),StudentComplainList.class));

        } else if (id == R.id.nav_manage) {

            startActivity(new Intent(getApplicationContext(),ContactAdvisorActivity.class));


        } else if (id == R.id.nav_advisory_session) {

            startActivity(new Intent(getApplicationContext(),StudentAdvisorySessionDetailsActivity.class));

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
