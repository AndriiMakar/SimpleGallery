package com.makar.andrii.simplegallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;



public class DisplaySingleImage extends Activity {                          // class used to show single image in gallery
    public ImageView imgG;                                                  //when click on image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);


        imgG = (ImageView) findViewById(R.id.photo_big);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); // message is equal to url of the clicked image

        Picasso.with(imgG.getContext())                                     // loading image into view
                .load(message)
                .into(imgG);


    }

}
