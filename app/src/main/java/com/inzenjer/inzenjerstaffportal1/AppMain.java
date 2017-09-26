package com.inzenjer.inzenjerstaffportal1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AppMain extends AppCompatActivity {
    private String username, password;
    Button login, registration;
    TextView forget_Password;
    EditText tusername, tPassword;
    ProgressDialog pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        getSupportActionBar().hide();
        login = (Button) findViewById(R.id.signInId);
        registration = (Button) findViewById(R.id.regi_button);
        tusername = (EditText) findViewById(R.id.username);
        tPassword = (EditText) findViewById(R.id.userpassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppMain.this, Reg.class);
                startActivity(i);


            }
        });
    }

    private void signin() {
        pg = ProgressDialog.show(AppMain.this, "Please wait...", "Fetching...", false, false);
        RequestQueue queue = Volley.newRequestQueue(AppMain.this);
        String response = null;
        final String finalResponse = response;
        String S_URL = "https://inzenjerdemo.000webhostapp.com/login.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pg.dismiss();
                        String sm = response.toString();
                        Log.v(" ", sm);
                        Toast.makeText(AppMain.this, response.toString(), Toast.LENGTH_LONG).show();


                        if (response.equals("Successfully Signed In")) {

                            Toast.makeText(AppMain.this, "success", Toast.LENGTH_SHORT).show();

                        }
                        if (response.equals("Fail")) {

                            Toast.makeText(AppMain.this, "fail", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.e("ErrorResponse", finalResponse);
                        Toast.makeText(AppMain.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("Email", tusername.getText().toString());
                params.put("Password", tPassword.getText().toString());

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }
}
