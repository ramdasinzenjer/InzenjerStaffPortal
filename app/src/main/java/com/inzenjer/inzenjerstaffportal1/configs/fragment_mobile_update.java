package com.inzenjer.inzenjerstaffportal1.configs;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.inzenjer.inzenjerstaffportal1.Adapter.conectivity;
import com.inzenjer.inzenjerstaffportal1.Adapter.updatemob;
import com.inzenjer.inzenjerstaffportal1.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by dell on 14-Oct-17.
 */

public class fragment_mobile_update extends Fragment {


    Context context = this.getContext();
    String m;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_fragment_mobile_update, container, false);
        Button bt = (Button) root.findViewById(R.id.mobupdate);
        final EditText mob = (EditText) root.findViewById(R.id.mobno);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m = mob.getText().toString();
                updatemob n = new  updatemob(m);
            }

        });


        return root;

    }


}
