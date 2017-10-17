package com.inzenjer.inzenjerstaffportal1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
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
    public Notification activity;
    public RelativeLayout update_laout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = (RecyclerView) findViewById(R.id.notificationView);
        update_laout = (RelativeLayout) findViewById(R.id.relativeUpdate);
        Toast.makeText(this, Config.Dob, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Config.Photo, Toast.LENGTH_SHORT).show();
        activity = this;

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
        mAdapter = new Myadapter(input , this , update_laout , recyclerView);
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


}
