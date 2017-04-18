package com.example.nirbhay.major_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import java.io.File;

import static android.R.attr.path;
import static com.example.nirbhay.major_project.NewProjectActivity.PATH_KEY;

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
