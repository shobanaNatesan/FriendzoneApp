package com.example.friendzone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmsadmin on 10/25/2016.
 */

public class clsFZfrndlist {


    private String FBEmail;


    public ArrayList<clsFZmemberdetails> getFriendlist() {
        return Friendlist;
    }

    public void setFriendlist(ArrayList<clsFZmemberdetails> friendlist) {
        Friendlist = friendlist;
    }

    private ArrayList<clsFZmemberdetails> Friendlist;


    public String getFBEmail() {
        return FBEmail;
    }

    public void setFBEmail(String FBEmail) {
        this.FBEmail = FBEmail;
    }


}
