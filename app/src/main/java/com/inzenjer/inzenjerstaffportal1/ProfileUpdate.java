package com.inzenjer.inzenjerstaffportal1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.inzenjer.inzenjerstaffportal1.configs.Config;

import java.io.ByteArrayOutputStream;

public class ProfileUpdate extends AppCompatActivity {
    Bitmap bittmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

ImageView img = (ImageView) findViewById(R.id.profileupdate) ;

        byte[]  imageBytes = Base64.decode(Config.Photo, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        img.setImageBitmap(decodedImage);

    }
}
