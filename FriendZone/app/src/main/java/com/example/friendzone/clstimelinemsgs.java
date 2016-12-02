package com.example.friendzone;

/**
 * Created by vmsadmin on 11/8/2016.
 */

public class clstimelinemsgs {

    public String getFBEmail() {
        return FBEmail;
    }

    public void setFBEmail(String FBEmail) {
        this.FBEmail = FBEmail;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getFriendename() {
        return friendename;
    }

    public void setFriendename(String friendename) {
        this.friendename = friendename;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }

    public String getMesgimagepath() {
        return mesgimagepath;
    }

    public void setMesgimagepath(String mesgimagepath) {
        this.mesgimagepath = mesgimagepath;
    }

    public String getSharedtime() {
        return sharedtime;
    }

    public void setSharedtime(String sharedtime) {
        this.sharedtime = sharedtime;
    }

    public String FBEmail;
    public String messageID;
    public String friendename;
    public String messagetext;
    public String mesgimagepath;
    public String sharedtime;
}
