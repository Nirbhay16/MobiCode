package com.example.nirbhay.major_project;

<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
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

    private Summernote summernote;

    private File folder;

=======
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.nashapp.androidsummernote.Summernote;

import static com.example.nirbhay.major_project.R.id.summernote;

public class NewProjectActivity extends AppCompatActivity {

    private Summernote summernote;

>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
<<<<<<< HEAD
        if (!isExternalStorageReadable()) {
            Toast.makeText(this, "cannot read", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "cannot write", Toast.LENGTH_SHORT).show();
            return;
        }
        folder = getProjectStorageDir(NewProjectActivity.this, "project_1");


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
=======
        summernote = (Summernote) findViewById(R.id.summernote);
        summernote.setRequestCodeforFilepicker(5);//Any Number which is not being used by other OnResultActivity


>>>>>>> origin/master
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        summernote.onActivityResult(requestCode, resultCode, intent);
<<<<<<< HEAD
    }


    private void saveCode() {
        String fileData = summernote.getText();
        if (fileData.isEmpty()) {
            Toast.makeText(this, "file data empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                File file = new File(folder, "test2.html");
                file.createNewFile();
                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(fileData.getBytes());
                outputStream.flush();
                outputStream.close();
                Toast.makeText(this, "file saved", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.project,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:
                // TODO ADD SHARE INTENT TO SHARE THE FILE
                break;
            case R.id.action_save:
                saveCode();
                break;
            case R.id.action_preview:
                // // TODO: 4/14/2017 ADD A COMMON INTENT FROM ANDROID DEVELOPER WEBSITE
                break;
        }
        return super.onOptionsItemSelected(item);
=======
//      In Activity
//        Fragment.summernote.onActivityResult(requestCode, resultCode, intent);
>>>>>>> origin/master
    }
}
