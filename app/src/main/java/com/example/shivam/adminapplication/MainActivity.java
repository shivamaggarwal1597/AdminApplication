package com.example.shivam.adminapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //This is for splash screen and would then go to Login Screen
        Intent intent = new Intent(MainActivity.this,NavigatorActivity.class );
        startActivity(intent);
    }
}
