package com.example.mick.studyhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Set set = (Set) intent.getSerializableExtra("set");

        Toast toast = Toast.makeText(this, set.getTitle(), Toast.LENGTH_LONG);
        toast.show();
    }
}
