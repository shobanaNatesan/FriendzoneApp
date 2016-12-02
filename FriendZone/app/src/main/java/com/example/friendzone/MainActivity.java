package com.example.friendzone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    protected Context context = null;


    CharSequence successmessage = "";
    CharSequence errmessage = "";
    protected int shortduration = Toast.LENGTH_SHORT;

    public EditText FZnameUI;
    public EditText FZemailUI;

    clsdatabase objdb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = this.getApplicationContext();
        objdb = new clsdatabase(this);




        this.getallinputviewbyID();

    }

    private void getallinputviewbyID(){

    }
    private void setinitialvalues(){
//        String tempmsgtext = "This is test..";
//        txtmsgview.setText(tempmsgtext);

    }

    public void Gotoaddfriends(View view){

        Intent intentnavigate = new Intent(this,AddfreindActivity.class);

        startActivity(intentnavigate);
        String FZname = "";
        String FZEmail = "";
        boolean Isselected;
        Isselected = false;

        FZnameUI = (EditText)findViewById(R.id.txt_FZName);
        FZemailUI = (EditText)findViewById(R.id.txt_Email);
        FZname = FZnameUI.getText().toString();
        FZEmail = FZemailUI.getText().toString();

        clsFZmemberdetails objFZmember = new clsFZmemberdetails();
        objFZmember.setName(FZname);
        objFZmember.setEmail(FZEmail);
        objFZmember.setFrndselected(false);

        objdb.addmember(FZname,FZEmail);


        Gson objgson = new Gson();
        String strjsonFZmem = "";

          strjsonFZmem = objgson.toJson(objFZmember);

        JSONObject reqobject = null;

        try{
            reqobject = new JSONObject(strjsonFZmem);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.95.3/FBApp/FBWebServices/AM";

        // Request a string response from the provided URL.
        JsonObjectRequest JORequest = new JsonObjectRequest (Request.Method.POST, url,reqobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject  response) {
                        // Display the first 500 characters of the response string.
                        successmessage = "Member has been added";

                        Toast.makeText(context,successmessage,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errmessage  = "That didn't work!";

                Toast.makeText(context,errmessage,Toast.LENGTH_LONG).show();
            }
        });

        queue.add(JORequest);

    }

    public void navigatetoaddfriends(View view){

        Intent intentnavigate = new Intent(this,AddfreindActivity.class);

        startActivity(intentnavigate);

    }


    public void GotoUpload(View view){

        Intent intentupload = new Intent(this,UploadActivity.class);
        startActivity(intentupload);
    }



    public void gototimeline(View view){

        Intent intenttimeline = new Intent(this,TimelineActivity.class);
        startActivity(intenttimeline);


    }

    public void GotoMsglistview(View view){
        Intent intentmsglistview = new Intent(this,messageactivity.class);
        startActivity(intentmsglistview);
    }


    public void showmessage(View view){

        ClsMessage objmessage = new ClsMessage();
        objmessage.SetMsgID("1");
        objmessage.setMsgText("This is test");

        String strjsonMsg = "";
        String jsonmsgarr = "";

        jsonmsgarr ="{" + "\"friendname\":\"Shobana\"," + "[" +
                            "{ " +
                            "\"msgId\":\"1\"," +
                            "\"msgText\":\"This Json message\"" +
                            "}" + "," +
                            "{ " +
                             "\"msgId\":\"2\"," +
                             "\"msgText\":\"This Json message2\"" +
                            "}" +
                                "]" + "}";


        try {


            JSONArray objjsonarray = new JSONArray(jsonmsgarr);

            String str = "";

           for(int i=0; i < objjsonarray.length(); i++){

               if(objjsonarray.get(i) instanceof JSONObject)
               {
                   JSONObject objjsonmsg  = (JSONObject) objjsonarray.get(i);
                   str = str + objjsonmsg.get("msgText").toString() + "\n";
               }
               if(objjsonarray.get(i) instanceof String)
               {
                   String string = (String) objjsonarray.get(i);

                   str = str + string + "\n";
               }




           }

        } catch (JSONException e) {
            e.printStackTrace();

        }

       // String strconv = objjson1.toString();
        try{
//            testclass objtest = new testclass();
//
//            Gson objgson = new Gson();
//
//            String strjsontest = objgson.toJson(objtest);
//
//            testclass objtest2 = objgson.fromJson(strjsontest ,testclass.class);
//
//            //txtmsgview.setText(strjsontest);
//
//            clsfriend objfriend = objgson.fromJson(jsonmsgarr,clsfriend.class);
//
//            String friendname = "";
//
//            friendname = objfriend.getFriendname();
//
//            //txtmsgview.setText(friendname);


        }
        catch (Exception e){
            e.printStackTrace();


        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
