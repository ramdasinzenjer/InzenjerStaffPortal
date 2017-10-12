package com.inzenjer.inzenjerstaffportal1.Adapter;


import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.inzenjer.inzenjerstaffportal1.Notification;
import com.inzenjer.inzenjerstaffportal1.R;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    private List<String> values;
    @Override
    public Myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Myadapter.ViewHolder holder, int position) {
        final String name = values.get(position);
        holder.notify.setText(name);
        holder.notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Notification nn = new Notification();
                String temp = holder.notify.getText().toString();
                nn.clicker(temp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public View layout;
        public TextView notify;
        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            notify = (TextView) itemView.findViewById(R.id.notifications);
        }
    }
    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }
    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }
    public Myadapter(List<String> myDataset) {
        values = myDataset;
    }

}
