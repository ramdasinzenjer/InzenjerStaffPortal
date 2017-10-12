package com.inzenjer.inzenjerstaffportal1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import com.inzenjer.inzenjerstaffportal1.Adapter.Myadapter;
import com.inzenjer.inzenjerstaffportal1.configs.Config;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {
    final List<String> input = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = (RecyclerView) findViewById(R.id.notificationView);
        Toast.makeText(this, Config.Dob, Toast.LENGTH_SHORT).show();
        if (Config.Dob == null) {
            input.add("Update your Date of birth");
        }
        if (Config.Photo == null) {
            input.add("Update Your Profile image");
        }
        if (Config.Mobile == null) {
            input.add("Update your Phone number");
        }
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new Myadapter(input);
        recyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        input.remove(viewHolder.getAdapterPosition());
                        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    public void clicker(String temp) {
        if (temp.contains("Date")) {
            Log.e("You are at", "work");
            //Toast.makeText(, "Date of birth", Toast.LENGTH_SHORT).show();
        }

            if (temp.contains("Profile")) {
                Log.e("You are at", "profile");
                Intent i = new Intent(Notification.this.getApplicationContext() , Notification.class);
                context.getApplicationContext().startActivity(i);
                //Toast.makeText(this, "Date of birth", Toast.LENGTH_SHORT).show();
            }


        if (temp.contains("Phone")) {
            Log.e("You are at", "Phone");
            //Toast.makeText(this, "Date of birth", Toast.LENGTH_SHORT).show();
        }

    }
}
