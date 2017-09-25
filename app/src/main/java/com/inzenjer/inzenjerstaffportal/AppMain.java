package com.inzenjer.inzenjerstaffportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AppMain extends AppCompatActivity {
    private  String username , password;
    Button login , registration;
    TextView forget_Password;
    EditText tusername,tPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        getSupportActionBar().hide();

    }
}
