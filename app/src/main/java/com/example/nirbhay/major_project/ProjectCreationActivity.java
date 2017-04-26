package com.example.nirbhay.major_project;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class ProjectCreationActivity extends AppCompatActivity {

    public static final String PACKAGE_NAME = "com.example.nirbhay.major_project";
    public static final String HAS_FOLDER_NAME = "com.example.nirbhay.major_project";
    public static final String MAIN_FOLDER = "my_editor";
    private EditText filename;
    private EditText etProjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_creation);
        Button btn = (Button) findViewById(R.id.btnCreate);
        etProjectName = (EditText) findViewById(R.id.etProjectName);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createrProjectFolder(v);
            }
        });
    }

    private void createrProjectFolder(View v) {

        if (!isExternalStorageReadable()) {
            Toast.makeText(this, "cannot read", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "cannot write", Toast.LENGTH_SHORT).show();
            return;
        }

        String folderName = etProjectName.getText().toString();
        if (folderName.isEmpty()) {
            folderName = "Untitled_" + System.currentTimeMillis();
        }
        File folder = getProjectStorageDir(ProjectCreationActivity.this, folderName);

        gotoHome(folder);
    }

    private void gotoHome(File folder) {
        Intent i = new Intent(this, ProjectActivity.class);
        i.putExtra(PACKAGE_NAME, folder.getAbsolutePath());
        i.putExtra(HAS_FOLDER_NAME, true);
        startActivity(i);
        finish();
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public File getProjectStorageDir(Context context, String folder) {
        // Get the directory for the app's private pictures directory.

        File file = new File(Environment.getExternalStorageDirectory()+File.separator+ MAIN_FOLDER, folder);
        if (file.exists()) {
            return file;
        }
        if (!file.mkdirs()) {
            Toast.makeText(context, "Directory could not created", Toast.LENGTH_SHORT).show();
        }
        return file;
    }
}
