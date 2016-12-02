package com.example.friendzone;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    public TextView mTextView = null;

    public ImageView mimage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextView = (TextView) findViewById(R.id.textView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void CheckVolley(View view){


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.95.3/FBApp/FBWebServices/HW";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);

    }

    public void CheckVolleyJSON (View view){

        String jsonmsglist;
        jsonmsglist = "{" + "\"friendname\":\"Shobana\"," +
                "\"msglist\":" +"[" +
                "{ " +
                "\"msgID\":\"1\"," +
                "\"msgText\":\"This Json messageA\"" +
                "}" + "," +
                "{ " +
                "\"msgID\":\"2\"," +
                "\"msgText\":\"This Json messageB\"" +
                "}" +"]" + "}";
        JSONObject reqobject = null;

        try{

             reqobject = new JSONObject(jsonmsglist);


        }
        catch(Exception e){
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.95.3/FBApp/FBWebServices/MLJ";


        JsonObjectRequest JORequest = new JsonObjectRequest (Request.Method.POST, url,reqobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject  response) {


                        mTextView.setText("Response is: "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

        queue.add(JORequest);

    }

    public void CheckVolleyImage(View view){

        final ImageView mimage;
        String url ="http://192.168.95.3/FBApp/MediaUploads/131180334316677284_purplescenery.JPG";

        mimage = (ImageView) findViewById(R.id.imageview);

            RequestQueue Queue = Volley.newRequestQueue(this);

        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mimage.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        mimage.setImageResource(R.mipmap.ic_favourites);
                    }
                });
// Access the RequestQueue through your singleton class.
        Queue.add(request);

    }

    public void callservice(View V){


        Intent intent = new Intent(this,MyIntentService.class);
        startService(intent);
    }


}




