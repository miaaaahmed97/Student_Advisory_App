package com.example.android.loginandsignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Manager extends AppCompatActivity {
    SQLiteDatabase myDB = Splashscreen.myDB;
    Facade_Person facade_person;

    private EditText editTextEmail1;
    private EditText editTextPassword1;
    ProgressDialog progressDialog;

    private boolean isSigninScreen = true;
    //private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSigninStudent;
    private Button btnSigninAdvisor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        myDB = openOrCreateDatabase("CosmicCakes", MODE_PRIVATE, null);
        facade_person = new Facade_Person();

        //tvSigninInvoker = (TextView) findViewById(R.id.tvSigninInvoker);

        btnSigninStudent= (Button) findViewById(R.id.buttonLogInAsStudent);
        btnSigninAdvisor= (Button) findViewById(R.id.buttonLogInAsAdvisor);

        llSignin = (LinearLayout) findViewById(R.id.llSignin);


        progressDialog = new ProgressDialog(this);

        editTextEmail1 = (EditText) findViewById(R.id.editTextEmail1);
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);
        progressDialog = new ProgressDialog(this);

//        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isSigninScreen = true;
//                showSigninForm();
//            }
//        });
        showSigninForm();

        btnSigninStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("login", "op");
                StudentLogIn();

            }
        });

        btnSigninAdvisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("login", "op");
                AdvisorLogIn();
            }
        });
    }



    private void showSigninForm() {

        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        //tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left_to_right);
        btnSigninStudent.startAnimation(clockwise);
    }


    public void StudentLogIn() {
        String email = editTextEmail1.getText().toString().trim();
        String password = editTextPassword1.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your email.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter your password.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        boolean success = facade_person.create_student_object(email, password);

        if(success){
            Toast.makeText(Manager.this,"Logged In!",Toast.LENGTH_SHORT).show();
            progressDialog.cancel();
            finish();
            startActivity(new Intent(getApplicationContext(),StudentHomeActivity.class));
        }
        else{
            Toast.makeText(Manager.this,"LogIn Failed!",Toast.LENGTH_SHORT).show();
            progressDialog.cancel();
            finish();
        }
    }


    public void AdvisorLogIn() {

        String email = editTextEmail1.getText().toString().trim();
        String password = editTextPassword1.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your email.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter your password.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        boolean success = facade_person.create_advisor_object(email, password);

        if(success){
            Toast.makeText(Manager.this,"Logged In!",Toast.LENGTH_SHORT).show();
            progressDialog.cancel();
            finish();
            startActivity(new Intent(getApplicationContext(),AdvisorHomeActivity.class));
        }
        else{
            Toast.makeText(Manager.this,"LogIn Failed!",Toast.LENGTH_SHORT).show();
            progressDialog.cancel();
            finish();
        }

    }



}

