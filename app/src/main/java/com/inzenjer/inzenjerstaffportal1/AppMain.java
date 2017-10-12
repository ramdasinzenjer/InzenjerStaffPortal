package com.inzenjer.inzenjerstaffportal1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.inzenjer.inzenjerstaffportal1.configs.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AppMain extends AppCompatActivity {
    private String username, password;
    Button login;
    TextView fgt;
    EditText tusername, tPassword;
    ProgressDialog pg;
    String status;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        login = (Button) findViewById(R.id.loginid);
        tusername = (EditText) findViewById(R.id.email_username);
        tPassword = (EditText) findViewById(R.id.passwordid);
        fgt = (TextView) findViewById(R.id.forgetpossword);
         //txt = (TextView) findViewById(R.id.username_dis);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
                ;
            }
        });

        fgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fgtpassword();
            }
        });
    }

    private void signin() {
        Config.Email = tusername.getText().toString().trim();
        pg = ProgressDialog.show(AppMain.this, "Please wait...", "Fetching...", false, false);
        RequestQueue queue = Volley.newRequestQueue(AppMain.this);
        String response = "";
        final String finalResponse = response;
        String S_URL = "https://inzenjerdemo.000webhostapp.com/login.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pg.dismiss();
                        //String sm = response.toString();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("result");
                            JSONObject employee = result.getJSONObject(1);
                           status = employee.getString("status");
                            Config.Name = employee.getString("Name");
                            Config.Dob = employee.getString("dob");
                            Config.Mobile = employee.getString("Mobile");
                            Config.Photo = employee.getString("photo");

                            //Toast.makeText(AppMain.this, response, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(AppMain.this, status, Toast.LENGTH_SHORT).show();
                           // View inflatedView = getLayoutInflater().inflate(R.layout.content_home, null);
                           // TextView text = (TextView) inflatedView.findViewById(R.id.username_dis);
                           // text.setText(Config.Name);
                            //Toast.makeText(AppMain.this, sm , Toast.LENGTH_SHORT).show();
                            //Toast.makeText(AppMain.this, status, Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            Toast.makeText(AppMain.this, e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        if (status.equals("sucess")) {

                            Intent i = new Intent(AppMain.this, Home.class);
                            startActivity(i);


                        }
                        if (status.equals("Fail")) {

                            Toast.makeText(AppMain.this, "No User ", Toast.LENGTH_SHORT).show();

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


                //params.put("FirstName", tusername.getText().toString());
                //params.put("Password", tPassword.getText().toString());
                params.put("FirstName", "ras");
                params.put("Password", "123");
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }

    private void fgtpassword() {
        Intent i = new Intent(AppMain.this, forgetpassword.class);
        startActivity(i);
    }
}
