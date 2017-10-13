package com.inzenjer.inzenjerstaffportal1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.app.Activity.RESULT_OK;


public class fragment_update_dp extends Fragment {
    ImageView imageView;
    TextView textview;
Context context = this.getContext();
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.fragment_updateprofile,container, false);
            Button bt = (Button) root.findViewById(R.id.update_image);
           // imageView = (ImageView) root.findViewById(R.id.imgimg);
            textview = (TextView) root.findViewById(R.id.imggg);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                }
            });

           return  root;

        }


    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
           try {
               switch (requestCode) {
                   case 0:

                       if (resultCode == RESULT_OK) {
                           Uri selectedImage = imageReturnedIntent.getData();
                           imageView.setImageURI(selectedImage);
                           File blob =  new File(selectedImage.toString());
                           Toast.makeText(context, selectedImage.toString(), Toast.LENGTH_SHORT).show();
                           Log.d("this is ssssssss",selectedImage.toString());
                           textview.setText(blob.toString());
                       }

                       break;
                   case 1:
                       if (resultCode == RESULT_OK) {
                           Uri selectedImage = imageReturnedIntent.getData();
                           imageView.setImageURI(selectedImage);

                           Toast.makeText(context, selectedImage.toString(), Toast.LENGTH_SHORT).show();
                           Log.e("",selectedImage.toString());
                           textview.setText(selectedImage.toString());

                       }
                       break;
               }
           }catch (Exception e)
           {
               Log.e("exe",e.toString());
           }

    }
    }

