package com.example.android.loginandsignup;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Facade_Person {
    Person person_home_object;
    SQLiteDatabase myDB = Splashscreen.myDB;

    public boolean create_student_object(String email, String password){
        Cursor cursor = myDB.rawQuery("select Student_cms_ID, Student_name, Email, Username, cms_password, cgpa, Status, Address from Student where Email = '" + email +"'and cms_password ='"+ password +"'", null);
        cursor.moveToFirst();
        if(cursor.getCount()!= 0){
            person_home_object = new Student(cursor);
            StudentHomeActivity.setStudent((Student)person_home_object);
            return true;
        }
        return false;
    }
    public boolean create_advisor_object(String email, String password){
        Cursor cursor = myDB.rawQuery("select Advisor_name, Advisor_Email, Advisor_Contact_Number, Advisor_Meeting_Hours, Advisor_Office_Location from Advisor", null);
        cursor.moveToFirst();
        if(cursor.getCount()!= 0){
            person_home_object = Advisor.getInstance(cursor);
            AdvisorHomeActivity.setStudent((Advisor)person_home_object);
            return true;
        }
        return false;
    }
}
