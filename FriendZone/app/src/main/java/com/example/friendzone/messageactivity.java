package com.example.friendzone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by vmsadmin on 10/12/2016.
 */

public class messageactivity extends AppCompatActivity {

    protected Context context = null;

    TextView friendname = null;

    RecyclerView Msglist = null;

    private List<ClsMessage> msglist;



    private LinearLayoutManager msglistManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base2);
        //setContentView(R.layout.activity_base22);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        context = this.getApplicationContext();

        friendname = (TextView) findViewById(R.id.txtfrndname);

        Msglist = (RecyclerView) findViewById(R.id.rcv1);

        clsfriend objfriend = new clsfriend();


        msglist = getdatafromserver().getMsglist();

            adapteractivity objadapter = new adapteractivity(this ,msglist);

                String Friendname = "";
                Friendname = getdatafromserver().getFriendname();
                friendname.setText(Friendname);


        msglistManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        msglistManager.scrollToPosition(0);
        Msglist.setLayoutManager(msglistManager);
        Msglist.setAdapter(objadapter);

    }


    public clsfriend getdatafromserver(){

        ClsMessage objmessage = new ClsMessage();

        String jsonmsglist = "";

        jsonmsglist = "{" + "\"friendname\":\"Shobana\"," +
                "\"msglist\":" +"[" +
                "{ " +
                "\"msgID\":\"1\"," +
                "\"msgText\":\"This Json message1\"" +
                "}" + "," +
                "{ " +
                "\"msgID\":\"2\"," +
                "\"msgText\":\"This Json message2\"" +
                "}" +"," +
                "{ " +
                "\"msgID\":\"3\"," +
                "\"msgText\":\"This Json message 3\"" +
                "}" + "," +
                "{ " +
                "\"msgID\":\"4\"," +
                "\"msgText\":\"This Json message4\"" +
                "}" +"," +
                "{ " +
                "\"msgID\":\"5\"," +
                "\"msgText\":\"This Json message 5\"" +
                "}" + "," +
                "{ " +
                "\"msgID\":\"6\"," +
                "\"msgText\":\"This Json message6\"" +
                "}" +"," +
                "{ " +
                "\"msgID\":\"7\"," +
                "\"msgText\":\"This Json message7\"" +
                "}" + "," +
                "{ " +
                "\"msgID\":\"8\"," +
                "\"msgText\":\"This Json message8\"" +
                "}" +"," +
                "{ " +
                "\"msgID\":\"9\"," +
                "\"msgText\":\"This Json message9\"" +
                "}" + "," +
                "{ " +
                "\"msgID\":\"10\"," +
                "\"msgText\":\"This Json message10\"" +
                "}" +
                "]" + "}";



            Gson objgson = new Gson();



            clsfriend objfriend = objgson.fromJson(jsonmsglist,clsfriend.class);

            return objfriend;



    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
