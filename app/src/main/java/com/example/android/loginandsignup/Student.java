package com.example.android.loginandsignup;

import android.database.Cursor;

public class Student extends Person{
    private int student_cms_ID;
    private String student_name;
    private String student_email;
    private String student_username;
    private String password;
    private double student_cgpa;
    private String student_status;
    private String student_address;

    Student(Cursor cursor){
        super();
        cursor.moveToFirst();
        student_cms_ID = cursor.getInt(cursor.getColumnIndex("Student_cms_ID"));
        student_name = cursor.getString(cursor.getColumnIndex("Student_name"));
        student_email = cursor.getString(cursor.getColumnIndex("Email"));
        student_username = cursor.getString(cursor.getColumnIndex("Username"));
        password = cursor.getString(cursor.getColumnIndex("cms_password"));
        student_cgpa = cursor.getDouble(cursor.getColumnIndex("cgpa"));
//        String cgpa = String.valueOf(student_cgpa);
        student_status = cursor.getString(cursor.getColumnIndex("Status"));
        student_address = cursor.getString(cursor.getColumnIndex("Address"));
    }
    public int getStudent_cms_ID() {
        return student_cms_ID;
    }
    public String getStudent_name() {
        return student_name;
    }
    public String getStudent_email() {
        return student_email;
    }
    public String getStudent_username() {
        return student_username;
    }
    public String getStudent_cgpa() {
        return String.valueOf(student_cgpa);
    }
    public String getStudent_status() {
        return student_status;
    }
    public String getStudent_address() {
        return student_address;
    }
}
