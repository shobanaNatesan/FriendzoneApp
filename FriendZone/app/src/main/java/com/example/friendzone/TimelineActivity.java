package com.example.friendzone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {


    protected Context context;

    EditText FZemail = null;

    RecyclerView Messagelist = null;

    List<clstimelinemsgs> messagelist;


    private LinearLayoutManager messagelistManager;

    clsmessagelist objmesglist = null;

    messagelistadapter objmsgadapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = this.getApplicationContext();
        Messagelist = (RecyclerView) findViewById(R.id.rcvtimeline);



        messagelistManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        messagelistManager.scrollToPosition(0);

        Messagelist.setLayoutManager(messagelistManager);

    }

    public void gotomainpage(View V){

        Intent intentmain = new Intent(this,MainActivity.class);

        startActivity(intentmain);
    }


    public void navigatetoaddfriends(View view){

        Intent intentnavigate = new Intent(this,AddfreindActivity.class);

        startActivity(intentnavigate);

    }

    public void GotoUpload(View view){

        Intent intentupload = new Intent(this,UploadActivity.class);
        startActivity(intentupload);
    }

    public void getmessages(View view) {

        getmesglistfromserver();


    }



    public void getmesglistfromserver() {

        FZemail = (EditText) findViewById(R.id.txt_FZemail);

        String loggedinmember = "";
        loggedinmember = FZemail.getText().toString();

        clsloggedinmember objloggedinmember = new clsloggedinmember();
        objloggedinmember.setFBEmail(loggedinmember);

        Gson objgson = new Gson();
        String jsonloggedinmember;

        jsonloggedinmember = objgson.toJson(objloggedinmember);

        JSONObject reqobject = null;

        try {

            reqobject = new JSONObject(jsonloggedinmember);


        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.95.3/FBApp/FBWebServices/GMFS";

        JsonObjectRequest objreq = new JsonObjectRequest(Request.Method.POST, url, reqobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                       Toast.makeText(TimelineActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        String jsonmsglist = "";

                        jsonmsglist = response.toString();
                        Gson objgsonresponse = new Gson();


                        objmesglist = objgsonresponse.fromJson(jsonmsglist, clsmessagelist.class);

                        messagelist = objmesglist.getMessagelist();

                        getmesglist(messagelist);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TimelineActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(objreq);

    }

    public void getmesglist(List<clstimelinemsgs> paramsglist) {

        objmsgadapter = new messagelistadapter(TimelineActivity.this,paramsglist);


        Messagelist.setAdapter(objmsgadapter);


    }

}
