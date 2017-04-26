package com.example.nirbhay.major_project;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import in.nashapp.androidsummernote.Summernote;

import static com.example.nirbhay.major_project.FileListAcitivity.FOLDER;
import static com.example.nirbhay.major_project.ProjectActivity.MAIN_FOLDER;
import static com.example.nirbhay.major_project.ProjectActivity.NAME;

public class CodeActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FOR_FILEPICKER = 23;
    private static final String LOG_TAG = "ab";
    public static final String PATH_KEY = "com.example.nirbhay.major_project.PATH";
    public static final String PATH_KEY_ACT = "trainedge.major_project.PATH";

    public Summernote summernote;

    private String projectFolderName;
    private File file;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //if (checkStorage()) return;
        initProjectFolder();
        loadOldFile();
        initCodeEditor();
    }

    private void initCodeEditor() {
        summernote = (Summernote) findViewById(R.id.summernote);
        summernote.setRequestCodeforFilepicker(REQUEST_CODE_FOR_FILEPICKER);
        summernote.enable_summernote();
        summernote.setText("");
    }

    private void loadOldFile() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(NAME)) {
            projectFolderName = intent.getStringExtra(FOLDER);
            fileName = intent.getStringExtra(NAME);
            file = new File(Environment.getExternalStorageDirectory() + File.separator + MAIN_FOLDER + File.separator + projectFolderName, fileName);
            String path = file.getAbsolutePath();
            Toast.makeText(this, "file loaded", Toast.LENGTH_SHORT).show();
            loadFileContent();
        }
    }

    private void loadFileContent() {

    }

    private void initProjectFolder() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(FOLDER)) {
            projectFolderName = intent.getStringExtra(FOLDER);
        } else {
            Toast.makeText(this, "folder could not be opened", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkStorage() {
        if (!isExternalStorageReadable()) {
            Toast.makeText(this, "cannot read", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "cannot write", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getProjectStorageDir(Context context, String folder) {
        // Get the directory for the app's private pictures directory.
        File file = new File(Environment.getExternalStorageDirectory(), folder);
        if (file.exists()) {
            return file;
        }
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
        //summernote = (Summernote) findViewById(R.id.summernote);
        // summernote.setRequestCodeforFilepicker(5);
    }


    private void saveCode() {
        String fileData = summernote.getText();
        if (fileData.isEmpty()) {
            Toast.makeText(this, "file data empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                String path = new File(Environment.getExternalStorageDirectory() + File.separator + MAIN_FOLDER, projectFolderName).getAbsolutePath();
                if (file != null && file.exists()) {
                    try {
                        OutputStream outputStream = new FileOutputStream(file);
                        outputStream.write(fileData.getBytes());
                        outputStream.flush();
                        outputStream.close();
                        Toast.makeText(this, "file saved " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        Toast.makeText(this, "Error not save content", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    file = AlertDialogWithEditText.createFileAlertDialog(this, "Provide File name", path, "html", fileData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.project, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Scoop");

                share.putExtra(Intent.EXTRA_TEXT, "Your friend has invited you to join the app./n To join click the link");
                startActivity(Intent.createChooser(share, "Share via..."));
                break;
            case R.id.action_save:
                saveCode();

                break;
            case R.id.action_preview:
                try {
                    saveCode();
                    showPerviewIntent();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);

//      In Activity
//        Fragment.summernote.onActivityResult(requestCode, resultCode, intent);
    }

    private void showPerviewIntent() {
        Toast.makeText(this, "Autosaving...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PreviewActivity.class);
        intent.putExtra(PATH_KEY, file.getAbsolutePath());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        summernote.onActivityResult(requestCode, resultCode, intent);

    }
}

