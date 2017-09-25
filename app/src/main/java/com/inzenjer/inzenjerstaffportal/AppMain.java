package com.inzenjer.inzenjerstaffportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
registration = (Button)findViewById(R.id.regi_button);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppMain.this , Reg.class);
                startActivity(i);
            }
        });
    }
}
