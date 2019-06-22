package com.example.android.loginandsignup;

/**
 * Created by Noor Binte Amir on 13-May-17.
 */


        import android.app.Activity;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.PixelFormat;
        import android.os.Bundle;
        import android.view.Window;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.ImageView;
        import android.widget.LinearLayout;

public class Splashscreen extends Activity {
    public static SQLiteDatabase myDB;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
        myDB = openOrCreateDatabase("CosmicCakes", MODE_PRIVATE, null);

        //delete();
        //insert_into_DB();

    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 4000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splashscreen.this,
                            Manager.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Splashscreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();

    }

    public void insert_into_DB() {

//        myDB.execSQL("CREATE TABLE IF NOT EXISTS Complains\n" +
//                //"\t(Complain_ID INT PRIMARY KEY,\n" +
//                "\t    (Description VARCHAR(500) NOT NULL,\n" +
//                "    Anonymous BOOL);\n");
//                //"    CONSTRAINT OrderList_Customer_FK FOREIGN KEY (Customer_ID)\n" +
//                //"\tREFERENCES Customer(Customer_ID) \n" +
//                //"    ON DELETE CASCADE);\n");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS Student\n" +
                "\t(Student_cms_ID INT PRIMARY KEY,\n" +
                "\t Student_name VARCHAR(40),\n" +
                "    Email VARCHAR(320) UNIQUE,\n" +
                "\tUsername VARCHAR(20) UNIQUE,\n" +
                "    cms_password VARCHAR(10) UNIQUE,\n" +
                "    cgpa DOUBLE,\n" +
                "    Status VARCHAR(20),\n" +
                "    Address VARCHAR(40));\n");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS Advisor\n" +
                "\t(Advisor_cms_ID INT PRIMARY KEY,\n" +
                "\t Advisor_name VARCHAR(40),\n" +
                "Advisor_Email VARCHAR(320) UNIQUE,\n" +
                "\tAdvisor_Username VARCHAR(20) UNIQUE,\n" +
                "Advisor_cms_password VARCHAR(10) UNIQUE,\n" +
                "Advisor_Contact_Number VARCHAR(20),\n" +
                "Advisor_Meeting_Hours VARCHAR(20),\n" +
                "Advisor_Office_Location VARCHAR(40));\n");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS Attendance \n" +
                "\t(Session_Date STRING,\n" +
                "    Student_cms_ID INT,\n" +
                "    Marked INTEGER DEFAULT 0,\n" +
                "    CONSTRAINT Attendance_PK PRIMARY KEY(Student_cms_ID, Session_Date)); \n");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS AdvisorySession \n" +
                "\t(Session_Number INT,\n" +
                "    Session_Date STRING,\n" +
                "    Time VARCHAR(30),\n" +
                "    Venue VARCHAR(30),\n" +
                "    Conducted INTEGER DEFAULT 0,\n" +
                "    CONSTRAINT AdvisorySession_PK PRIMARY KEY(Session_Number)); \n");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS Complains\n" +
                "\t(Complain_Number INT ,\n" +
                "    Problem VARCHAR(200),\n" +
                "    Status VARCHAR(10),\n" +
                "    Student_cms_ID INT, \n "+
                "    CONSTRAINT Complains_PK PRIMARY KEY(Complain_Number),\n" +
                "    CONSTRAINT Complains_FK FOREIGN KEY (Student_cms_ID)\n" +
                "\tREFERENCES Student(Student_cms_ID) \n" +
                "    ON DELETE CASCADE\n" +
                "    );\n");


        myDB.execSQL("INSERT INTO Student (Student_cms_ID, Student_name, Email, Username, cms_password, cgpa, Status, Address) VALUES(190290,'Hamna Moiz','hmoieez.bese16seecs@seecs.edu.pk','hmoieez.bese16seecs','100',3.41,'Regular','7th road, satellite Town,Rawalpindi');");
        myDB.execSQL("INSERT INTO Student (Student_cms_ID, Student_name, Email, Username, cms_password, cgpa, Status, Address) VALUES(185178,'Maria Ahmed','mahmed.bese16seecs@seecs.edu.pk','mahmed.bese16seecs','1000',3.6,'Regular','Hyderabad');");

        myDB.execSQL("INSERT INTO Advisor (Advisor_cms_ID, Advisor_name, Advisor_Email, Advisor_Username, Advisor_cms_password, Advisor_Contact_Number, Advisor_Meeting_Hours, Advisor_Office_Location) VALUES(111111,'Majid Maqbool','majid.maqbool@seecs.edu.pk','majid.maqbool','seecs','03331122334','10:00-11:00','Room 101, Faculty Block');");

        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (1,'08/12/2018', '12:00PM', 'CR-01_SEECS', 1)");
        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (2,'09/12/2018', '12:00PM', 'CR-02_SEECS', 0)");
        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (3,'10/12/2018', '12:00PM', 'CR-03_SEECS', 0)");
        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (4,'11/12/2018', '12:00PM', 'CR-04_SEECS', 0)");
        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (5,'12/12/2018', '12:00PM', 'CR-05_SEECS', 0)");
        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (6,'13/12/2018', '12:00PM', 'CR-06_SEECS', 0)");
        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (7,'14/12/2018', '12:00PM', 'CR-07_SEECS', 0)");
        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (8,'15/12/2018', '12:00PM', 'CR-08_SEECS', 0)");
        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (9,'16/12/2018', '12:00PM', 'CR-09_SEECS', 0)");
        myDB.execSQL("INSERT INTO AdvisorySession (Session_Number, Session_Date, Time, Venue, Conducted) VALUES (10,'17/12/2018', '12:00PM', 'CR-10_SEECS', 0)");

        myDB.execSQL("INSERT INTO Complains (Complain_Number, Problem, Status, Student_cms_ID) VALUES (1,'You are evil', 'UNRESOLVED', 190290)");
        myDB.execSQL("INSERT INTO Complains (Complain_Number, Problem, Status, Student_cms_ID) VALUES (2,'Why are you so evil', 'UNRESOLVED', 185178)");
        myDB.execSQL("INSERT INTO Complains (Complain_Number, Problem, Status, Student_cms_ID) VALUES (3,'Hello evil.', 'UNRESOLVED', 185178)");


    }

    public void delete(){

        myDB.execSQL("DROP TABLE Student");
        myDB.execSQL("DROP TABLE Advisor");
        myDB.execSQL("DROP TABLE AdvisorySession");
        myDB.execSQL("DROP TABLE Attendance");
        myDB.execSQL("DROP TABLE Complains");

    }


}

