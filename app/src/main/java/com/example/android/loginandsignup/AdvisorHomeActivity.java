package com.example.android.loginandsignup;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AdvisorHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Context cont;
    SQLiteDatabase myDB = Splashscreen.myDB;
    private static Advisor advisor;
    int advisor_contact_number = 0;

    public static void setStudent(Advisor advisor_object) {
        advisor = advisor_object;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_home);
        cont = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cardDataDisplay();
    }

    private void cardDataDisplay(){
        TextView textView1 = (TextView) findViewById(R.id.advisorName);
        TextView textView2 = (TextView) findViewById(R.id.advisorEmail);
        TextView textView3 = (TextView) findViewById(R.id.advisorContact);
        TextView textView4 = (TextView) findViewById(R.id.advisorOfficeHours);
        TextView textView5 = (TextView) findViewById(R.id.advisorOfficeLocation);

        textView1.setText(advisor.getAdvisor_name());
        textView2.setText("Email: "+advisor.getAdvisor_email());
        textView3.setText("Contact Number: "+advisor.getAdvisor_contact_number());
        textView4.setText("Meeting Hours: "+advisor.getAdvisor_meeting_hours());
        textView5.setText("Office Location: "+advisor.getAdvisor_office_location());
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
        } else if (id == R.id.nav_session) {

            startActivity(new Intent(getApplicationContext(),AdvisorySessionActivity.class));


        } else if (id == R.id.nav_complain_viewer) {

            startActivity(new Intent(getApplicationContext(),ComplainDetailsAdvisorActivity.class));

        } else if (id == R.id.nav_manage) {

            //startActivity(new Intent(getApplicationContext(),ContactAdvisorActivity.class));


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
