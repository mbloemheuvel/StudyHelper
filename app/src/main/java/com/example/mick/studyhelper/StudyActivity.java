package com.example.mick.studyhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        intent.getStringExtra("jsonArray");
        String jsonArray = intent.getStringExtra("jsonArray");
        if (jsonArray != null) {
            try {
                JSONArray terms = new JSONArray(jsonArray);
                Toast toast = Toast.makeText(this,terms.toString(), Toast.LENGTH_LONG);
                toast.show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
