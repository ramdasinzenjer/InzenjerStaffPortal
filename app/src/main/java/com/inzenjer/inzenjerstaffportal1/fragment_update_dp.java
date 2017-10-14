package com.inzenjer.inzenjerstaffportal1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inzenjer.inzenjerstaffportal1.Adapter.conectivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class fragment_update_dp extends Fragment {
    ImageView imageView;
    TextView textview;
    Context context = this.getContext();
    Bitmap bittmap;
    String s;
    byte[] bArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_updateprofile, container, false);
        Button bt = (Button) root.findViewById(R.id.update_image);
        // imageView = (ImageView) root.findViewById(R.id.imgimg);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });

        return root;

    }


    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                Log.e("one", "2");
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageView.setImageURI(selectedImage);
                    try {
                        bittmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bittmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                    byte[] bArray = bos.toByteArray();
                    //Log.e("byte",bArray.toString());
                }

                break;
            case 1:
                Log.e("byte", "p1");
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bittmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bittmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                     bArray = bos.toByteArray();
                    Log.e("str", "str");
                    conectivity = new conectivity("jahcgjd",this.getContext() , bArray);
                }
                break;

        }

        generateNoteOnSD(this.getContext(), "stext",bArray );
    }

    public void generateNoteOnSD(Context context, String sFileName, byte[] sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            String sd= Base64.encodeToString( bArray , 1);
            writer.append(sd);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("txt",e.toString());
        }
    }
}

