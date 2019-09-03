package com.nea.myjournal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JournalService {
    public static void findJournals(String content, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.USER_KEY_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.USER_CONTENT_QUERY_PARAMETER, content);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.USER_KEY)
                .build();


        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Journal> processResults(Response response) {
        ArrayList<Journal> journals = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject userJSON = new JSONObject(jsonData);
            JSONArray journalsJSON = userJSON.getJSONArray("sources");
            if (response.isSuccessful()) {
                for (int i = 0; i < journalsJSON.length(); i++) {
                    JSONObject journalJSON = journalsJSON.getJSONObject(i);
                    String id = journalJSON.getString("id");
                    String name = journalJSON.getString("name");
                    String description = journalJSON.getString("description");
                    String url = journalJSON.getString("url");
                    String category = journalJSON.getString("category");
                    String language = journalJSON.getString("language");
                    String country = journalJSON.getString("country");
                    Journal journal  = new Journal(id, name, description, url, category, language, country);
                    journals.add(journal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return journals;
    }
}