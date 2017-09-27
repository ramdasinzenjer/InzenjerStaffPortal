package com.inzenjer.inzenjerstaffportal1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Emailauth extends AppCompatActivity {
  ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailauth);
        getSupportActionBar().hide();
        Button Bt;
        Bt = (Button) findViewById(R.id.EmailValidation);
        Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }
        });

    }
    private  void validation()
    {
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String urlfinal = "https://inzenjerdemo.000webhostapp.com/trial.php?id=";
        String url = urlfinal+Config.Email ;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(Emailauth.this, "Verify Email and login again", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Emailauth.this , AppMain.class);
                startActivity(i);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
