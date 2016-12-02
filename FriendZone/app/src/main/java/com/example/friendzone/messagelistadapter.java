package com.example.friendzone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmsadmin on 10/12/2016.
 */



public class messagelistadapter extends RecyclerView.Adapter<messagelistadapter.ViewHolder> {

    private List<clstimelinemsgs> messagelist;
    private  Context mcontext;



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView friendname;
        public TextView statusmsg;
        public ImageView statusimg;
        public TextView sharedtime;


        public ViewHolder(View itemview) {

            super(itemview);

            this.friendname = (TextView) itemview.findViewById(R.id.txtfriendname);
            this.statusmsg = (TextView) itemview.findViewById(R.id.txtstatus);
            this.statusimg = (ImageView) itemview.findViewById(R.id.imgstatus);
            this.sharedtime = (TextView) itemview.findViewById(R.id.txtshrdtime);


        }
    }

    private Context getMcontext(){

        return mcontext;
    }

    public messagelistadapter(Context context,List<clstimelinemsgs> prmmsglist){
        mcontext = context;
        messagelist = prmmsglist;

    }


    @Override
    public messagelistadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View frndlistView = inflater.inflate(R.layout.messagelist, parent, false);


        ViewHolder frndlistVH = new ViewHolder(frndlistView);

        return frndlistVH;
    }


    @Override
    public void onBindViewHolder(messagelistadapter.ViewHolder viewHolder, int position) {

        final int pos = position;


        TextView friendname = viewHolder.friendname;
        friendname.setText(messagelist.get(pos).getFriendename());

        TextView statusmsg = viewHolder.statusmsg;
        statusmsg.setText(messagelist.get(pos).getMessagetext());



        ImageView statusimg = viewHolder.statusimg;

        String imagepath = messagelist.get(pos).getMesgimagepath();

        if(imagepath.isEmpty())
        {
            statusimg.setImageResource(R.mipmap.ic_favourites);
        }
        else
        {

            byte[] imagebytearray = Base64.decode(imagepath,Base64.DEFAULT);
            Bitmap bm = BitmapFactory.decodeByteArray(imagebytearray ,0 ,imagebytearray.length);
            statusimg.setImageBitmap(bm);
        }

//        String url = "";
//        url = messagelist.get(pos).getMesgimagepath();
//
//        RequestQueue Queueimg = Volley.newRequestQueue(mcontext);
//
//        ImageRequest request = new ImageRequest(url,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//                        statusimg.setImageBitmap(bitmap);
//                    }
//                }, 0, 0, null,
//                new Response.ErrorListener() {
//                    public void onErrorResponse(VolleyError error) {
//                        statusimg.setImageResource(R.mipmap.ic_favourites);
//                    }
//                });
//
//
//        Queueimg.add(request);



        TextView sharedtime = viewHolder.sharedtime;
        sharedtime.setText(messagelist.get(pos).getSharedtime());

            }




    @Override
    public int getItemCount() {

        return messagelist.size();
    }

    public List<clstimelinemsgs> getMessagelist(){

        return messagelist;
    }


}
