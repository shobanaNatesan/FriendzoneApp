package com.example.friendzone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmsadmin on 11/2/2016.
 */

public class clsmemberlist {


    public ArrayList<clsFZmemberdetails> getMymemberlist() {
        return Mymemberlist;
    }

    public void setMymemberlist(ArrayList<clsFZmemberdetails> mymemberlist) {
        Mymemberlist = mymemberlist;
    }

    public ArrayList<clsFZmemberdetails> Mymemberlist;
}
