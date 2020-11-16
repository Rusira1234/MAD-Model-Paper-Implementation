package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {
    EditText username,DOB,password,TextPersonName6,TextPersonName7,TextPersonName8;
    TextView textView12;
    RadioGroup Gender2;
    RadioButton G,radioButton3,radioButton4;
    String value;
    String UName;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DBHelper databaseHelper;
    Intent Retriew2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        username = findViewById(R.id.TextPersonName6);
        DOB = findViewById(R.id.TextPersonName7);
        password = findViewById(R.id.TextPersonName8);
        Gender2 = findViewById(R.id.radioScene);
        TextPersonName6 = findViewById(R.id.TextPersonName6);
        TextPersonName7 = findViewById(R.id.TextPersonName7);
        TextPersonName8 = findViewById(R.id.TextPersonName8);
        textView12 = findViewById(R.id.textView12);
        radioButton3 =findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        Retriew2 = getIntent();
        TextPersonName6.setText(Retriew2.getStringExtra("UserName"));
        TextPersonName7.setText(Retriew2.getStringExtra("DOB"));
        TextPersonName8.setText(Retriew2.getStringExtra("Password"));
        //int radioButton3 = Gender2.getCheckedRadioButtonId();
        //G = (RadioButton) findViewById(radioButton3);
        //radioButton3 = findViewById(R.id.radioButton3);
        //radioButton4 = findViewById(R.id.radioButton4);
        //RadioGroup  Gender2 = (RadioGroup)findViewById(R.id.radioScene);
        //int radioButton3 = radioGroup.getCheckedRadioButtonId();
        //G = (RadioButton) findViewById(radioButton3);
        //String radiovalue =  G.getText().toString();
        //final String value = ((RadioButton)findViewById(Gender.getCheckedRadioButtonId())).getText().toString();

    }

    public void deleteData(View view){
        DBHelper dbHelper = new DBHelper(this);

        dbHelper.deleteInfo(username.getText().toString());

        Toast.makeText(this,"Data successfully deleted",Toast.LENGTH_SHORT).show();
    }

    public void updateData(View view){
        DBHelper dbHelper = new DBHelper(this);
        int radioButton3 = Gender2.getCheckedRadioButtonId();
        textView12.setText(String.format("%d",radioButton3));
        //radioButton3.getText().toString();
        G = (RadioButton) findViewById(radioButton3);
        //String radiovalue =  G.getText().toString();
        int val = dbHelper.updateInfo(username.getText().toString(),password.getText().toString(),DOB.getText().toString(),G.getText().toString());

        if(val > 0){
            Toast.makeText(this,"Data updated successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Data updated Unsuccessfully",Toast.LENGTH_SHORT).show();
        }
    }

    public void SearchContact(View view){
        UName = TextPersonName6.getText().toString();
        databaseHelper = new DBHelper(getApplicationContext());
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = databaseHelper.getContact(UName,db);
        if(cursor.moveToNext())
        {
            String DOB = cursor.getString(cursor.getColumnIndex("DOB"));
            String Password = cursor.getString(cursor.getColumnIndex("PW"));
            String Gender = cursor.getString(cursor.getColumnIndex("Gender"));
            //TextTitleName.setText(Title);
            TextPersonName7.setText(DOB);
            TextPersonName8.setText(Password);
            if(Gender == "Male"){
                radioButton3.setChecked(true);
            }
            else if(Gender == "Female"){
                radioButton4.setChecked(true);
            }

            //radioButton3;

            //Gender2.setOnCheckedChangeListener(Gender.toString());

        }
        else{
            Toast.makeText(getApplicationContext(), "Invalid Name ! ", Toast.LENGTH_SHORT ).show();
        }
    }
}