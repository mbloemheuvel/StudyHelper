package com.example.mick.studyhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mick.studyhelper.Util.SetListAdapter;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("jsonArray");
        try {
            JSONArray list = new JSONArray(jsonArray);
            List<Set> setList = prepareSetData(list);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListView setList = (ListView)findViewById(R.id.set_list);
        setList.setAdapter(new SetListAdapter());
    }

    private List<Set> prepareSetData(JSONArray list) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Set> setList = new ArrayList<>();
        for(int i=0; i<list.length(); i++){
            String set = list.getString(i);
            Set setObject = mapper.readValue(set, Set.class);
            setList.add(setObject);
        }
        return setList;
    }
}
