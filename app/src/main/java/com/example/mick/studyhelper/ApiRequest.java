package com.example.mick.studyhelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Mick on 21-Jun-16.
 */
public class ApiRequest {
    public JSONArray getJSONFromUrl(String url){
        JSONArray result = null;
        try {
            URL request = new URL(url);
            HttpsURLConnection urlConnection = (HttpsURLConnection)request.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private JSONArray readStream(InputStream in) {
        JSONArray result = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while((line = reader.readLine())!=null) {
                builder.append(line);
            }
            try {
                JSONObject jsonResponse = new JSONObject(builder.toString());
                result = jsonResponse.getJSONArray("sets");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
