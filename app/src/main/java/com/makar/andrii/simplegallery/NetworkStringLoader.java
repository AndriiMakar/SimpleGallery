package com.makar.andrii.simplegallery;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


public class NetworkStringLoader {                                                      // class that used to load json from internet
    String jsonString;                                                                  // this string will have the value of the responding request
    StringRequest strReq;                                                               // string used to make request
    OnLoadFinishedListener onLoadFinishedListener;                                      // listener that wiil perform when jsonString get its value


    public void requestString(String url) {

        String tag_string_req = "string_req";
        strReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        jsonString = response.toString();                                 // set the value of json string
                        if (onLoadFinishedListener != null) {
                            onLoadFinishedListener.onLoadFinished(jsonString);            // when string get value send it with onLoadFinished method
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        VolleySingleton.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public void setOnLoadFinishedListener(OnLoadFinishedListener onLoadFinishedListener) {
        this.onLoadFinishedListener = onLoadFinishedListener;
    }
}
