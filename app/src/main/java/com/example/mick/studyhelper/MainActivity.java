package com.example.mick.studyhelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mick.studyhelper.Util.Util;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    String apiUrl;
    String clientId;
    String request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        if (!isNetworkConnected()) {
            new AlertDialog.Builder(this)
                    .setTitle("No internet connection!")
                    .setMessage("An internet connection is required, do you want to go to settings?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
        }

        readProperties();
    }

    private void readProperties() {
        try{
            apiUrl = Util.getProperty("apiurl_search", getApplicationContext());
            clientId = Util.getProperty("client_id", getApplicationContext());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void buildRequest(){
        EditText searchbox = (EditText) findViewById(R.id.search_field);
        StringBuilder builder = new StringBuilder();
        //Uri.Builder builder = new Uri.Builder();
        builder.append("https://")
                .append(apiUrl)
                .append("?q=" + searchbox.getText().toString().replace(" ", "%20"))
                .append("&per_page=50")
                .append("&sort=most_studied")
                .append("&client_id="+clientId);

        request = builder.toString();
    }

    public void getLists(View view){
        Context context = this.getApplicationContext();
        EditText searchbox = (EditText) findViewById(R.id.search_field);
        if(searchbox.getText().length() < 3){

        }else{
            buildRequest();
            new CallApi(context).execute();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

    private class CallApi extends AsyncTask<String, Integer, String> {
        Context context;
        private CallApi(Context context){
            this.context = context.getApplicationContext();
        }

        @Override
        protected String doInBackground(String... params) {
            ApiRequest apiRequest = new ApiRequest();
            JSONArray jsonFromUrl = apiRequest.getJSONFromUrl(request, "sets");
            if(jsonFromUrl==null){
                return "Geen resultaat";
            }else {
                return jsonFromUrl.toString();
            }
        }

        protected void onPostExecute(String result){
            //Start new activity from here.
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            intent.putExtra("jsonArray", result);
            MainActivity.this.startActivity(intent);
        }
    }
}
