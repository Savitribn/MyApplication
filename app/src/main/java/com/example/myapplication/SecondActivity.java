package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    public TextView disDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        try {
            disDate = (TextView) findViewById(R.id.Disdate);
            Bundle bundle = getIntent().getExtras();
            String rdate = bundle.getString("sDate");

            disDate.setText(" : " + rdate);
        }
        catch (Exception e){
            Log.e(TAG,"Exception in displaying results"+e);
        }


    }
}
