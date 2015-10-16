package com.makar.andrii.simplegallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class NoInternet extends Activity {                                                                  // activity that start when there is no interner connection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        TextView noInternet = (TextView) findViewById(R.id.textView);

        noInternet.setText("No internet connection");
        noInternet.setTextColor(Color.BLACK);

    }


    public void refresh(View view) {                                                                         // this method is called every time when user
                                                                                                                // press refresh button
                                                                                                                    //if internet appears than star activity
        ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);              // which show gallery
        NetworkInfo nf = cn.getActiveNetworkInfo();

        if (nf != null && nf.isConnected()) {
            Intent intentYesInternet = new Intent(NoInternet.this, MainActivity.class);
            startActivity(intentYesInternet);
            this.finish();


        }
    }
}
