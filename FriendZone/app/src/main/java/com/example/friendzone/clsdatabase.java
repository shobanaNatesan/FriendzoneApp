package com.example.friendzone;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

/**
 * Created by vmsadmin on 11/9/2016.
 */



    public class clsdatabase extends SQLiteOpenHelper {


        EditText FZnameUI;
        EditText FZemailUI;

        private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "frndzoneDB.sqlite";

        private static final String Table1_Name = "member";
        private static final String TABLE2_NAME = "memberfriend";

    private static String KEY_id = "id";
    private static String KEY_friendid = "friendid";
    //private static String KEY_memberid = "memberid";

    private static String KEY_friendname = "friendname";
    private static String KEY_membername = "membername";
    private static String KEY_friendemail = "friendemail";
    private static String KEY_memberemail = "memberemail";
    private static final String memberfriend_TABLE_CREATE =

                "CREATE TABLE " + TABLE2_NAME + "("  +
            KEY_id + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
            KEY_friendid + " INTEGER NOT NULL  UNIQUE , " +
                        KEY_friendname + " TEXT NOT NULL , " +
            KEY_friendemail + " TEXT NOT NULL  UNIQUE , " +
            KEY_membername + " TEXT NOT NULL , " +
            KEY_memberemail + " TEXT NOT NULL  UNIQUE );" ;

    private static final String member_Table_create =
            "Create table " + Table1_Name + "(" +
                    KEY_id + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                    KEY_membername + " TEXT NOT NULL , " +
                    KEY_memberemail + " TEXT NOT NULL  UNIQUE ); " ;



    clsdatabase(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db1) {

            //SQLiteDatabase db1 = this.getWritableDatabase();
            db1.execSQL(memberfriend_TABLE_CREATE);

            db1.execSQL(member_Table_create);

        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Table1_Name);
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE2_NAME);

        onCreate(db);

    }

    public void addmember(String membername,String memberemail) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_membername, membername);
        values.put(KEY_memberemail, memberemail);

        db.insert(Table1_Name, null, values);
        db.close();
    }




}

