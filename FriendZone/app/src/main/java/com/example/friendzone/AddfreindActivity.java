package com.example.friendzone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class AddfreindActivity extends AppCompatActivity {


    protected Context context = null;

    EditText FZemail = null;

    RecyclerView memberlist = null;

    ArrayList<clsFZmemberdetails> Memberlist;

    private LinearLayoutManager memberlistManager;

    clsmemberlist objmemberlist = null;

    clsdataoprstatus objoperstatus = null;

    memberlistadapter objadapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfreind);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this.getApplicationContext();
        memberlist = (RecyclerView) findViewById(R.id.rcv2);



        memberlistManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        memberlistManager.scrollToPosition(0);

        memberlist.setLayoutManager(memberlistManager);
    }

    public void gotomainpage(View view) {

        Intent intentnavigate = new Intent(this, MainActivity.class);

        startActivity(intentnavigate);
    }

    public void gotoupload(View view) {

        Intent intentupload = new Intent(this, UploadActivity.class);

        startActivity(intentupload);
    }

    public void gototimeline(View V){

        Intent intenttimeline = new Intent(this,TimelineActivity.class);

        startActivity(intenttimeline);
    }

    public void getmemberlist(View view) {

        getmemblistfromserver();


    }

    public void getmemblistfromserver() {

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
        String url = "http://192.168.95.3/FBApp/FBWebServices/GMLFS";

        JsonObjectRequest objreq = new JsonObjectRequest(Request.Method.POST, url, reqobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                        String jsonfrndlist = "";

                        jsonfrndlist = response.toString();
                        Gson objgsonresponse = new Gson();


                        objmemberlist = objgsonresponse.fromJson(jsonfrndlist, clsmemberlist.class);

                        Memberlist = objmemberlist.getMymemberlist();

                        getmemblist(Memberlist);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(objreq);

    }

    public void getmemblist(ArrayList<clsFZmemberdetails> paramemblist) {

        objadapter = new memberlistadapter(AddfreindActivity.this, paramemblist);

        memberlist.setAdapter(objadapter);


    }


    public void addfriends(View view) {
        clsFZfrndlist objfriendlist = new clsFZfrndlist();

        FZemail = (EditText) findViewById(R.id.txt_FZemail);

        String loggedinmember = "";
        loggedinmember = FZemail.getText().toString();
        objfriendlist.setFBEmail(loggedinmember);


        ArrayList<clsFZmemberdetails> frndselecectedlist = new ArrayList<clsFZmemberdetails>();

        ArrayList<clsFZmemberdetails> frndlist = ((memberlistadapter) objadapter)
                .getMemberlist();

        for (int i = 0; i < frndlist.size(); i++) {
            clsFZmemberdetails objfrnd = frndlist.get(i);
            if (objfrnd.isFrndselected() == true) {


                frndselecectedlist.add(objfrnd);

            } else {

                //do something
            }

            objfriendlist.setFriendlist(frndselecectedlist);
        }

        Gson objfrndlistgson = new Gson();
        String jsonfrndlist = "";

        jsonfrndlist = objfrndlistgson.toJson(objfriendlist);

        JSONObject frndlistreqobj = null;

        try {
            frndlistreqobj = new JSONObject(jsonfrndlist);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestQueue queuefrndlist = Volley.newRequestQueue(this);
        String url = "http://192.168.95.3/FBApp/FBWebServices/AFLTM";

        JsonObjectRequest jsonreqobj = new JsonObjectRequest(Request.Method.POST, url, frndlistreqobj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String jsondataopstatus = "";


                        jsondataopstatus = response.toString();
                        Gson objgsonresponse = new Gson();


                        objoperstatus = objgsonresponse.fromJson(jsondataopstatus, clsdataoprstatus.class);

                        String operstatusmsg = "";

                        operstatusmsg = objoperstatus.getDboperationstausmsg();


                        Toast.makeText(AddfreindActivity.this, operstatusmsg, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        queuefrndlist.add(jsonreqobj);
    }



    }




