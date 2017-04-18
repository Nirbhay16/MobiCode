package com.example.nirbhay.major_project;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import in.nashapp.androidsummernote.Summernote;

public class NewProjectActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FOR_FILEPICKER = 23;
    private static final String LOG_TAG = "ab";
    public static final String PATH_KEY = "com.example.nirbhay.major_project.PATH";

    public Summernote summernote;

    private File folder;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!isExternalStorageReadable()) {
            Toast.makeText(this, "cannot read", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "cannot write", Toast.LENGTH_SHORT).show();
            return;
        }
        folder = getProjectStorageDir(NewProjectActivity.this, "project_2");


        summernote = (Summernote) findViewById(R.id.summernote);
        summernote.setRequestCodeforFilepicker(REQUEST_CODE_FOR_FILEPICKER);
        summernote.enable_summernote();
        summernote.setText("<h2>Hello World.<h2><br><h3> I'am Summernote</h3>");
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
                file = new File(folder, "test3.html");
                file.createNewFile();
                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(fileData.getBytes());
                outputStream.flush();
                outputStream.close();
                Toast.makeText(this, "file saved " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
                }catch (Exception e){
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
        Intent intent = new Intent(this,PreviewActivity.class);
        intent.putExtra(PATH_KEY,file.getAbsolutePath());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        summernote.onActivityResult(requestCode, resultCode, intent);

    }
}

