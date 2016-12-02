package com.example.friendzone;

import java.util.List;

/**
 * Created by vmsadmin on 11/8/2016.
 */

public class clsmessagelist {


    public String getFBemail() {
        return FBemail;
    }

    public void setFBemail(String FBemail) {
        this.FBemail = FBemail;
    }

    public List<clstimelinemsgs> getMessagelist() {
        return Messagelist;
    }

    public void setMessagelist(List<clstimelinemsgs> messagelist) {
        Messagelist = messagelist;
    }

    public String FBemail;
    public List<clstimelinemsgs> Messagelist;
}
