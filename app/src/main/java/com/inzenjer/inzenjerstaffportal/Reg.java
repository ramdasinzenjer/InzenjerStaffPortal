package com.inzenjer.inzenjerstaffportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Reg extends AppCompatActivity {
    Button regg;
    ProgressDialog pg;
    EditText username, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        regg = (Button) findViewById(R.id.reggbutton);
        regg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = (EditText) findViewById(R.id.name);
                password = (EditText) findViewById(R.id.password);
                email = (EditText) findViewById(R.id.email);
                signupRequest();
                pg = ProgressDialog.show(Reg.this, "Please wait...", "Fetching...", false, false);
            }
        });
    }


    private void signupRequest() {

        RequestQueue queue = Volley.newRequestQueue(Reg.this);
        String response = null;
        final String finalResponse = response;
        String S_URL = "https://inzenjerdemo.000webhostapp.com/reg.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pg.dismiss();
                        String sm = response.toString();
                        Log.v(" ",sm);
                        Toast.makeText(Reg.this, response.toString(), Toast.LENGTH_LONG).show();

                        if (response.equals("Successfully Signed In")) {

                            Toast.makeText(Reg.this, "success", Toast.LENGTH_SHORT).show();

                        }
                        if (response.equals("Error Sign in")) {

                            Toast.makeText(Reg.this, "fail", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.e("ErrorResponse", finalResponse);
                        Toast.makeText(Reg.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Email", username.getText().toString());
                params.put("Password", password.getText().toString());
                params.put("Username", email.getText().toString());

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }
}
