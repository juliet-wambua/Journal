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


import butterknife.BindView;
import butterknife.ButterKnife;

public class JournalActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;

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
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here is a list of all the journals i have created: " + location);
        Log.d("JournalActivity","In the onCreate method" );

    }
}