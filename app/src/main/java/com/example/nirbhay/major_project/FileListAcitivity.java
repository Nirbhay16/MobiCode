package com.example.nirbhay.major_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static com.example.nirbhay.major_project.ProjectActivity.NAME;
import static com.example.nirbhay.major_project.ProjectActivity.PACKAGE_NAME;
import static com.example.nirbhay.major_project.ProjectActivity.POS;

public class FileListAcitivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static final String FOLDER = PACKAGE_NAME + ".folder";
    private Toolbar toolbar;
    private ListView lvFiles;
    private File projectFolder;
    private ArrayList<String> listOfFiles;
    private String projectFolderName;
    private FloatingActionButton fabNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list_acitivity);
        bindViews();
        initViews();
        initListeners();
        initProjectFolder();
        getFileList();
        displayProjectList();

    }

    private void displayProjectList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfFiles);
        lvFiles.setAdapter(adapter);
    }

    private void getFileList() {
        listOfFiles = new ArrayList<String>();
        File[] allProjectsFiles = projectFolder.listFiles();
        for (File file : allProjectsFiles) {
            if (file.isFile()) {
                listOfFiles.add(file.getName());
            }
        }
    }

    private void initProjectFolder() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(NAME)) {
            projectFolderName = intent.getStringExtra(NAME);
            projectFolder = new File(Environment.getExternalStorageDirectory() + File.separator + "my_editor", projectFolderName);
        } else {
            Toast.makeText(this, "there was some error", Toast.LENGTH_SHORT).show();
        }

    }

    private void initListeners() {
        lvFiles.setOnItemClickListener(this);
        fabNew.setOnClickListener(this);
    }

    private void initViews() {
        setSupportActionBar(toolbar);
    }

    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lvFiles = (ListView) findViewById(R.id.lvFiles);
        fabNew = (FloatingActionButton) findViewById(R.id.fabNew);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, FileListAcitivity.class);
        i.putExtra(POS, position);
        i.putExtra(NAME, listOfFiles.get(position));
        i.putExtra(FOLDER, projectFolderName);
        startActivity(i);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabNew:
                Intent i = new Intent(this,CodeActivity.class);
                i.putExtra(FOLDER, projectFolderName);
                startActivity(i);
        }
    }
}
