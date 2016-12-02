package com.example.friendzone;

/**
 * Created by vmsadmin on 10/25/2016.
 */

public class clsFZfriend {

    public String getFrndname() {
        return frndname;
    }

    public void setFrndname(String frndname) {
        this.frndname = frndname;
    }

    private String frndname;

    public String getFrndemail() {
        return frndemail;
    }

    public void setFrndemail(String frndemail) {
        this.frndemail = frndemail;
    }

    private String frndemail;

    public boolean isFrndselected() {
        return frndselected;
    }

    public void setFrndselected(boolean frndselected) {
        this.frndselected = frndselected;
    }

    private boolean frndselected;
}
