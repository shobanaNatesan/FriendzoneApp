package com.example.friendzone;

/**
 * Created by vmsadmin on 10/6/2016.
 */

public class ClsMessage {

    private String msgID;
    private String msgText;

    public String getMsgID() {
        return msgID;
    }
    public void SetMsgID(String msgID){
        this.msgID = msgID;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    @Override
    public String toString(){
        return msgID + "-" + msgText ;
    }

    public ClsMessage(){

    }
}
