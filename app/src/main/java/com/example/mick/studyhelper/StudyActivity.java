package com.example.mick.studyhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mick.studyhelper.Model.Card;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                try {
                    List<Card> cardList = prepareSetData(terms);
                    Toast toast = Toast.makeText(this, cardList.get(0).getTerm(), Toast.LENGTH_LONG);
                    toast.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Card> prepareSetData(JSONArray list) throws JSONException, IOException {
        if(list!=null) {
            ObjectMapper mapper = new ObjectMapper();
            List<Card> cardList = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                String card = list.getString(i);
                Card cardObject = mapper.readValue(card, Card.class);
                cardList.add(cardObject);
            }
            return cardList;
        }
        return null;
    }
}
