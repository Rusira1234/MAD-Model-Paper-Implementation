package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileManagement extends AppCompatActivity {
    Button button3;
    Intent RetriewIntent,RetriewRegister;
    TextView TextPersonName3,TextPersonName4,TextPersonName5;
    String UserName,DOB,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
        button3 = findViewById(R.id.button3);
        TextPersonName3 = findViewById(R.id.TextPersonName3);
        TextPersonName4 = findViewById(R.id.TextPersonName4);
        TextPersonName5 = findViewById(R.id.TextPersonName5);
        RetriewIntent = getIntent();
        TextPersonName3.setText(RetriewIntent.getStringExtra("UserName"));
        TextPersonName4.setText(RetriewIntent.getStringExtra("DOB"));
        TextPersonName5.setText(RetriewIntent.getStringExtra("Password"));
        RetriewRegister = getIntent();
        TextPersonName3.setText(RetriewRegister.getStringExtra("UserName2"));
        //TextPersonName4.setText(RetriewRegister.getStringExtra("DOB2"));
        TextPersonName5.setText(RetriewRegister.getStringExtra("Password2"));


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                intent.putExtra("UserName",TextPersonName3.getText().toString());
                intent.putExtra("Password",TextPersonName5.getText().toString());
                intent.putExtra("DOB",TextPersonName4.getText().toString());
                //intent.putExtra("Gender",Gender);
                startActivity(intent);
            }
        });
    }
}