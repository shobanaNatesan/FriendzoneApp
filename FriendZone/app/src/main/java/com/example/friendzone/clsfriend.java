package com.example.friendzone;

import java.util.List;

/**
 * Created by vmsadmin on 10/10/2016.
 */

public class clsfriend {
    private String friendname;

    private List<ClsMessage> msglist;

    public String getFriendname(){
        return friendname;
    }
    public void setFriendname(String prmfriendname){

        this.friendname = prmfriendname;
    }


    public List<ClsMessage> getMsglist() {

        return msglist;
    }

    public void setMsglist(List<ClsMessage> msglist) {

        this.msglist = msglist;
    }


    clsfriend(){

    }


}
