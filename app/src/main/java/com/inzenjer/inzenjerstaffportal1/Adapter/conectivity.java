package com.inzenjer.inzenjerstaffportal1.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
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


public class conectivity {

    String url, sd, status;
    Context context;
    ProgressDialog pg;

    public conectivity(String url, Context context, String sd, ProgressDialog pg) {
        this.url = url;
        this.context = context;
        this.sd = sd;
        this.pg = pg;
        signin();


    }

    private void signin() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String response = "";
        final String finalResponse = response;
        String S_URL = url;
        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        //String sm = response.toString();
                        pg.dismiss();
                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        try {
                            /*JSONObject jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("result");
                            JSONObject employee = result.getJSONObject(1);
                            status = employee.getString("status");*/
                            //TODO add json parser

                            //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(AppMain.this, status, Toast.LENGTH_SHORT).show();
                            // View inflatedView = getLayoutInflater().inflate(R.layout.content_home, null);
                            // TextView text = (TextView) inflatedView.findViewById(R.id.username_dis);
                            // text.setText(Config.Name);
                            //Toast.makeText(AppMain.this, sm , Toast.LENGTH_SHORT).show();
                            //Toast.makeText(AppMain.this, status, Toast.LENGTH_SHORT).show();


                        } catch (Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    /*  if (status.equals("sucess"))
                        {

                            Toast.makeText(context, "Succ", Toast.LENGTH_SHORT).show();


                        }
                        if (status.equals("Fail"))
                        {

                            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();

                        }*/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.e("ErrorResponse", finalResponse);
                        Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_LONG).show();


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                //params.put("FirstName", tusername.getText().toString());
                //params.put("Password", tPassword.getText().toString());
                params.put("Password", "abc");
                params.put("FirstName", "jojikennady@gmail.com");
                params.put("photo", sd);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
        Toast.makeText(context, "worked", Toast.LENGTH_SHORT).show();
    }
}
