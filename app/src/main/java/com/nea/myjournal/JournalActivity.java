package com.nea.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JournalActivity extends AppCompatActivity {
    public static final String TAG = JournalActivity.class.getSimpleName();
    @BindView(R.id.contentView) TextView mContentTextView;
    @BindView(R.id.listView) ListView mListView;
    public ArrayList<Journal> journals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String content = intent.getStringExtra("mimi");
        mContentTextView.setText("Here is a list of all the journals created: " );
        getJournals(content);

    }
    private void getJournals(String content){
        final JournalService journalService = new JournalService();
        JournalService.findJournals(content, new Callback() {

            @Override
            public void onFailure(Call call, IOException e){
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                journals = journalService.processResults(response);
                JournalActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] journalNames = new String[journals.size()];
                        for (int i = 0; i < journalNames.length; i++) {
                            journalNames[i] = journals.get(i).getCategory();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(JournalActivity.this, android.R.layout.simple_list_item_1, journalNames);
                        mListView.setAdapter(adapter);
                        for (Journal journal : journals) {
                            Log.d(TAG, "Source: " + journal.getId());
                            Log.d(TAG, "Content: " + journal.getName());
                            Log.d(TAG, "Author: " + journal.getDescription());
                            Log.d(TAG, "Title: " + journal.getUrl());
                            Log.d(TAG, "Description: " + journal.getCategory());
                            Log.d(TAG, "Url: " + journal.getLanguage());
                            Log.d(TAG, "UrlToImage: " + journal.getCountry());
                        }
                    }
                });
            }
        });
    }

}