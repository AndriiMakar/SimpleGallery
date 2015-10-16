package com.makar.andrii.simplegallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;

//main activity class
public class MainActivity extends Activity implements OnRecyclerClickListener, OnLoadFinishedListener {
    public final static String EXTRA_MESSAGE = "com.makar.andrii.simplegallery.MESSAGE";
    private ArrayList<ImageClass> imageClasses = new ArrayList<>();                             // array list in which i strore image url and id
    private RecyclerView rv;
    private static final String jsonURL = "http://192.168.2.164/test.json";                     // url to json file in internet
    private NetworkStringLoader networkStringLoader;                                            // class with which i get json string


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);  // checking internet availability
        NetworkInfo nf = cn.getActiveNetworkInfo();

        if (nf != null && nf.isConnected()) {                                                           // if internet connection presented start activity that
                                                                                                        // show layout with the gallery and images
            setContentView(R.layout.activity_main);

            rv = (RecyclerView) findViewById(R.id.rv);
            GridLayoutManager lm = new GridLayoutManager(this, 2);
            rv.setLayoutManager(lm);


            networkStringLoader = new NetworkStringLoader();
            networkStringLoader.requestString(jsonURL);                                                  // calling requestString method to make request
            networkStringLoader.setOnLoadFinishedListener(this);                                         // and get json string


        } else if (nf == null || !nf.isConnected()) {                                                    // if no internet connection start activity that
                                                                                                         // show message no iternet and have refresh button
            Intent intentNoInternet = new Intent(this, NoInternet.class);
            startActivity(intentNoInternet);
            this.finish();
        }

    }


    private void initializeData(String json1) {                                             // this function doing parse json string

        String url1 = "";
        int id1 = 0;

        JsonReader reader = new JsonReader(new StringReader(json1));
        reader.setLenient(true);

        JsonElement jsonElement = new JsonParser().parse(reader);
        int i = 1;
        imageClasses = new ArrayList<>();

        for (JsonElement data : jsonElement.getAsJsonObject().getAsJsonArray("data")) {
            id1 = data.getAsJsonObject().get("id").getAsInt();                              // get from the json string id and url of image
            url1 = data.getAsJsonObject().get("url").getAsString();

            imageClasses.add(new ImageClass(id1, url1));                                    // and put them into array list
            initializeAdapter();
        }
    }


    private void initializeAdapter() {                                                      // method which initialize adapter with array list of images
        Adapter adapter = new Adapter(imageClasses);
        adapter.setOnRecyclerClickListener(this);
        rv.setAdapter(adapter);
    }


    @Override
    public void onClick(int position, View view) {                                          // method by which i get the url and positionof the clicked image
        String strName = imageClasses.get(position).url;
        Intent intent = new Intent(this, DisplaySingleImage.class);
        intent.putExtra(EXTRA_MESSAGE, strName);                                            // send the url of image to another activity
        startActivity(intent);
    }


    @Override
    public void onLoadFinished(String json) {
        initializeData(json);                                                               // when json string is loaded, start initializeData() to initilize
    }                                                                                       // array list
}
