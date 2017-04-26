package com.example.nirbhay.major_project;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Abc on 07-01-2017.
 */

public class AlertDialogWithEditText {
    public static File createFileAlertDialog(final Context context, String title, final String path, final String extention, final String fileData) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_project_create, null);
        dialog.setView(v);
        final File[] f = new File[1];
        final EditText etProjectName = (EditText) v.findViewById(R.id.etProjectName);
        dialog.setTitle(title);
        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = etProjectName.getText().toString().trim();
                if (!name.isEmpty()) {
                     f[0] = new File(path, "/" + name + "." + extention);
                    if (!f[0].exists()) {
                        try {
                            f[0].createNewFile();
                            OutputStream outputStream = new FileOutputStream(f[0]);
                            outputStream.write(fileData.getBytes());
                            outputStream.flush();
                            outputStream.close();
                            Toast.makeText(context, "file saved " + f[0].getAbsolutePath(), Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Already Exists..!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    etProjectName.setError("Enter text");
                }

            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.cancel();
            }
        });
        dialog.show();
        return f[0];
    }
}
