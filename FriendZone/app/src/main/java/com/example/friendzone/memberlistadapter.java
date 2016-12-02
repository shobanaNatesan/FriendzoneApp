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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmsadmin on 10/12/2016.
 */




public class



memberlistadapter extends RecyclerView.Adapter<memberlistadapter.ViewHolder> {

    private ArrayList<clsFZmemberdetails> memberlist;
    private  Context mcontext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView membername;
        public TextView memberemail;
        public CheckBox membselected;

        public clsFZmemberdetails objfrnd;

        public ViewHolder(View itemview) {

            super(itemview);

            this.membername = (TextView) itemview.findViewById(R.id.txtmembername);
            this.memberemail = (TextView) itemview.findViewById(R.id.txtmemberemail);
            this.membselected = (CheckBox) itemview.findViewById(R.id.chkmembselect);



       }
    }

    private Context getMcontext(){

        return mcontext;
    }

    public memberlistadapter(Context context,ArrayList<clsFZmemberdetails> prmmemblist){
        mcontext = context;
        memberlist = prmmemblist;
    }


    @Override
    public memberlistadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View frndlistView = inflater.inflate(R.layout.friendlist, parent, false);

        // Return a new holder instance
        ViewHolder frndlistVH = new ViewHolder(frndlistView);

        return frndlistVH;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(memberlistadapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position

        final int pos = position;

        // Set item views based on your views and data model
        TextView friendname = viewHolder.membername;
        friendname.setText(memberlist.get(pos).getName());

        TextView friendemail = viewHolder.memberemail;
        friendemail.setText(memberlist.get(pos).getEmail());

        CheckBox frndselected = viewHolder.membselected;
        frndselected.setChecked(memberlist.get(pos).isFrndselected());


        frndselected.setTag(memberlist.get(position));

        frndselected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                clsFZmemberdetails contact = (clsFZmemberdetails) cb.getTag();

                contact.setFrndselected(cb.isChecked());
                memberlist.get(pos).setFrndselected(cb.isChecked());


            }
        });

    }

    @Override
    public int getItemCount() {

        return memberlist.size();
    }

    public ArrayList<clsFZmemberdetails> getMemberlist ()
    {
        return memberlist;
    }
}
