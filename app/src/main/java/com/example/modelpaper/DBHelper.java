package com.example.modelpaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import static com.example.modelpaper.UserProfile.Users.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Model.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        UserProfile.Users.COLS_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        UserProfile.Users.COLS_2 + " TEXT," +
                        UserProfile.Users.COLS_3 + " TEXT," +
                        UserProfile.Users.COLS_4 + " TEXT," +
                        UserProfile.Users.COLS_5 + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addInfo(String userName,String Password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLS_2,userName);
        values.put(UserProfile.Users.COLS_4,Password);

        long newRowId = db.insert(TABLE_NAME,null,values);
        return newRowId;

    }

    public void  readAllInfor(){

    }

    public Cursor getContact(String UName, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserProfile.Users.COLS_3, UserProfile.Users.COLS_4,UserProfile.Users.COLS_5};
        String selection = UserProfile.Users.COLS_2 + " LIKE?";
        String[] selection_args = {UName};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }


    public void deleteInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();
        String selection = UserProfile.Users.COLS_2 + " LIKE ?";
        String[] selectionArgs = {userName};
        db.delete(TABLE_NAME,selection,selectionArgs);
    }

    public int updateInfo(String userName,String Password,String DOB,String Gender){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLS_3,DOB);
        //values.put(UserProfile.Users.COLS_5,Gender);
        values.put(UserProfile.Users.COLS_4,Password);
        values.put(UserProfile.Users.COLS_5,Gender);

        String selection = UserProfile.Users.COLS_2 + " LIKE ?";
        String[] selectionArgs = {userName};

        int count = db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;
    }

    public Boolean checkusernamepassword(String username , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Model where UN = ? and PW = ?", new String[] {username,password});
        if(cursor.getCount() > 0 ){
            return true;
        }
        else
            return false;
    }

    /*public List readAllInfo(String req)
    {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                UserProfile.Users.COLS_1,
                UserProfile.Users.COLS_2,
                UserProfile.Users.COLS_3,
                UserProfile.Users.COLS_4,
                UserProfile.Users.COLS_5
        };
        String sortOrder = UserProfile.Users.COLS_2 + " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLS_2));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLS_4));
            userNames.add(username);
            passwords.add(password);
        }
        cursor.close();
        return userNames;

    }*/
}
