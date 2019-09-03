package com.nea.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Button mFindJournalsButton;
    @BindView(R.id.locationEditText)
    EditText mArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFindJournalsButton = (Button) findViewById(R.id.findJournalsButton);
        mFindJournalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JournalActivity.class);
                intent.putExtra("mimi", mArticle.getText().toString());
                startActivity(intent);
            }
        });
    }
}
