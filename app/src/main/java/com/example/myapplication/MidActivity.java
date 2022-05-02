package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MidActivity extends AppCompatActivity {
    Button mnext, dateCh;
    public int dYear, dMonth, dDay;
    public String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_layout);

        mnext = (Button) findViewById(R.id.mnext);
        dateCh = (Button) findViewById(R.id.picDate);

        dateCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                dYear = c.get(Calendar.YEAR);
                dMonth = c.get(Calendar.MONTH);
                dDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MidActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                                //Toast.makeText(MidActivity.this, "Date "+date,Toast.LENGTH_SHORT).show();

                            }
                        }, dYear, dMonth, dDay);
                datePickerDialog.show();
            }
        });

        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!date.equals("")) {
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        intent.putExtra("sDate", date);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Select Date", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Log.e(TAG,"Exception in invoking next page2"+e);
                }
            }
        });

    }
}
