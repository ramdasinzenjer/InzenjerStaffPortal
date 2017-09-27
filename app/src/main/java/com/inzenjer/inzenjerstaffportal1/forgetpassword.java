package com.inzenjer.inzenjerstaffportal1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class forgetpassword extends AppCompatActivity {
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        final EditText txtid = (EditText) findViewById(R.id.TxtRequestPassword);
        Button bt = (Button) findViewById(R.id.RequestPassword);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st= txtid.getText().toString();
                Forgetpassword(st);
            }
        });
    }
    private  void Forgetpassword(String s)
    {
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String urlfinal = "https://inzenjerdemo.000webhostapp.com/password.php?id=";
        String url = urlfinal+s ;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(forgetpassword.this, "Get Password from email and login again", Toast.LENGTH_LONG).show();
                Intent i = new Intent(forgetpassword.this , AppMain.class);
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
