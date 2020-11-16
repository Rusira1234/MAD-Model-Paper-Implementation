package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Home extends AppCompatActivity {
    Button button2,button;
    EditText UserName,Password;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DBHelper databaseHelper;
    String DOB,Password2,Gender,user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button2 = findViewById(R.id.button2);
        button = findViewById(R.id.button);
        UserName =findViewById(R.id.TextPersonName);
        Password = findViewById(R.id.TextPassword);

    }

    public void addData(View view){
        DBHelper dbHelper = new DBHelper(this);
        long val = dbHelper.addInfo(UserName.getText().toString(),Password.getText().toString());
        if(val > 0){
            Toast.makeText(this,"Data successfully inserted",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Error in insertion",Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(getApplicationContext(), ProfileManagement.class);
        intent.putExtra("UserName2",UserName.getText().toString());
        intent.putExtra("Password2",Password.getText().toString());
        startActivity(intent);
    }

    public void SignIn(View view){
        DBHelper dbHelper = new DBHelper(this);
        user = UserName.getText().toString();
        pass = Password.getText().toString();
        databaseHelper = new DBHelper(getApplicationContext());
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = databaseHelper.getContact(user,db);
        if(cursor.moveToNext())
        {
            DOB = cursor.getString(cursor.getColumnIndex("DOB"));
            Password2 = cursor.getString(cursor.getColumnIndex("PW"));
            Gender = cursor.getString(cursor.getColumnIndex("Gender"));
            //TextTitleName.setText(Title);
            //TextPersonName7.setText(DOB);
            //TextPersonName8.setText(Password);
            //radioButton3;

            //Gender2.setOnCheckedChangeListener(Gender.toString());

        }

        Boolean checkpass = dbHelper.checkusernamepassword(user,pass);
        if(checkpass == true){
            Toast.makeText(this,"Successful login",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),ProfileManagement.class);
            intent.putExtra("UserName",user);
            intent.putExtra("Password",pass);
            intent.putExtra("DOB",DOB);
            intent.putExtra("Gender",Gender);
            startActivity(intent);
        }else
            Toast.makeText(this,"Wrong credentials",Toast.LENGTH_SHORT).show();

    }

    /*public void signIn(View view){
        DBHelper dbHelper = new DBHelper(this);

        List usernames = dbHelper.readAllInfo("UN");
        List passwords = dbHelper.readAllInfo("PW");

        String user = UserName.getText().toString();
        String password = Password.getText().toString();

        if(usernames.indexOf(user)>=0){
            if(passwords.get(passwords.indexOf(user)).equals(password)){
                Toast.makeText(this,"Login Successfull",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show();
            }
        }
    }*/





}