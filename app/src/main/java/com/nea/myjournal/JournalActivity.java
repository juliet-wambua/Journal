package com.nea.myjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JournalActivity extends AppCompatActivity {
    private static final String TAG = "JournalActivity";
    @BindView(R.id.contentView) TextView mContentTextView;
    @BindView(R.id.listView) ListView mListView;
    public ArrayList<Journal> mJournals = new ArrayList<>();

    private String[] journals = new String[] {"A trip to Mwanza","First day at Moringa School", "My Love life"};
    private String[]  About = new String[] {"Tanzania", "Moringa", "Queen of Hearts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, journals);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String journal = ((TextView)view).getText().toString();
                Toast.makeText(JournalActivity.this, journal, Toast.LENGTH_LONG).show();
                Log.v("JournalActivity", "In the onItemClickListener!");
            }
        });

        Intent intent = getIntent();
        String content = intent.getStringExtra("location");
        mContentTextView.setText("Here is a list of all the journals i have created: " );
        Log.d("JournalActivity","In the onCreate method" );
        getJournals(content);

    }
    private void getJournals(String content){
        final JournalService journalService = new JournalService();
        journalService.findJournals(content, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                        mJournals = journalService.processResults(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}