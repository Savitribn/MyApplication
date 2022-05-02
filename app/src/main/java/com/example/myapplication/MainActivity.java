package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.se.omapi.Session;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Locale locale;
    String currentLanguage = "en", selectLang;
    Button next;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
            mSharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
            mEditor = mSharedPreferences.edit();
        }
        catch(Exception e){
            Log.e(TAG,"Exception in preferences "+e);
        }

        next = (Button) findViewById(R.id.next);

        currentLanguage = getIntent().getStringExtra(selectLang);

        spinner = (Spinner) findViewById(R.id.spinner);

        List<String> list = new ArrayList<String>();

        list.add("Select Language");
        list.add("English");
        list.add("Hindi");
        list.add("Español");
        list.add("Français");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("hi");
                        break;
                    case 3:
                        setLocale("es");
                        break;
                    case 4:
                        setLocale("fr");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        int sLang = mSharedPreferences.getInt("Lang", 0);
       // Toast.makeText(getApplicationContext(),"Pref"+sLang,Toast.LENGTH_SHORT).show();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (sLang == 1) {
                        Intent intent = new Intent(getApplicationContext(), MidActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Select Language", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){
                    Log.e(TAG,"Exception in invoking next page"+e);
                }
            }


        });
    }


    public void setLocale(String localeName) {
        try {
            if (!localeName.equals(currentLanguage)) {
                locale = new Locale(localeName);
                Resources res = getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                Configuration config = res.getConfiguration();
                config.locale = locale;
                res.updateConfiguration(config, dm);

               // SharedPreferences.Editor editor = settings.edit();
                mEditor.putInt("Lang", 1);
                mEditor.commit();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);


            } else {
                Toast.makeText(MainActivity.this, "Language selected!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Log.e(TAG,"Exception in setLocale"+e);
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}