package com.example.nirbhay.major_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import static com.example.nirbhay.major_project.CodeActivity.PATH_KEY;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        WebView webView = (WebView) findViewById(R.id.Web_View);
        String filePath = getIntent().getStringExtra(PATH_KEY);
        webView.loadUrl("file://"+filePath);

    }

}
