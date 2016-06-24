package com.example.mick.studyhelper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mick.studyhelper.Util.Util;

import org.json.JSONArray;

public class DetailsActivity extends AppCompatActivity {

    String apiUrl;
    String clientId;
    String request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Set set = (Set) intent.getSerializableExtra("set");

        readProperties();
        buildRequest(set.getId());
        TextView details = (TextView) findViewById(R.id.details);
        details.setText(set.toString());
    }

    private void readProperties() {
        try{
            apiUrl = Util.getProperty("apiurl_details", getApplicationContext());
            clientId = Util.getProperty("client_id", getApplicationContext());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void buildRequest(int id){
        EditText searchbox = (EditText) findViewById(R.id.search_field);
        StringBuilder builder = new StringBuilder();
        //Uri.Builder builder = new Uri.Builder();
        builder.append("https://")
                .append(apiUrl)
                .append("/"+id)
                .append("?client_id="+clientId);

        request = builder.toString();
    }

    public void getDetails(View view){
        Context context = this.getApplicationContext();
        new CallApi(context).execute();
    }


    private class CallApi extends AsyncTask<String, Integer, String> {
        Context context;
        private CallApi(Context context){
            this.context = context.getApplicationContext();
        }

        @Override
        protected String doInBackground(String... params) {
            ApiRequest apiRequest = new ApiRequest();
            JSONArray jsonFromUrl = apiRequest.getJSONFromUrl(request);
            if(jsonFromUrl!=null) {
                return jsonFromUrl.toString();
            }
            return null;

        }
    }
}
