package com.example.friendzone;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vmsadmin on 10/12/2016.
 */



    public class adapteractivity extends RecyclerView.Adapter<adapteractivity.ViewHolder> {

        private List<ClsMessage> msglist;
        private  Context mcontext;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txtvmsgId;
            public TextView txtmsgText;
            public ImageView imgmsgpic;


            public ViewHolder(View itemview) {
                super(itemview);


                txtvmsgId = (TextView) itemview.findViewById(R.id.txtmsgid);
                txtmsgText = (TextView) itemview.findViewById(R.id.txtmsgtext);
                imgmsgpic = (ImageView) itemview.findViewById(R.id.imgmsgpic);
            }

        }

            private Context getMcontext(){

                return mcontext;
                }

            public adapteractivity(Context context,List<ClsMessage> prmmsglist){

                    mcontext = context;
                    msglist = prmmsglist;
                }


    @Override
    public adapteractivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        View msglistView = inflater.inflate(R.layout.content_base3, parent, false);

        // Return a new holder instance
        ViewHolder msglistVH = new ViewHolder(msglistView);

        return msglistVH;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(adapteractivity.ViewHolder viewHolder, int position) {
        // Get the data model based on position

        ClsMessage objmessage = msglist.get(position);

        // Set item views based on your views and data model
        TextView textviewmsgId = viewHolder.txtvmsgId;
        textviewmsgId.setText(objmessage.getMsgID());

        TextView texviewmsgText = viewHolder.txtmsgText;
        texviewmsgText.setText(objmessage.getMsgText());

        //ImageView imagemesgpic = viewHolder.imgmsgpic;


    }


    @Override
    public int getItemCount() {

        return msglist.size();
    }

}
