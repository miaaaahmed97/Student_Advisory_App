package com.example.android.loginandsignup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterComplains extends AppCompatActivity {
    SQLiteDatabase myDB = Splashscreen.myDB;
    public Context context;
    //ProgressDialog progressDialog;
    private Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_register_complains);
        myDB = openOrCreateDatabase("CosmicCakes", MODE_PRIVATE, null);
        btnRegister= (Button) findViewById(R.id.ButtonSendFeedback);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("feedback", "op");
                sendFeedback(btnRegister);
            }
        });


    }

    public void sendFeedback(View button){

        final EditText feedbackField = (EditText) findViewById(R.id.EditTextFeedbackBody);
        String feedback = feedbackField.getText().toString();
        Log.i("hamna", feedback);



        int cms_ID = StudentHomeActivity.getStudent().getStudent_cms_ID();
//        int cms_ID = StudentHomeActivity.getRegNum();
        Cursor cursor = myDB.rawQuery("select max(Complain_Number) from Complains", null);
        cursor.moveToFirst();
        int complain_ID = cursor.getInt(cursor.getColumnIndex("max(Complain_Number)"));
        myDB.execSQL("INSERT INTO Complains (Complain_Number, Problem, Status, Student_cms_ID) VALUES ("+ ++complain_ID +",'"+feedback+"', 'UNRESOLVED',"+ cms_ID+")");
        //myDB.execSQL("INSERT INTO Complains(Description,Anonymous) VALUES('"+feedback+"','"+bRequiresResponse+"')");

        Toast.makeText(RegisterComplains.this,"Complain Registered!",Toast.LENGTH_SHORT).show();
        //progressDialog.cancel();
        //finish();
        startActivity(new Intent(getApplicationContext(),StudentComplainList.class));

        //Intent i = new Intent(getApplicationContext(), YourComplainsActivity.class);
        //startActivity(i);

        //myDB.close();
    }
}
