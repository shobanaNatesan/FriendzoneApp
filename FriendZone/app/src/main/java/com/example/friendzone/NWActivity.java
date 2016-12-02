package com.example.friendzone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NWActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    protected void connecttoNW(View view) {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

             NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        String url = "";
                    url = "http://192.168.95.3/FBApp/FBWebServices/MLJ";

                String method = "";
                method = "POST";

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



        JSONObject reqobj = null;


        if (networkInfo != null && networkInfo.isConnected()) {



            reqobj = new JSONObject();
            try{
                JSONObject apireqobj = new JSONObject(jsonmsglist);
                reqobj.put("requrl" , url);
                reqobj.put("reqmethod",method);
                reqobj.put("reqobject",apireqobj);

                new DownloadWebpageTask().execute(reqobj);

            }



            catch(Exception e){
                e.printStackTrace();
            }

            Toast.makeText(this, "Connection is available", Toast.LENGTH_LONG).show();

        }
        else {


            Toast.makeText(this, "No network connection available.", Toast.LENGTH_LONG).show();
        }

    }

      private class DownloadWebpageTask extends AsyncTask< JSONObject, String, String> {
        @Override
        protected String doInBackground(JSONObject... paramjsonobj) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(paramjsonobj[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            //textView.setText(result);
            Toast.makeText(NWActivity.this, "Result received" +result +
                    "" +
                    "", Toast.LENGTH_SHORT).show();
        }
    }

     // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content as a InputStream, which it returns as
    // a string.
    private String downloadUrl(JSONObject paramjsonobj) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

            String localurl = "";
            String localmethod = "";
            JSONObject localapireqobj = new JSONObject();

        try{

            localurl = paramjsonobj.get("requrl").toString();
            localmethod= paramjsonobj.get("reqmethod").toString();
            localapireqobj = paramjsonobj.getJSONObject("reqobject");



        }
        catch(Exception e){
            e.printStackTrace();
        }

        try {
            URL url = new URL(localurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");
            // Starts the query

            if(localmethod == "GET")
            {
               conn.setRequestMethod("GET");
            }
            if(localmethod == "POST")
            {
                conn.setRequestMethod("POST");
            }

            if(localapireqobj == null)
            {
                conn.connect();
            }

            if(localapireqobj != null)
            {
                try{
//
//                    OutputStream os = conn.getOutputStream();
//                    OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//                    osw.write(localapireqobj.toString());
//                    osw.flush();
//                    osw.close();
//                    os.close();

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            int response = conn.getResponseCode();
            Log.d("DEBUG", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}

