package com.example.android.loginandsignup;

import android.database.Cursor;

public class Advisor extends Person{
    int advisor_cms_ID;
    String advisor_name;
    String advisor_email;
    String advisor_username;
    String password;
    String advisor_contact_number;
    String advisor_meeting_hours;
    String advisor_office_location;

    private static Advisor ourInstance;

    public static Advisor getInstance(Cursor cursor) {
        if(ourInstance == null){
            ourInstance = new Advisor(cursor);
        }
        return ourInstance;
    }

    private Advisor(Cursor cursor) {
        cursor.moveToFirst();
        advisor_name = cursor.getString(cursor.getColumnIndex("Advisor_name"));
        advisor_email = cursor.getString(cursor.getColumnIndex("Advisor_Email"));
        advisor_meeting_hours = cursor.getString(cursor.getColumnIndex("Advisor_Meeting_Hours"));
        advisor_office_location = cursor.getString(cursor.getColumnIndex("Advisor_Office_Location"));
        advisor_contact_number = cursor.getString(cursor.getColumnIndex("Advisor_Contact_Number"));
    }
    public int getAdvisor_cms_ID() {
        return advisor_cms_ID;
    }

    public String getAdvisor_name() {
        return advisor_name;
    }

    public String getAdvisor_email() {
        return advisor_email;
    }

    public String getAdvisor_username() {
        return advisor_username;
    }

    public String getAdvisor_contact_number() {
        return advisor_contact_number;
    }

    public String getAdvisor_meeting_hours() {
        return advisor_meeting_hours;
    }

    public String getAdvisor_office_location() {
        return advisor_office_location;
    }
}
