package com.example.mick.studyhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mick.studyhelper.Model.Set;
import com.example.mick.studyhelper.Util.SetListAdapter;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;

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
            if(jsonArray!=null) {
            JSONArray list = new JSONArray(jsonArray);
            List<Set> setList = prepareSetData(list);

                ListView listView = (ListView) findViewById(R.id.set_list);
                SetListAdapter setListAdapter = new SetListAdapter(this, R.layout.list_row, setList);
                listView.setAdapter(setListAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Set set = (Set) parent.getItemAtPosition(position);
                        Intent detailsIntent = new Intent(ListActivity.this, DetailsActivity.class);
                        detailsIntent.putExtra("set", set);
                        ListActivity.this.startActivity(detailsIntent);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private List<Set> prepareSetData(JSONArray list) throws JSONException, IOException {
        if(list!=null) {
            ObjectMapper mapper = new ObjectMapper();
            List<Set> setList = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                String set = list.getString(i);
                Set setObject = mapper.readValue(set, Set.class);
                setList.add(setObject);
            }
            return setList;
        }
        return null;
    }
}
