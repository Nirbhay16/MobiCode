package com.example.nirbhay.major_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DialogActivity extends AppCompatActivity {

    private EditText filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        filename = (EditText) findViewById(R.id.filename);
    }
}
