package com.inzenjer.inzenjerstaffportal1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.NullCipher;

public class Reg extends AppCompatActivity {
    Button regg;
    ProgressDialog pg;
    EditText Firstname, Lastname, Email, Mobile, Password, ConfirmPassword;
    TextView db;
    SimpleDateFormat dtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        setTitle("Staff Registation");
        regg = (Button) findViewById(R.id.ProceedId);
        regg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firstname = (EditText) findViewById(R.id.FirstNameId);
                Lastname = (EditText) findViewById(R.id.LastNameId);
                Email = (EditText) findViewById(R.id.EmailId);
                Mobile = (EditText) findViewById(R.id.MobileId);
                Password = (EditText) findViewById(R.id.PasswordId);
                ConfirmPassword = (EditText) findViewById(R.id.PasswordConfirmId);
                chechall();


            }
        });
    }

    private void chechall()
    {   int b = 0;
        String fn = Firstname.getText().toString().trim();
        String ln = Lastname.getText().toString().trim();
        String Em = Email.getText().toString().trim();
        String Mob = Mobile.getText().toString().trim();
        String Pas = Password.getText().toString().trim();
        String cn = ConfirmPassword.getText().toString().trim();

        if (fn.matches("")) {
            Toast.makeText(this, "Enter 1st Name", Toast.LENGTH_SHORT).show();
            Firstname.requestFocus();
            b=1;
        }
        else if (ln.matches("")) {
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show();
            Lastname.requestFocus();
            b=1;
        }
        else if (Em.matches("")) {
            Toast.makeText(this, "Enter Email ", Toast.LENGTH_SHORT).show();
            Email.requestFocus();
            b=1;
        }
        else if(isEmailValid(Em)==false)
        {
            Toast.makeText(this, "Enter a valid Email", Toast.LENGTH_SHORT).show();
            Email.requestFocus();
            b=1;
        }
       else if (Mob.matches("")) {
            Toast.makeText(this, "Enter Mobile no", Toast.LENGTH_SHORT).show();
            Mobile.requestFocus();
            b=1;
        }
       else if (Pas.matches("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            Password.requestFocus();
            b=1;
        }
        else if (cn.matches("")) {
            Toast.makeText(this, "Repeat Password", Toast.LENGTH_SHORT).show();
            ConfirmPassword.requestFocus();
            b=1;
        }
        else if (Pas.matches(cn)==false)
        {
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
            b=1;
        }
        else if (b==0)
        {
            signupRequest();
            pg = ProgressDialog.show(Reg.this, "Please wait...", "Fetching...", false, false);
        }

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
                        Log.v(" ", sm);
                        Toast.makeText(Reg.this, response.toString(), Toast.LENGTH_LONG).show();


                        if (response.equals("Successfully Signed In")) {

                            Toast.makeText(Reg.this, "success", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Reg.this , AppMain.class);
                            startActivity(i);

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


                params.put("FirstName", Firstname.getText().toString());
                params.put("LastName", Lastname.getText().toString());
                params.put("Email", Email.getText().toString());
                params.put("Mobile", Mobile.getText().toString());
                params.put("Password", Password.getText().toString());
                params.put("CreatedDate", "Date");

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
