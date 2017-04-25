package com.example.nirbhay.major_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher{
    Button button;
    private EditText erfeed, ermail;
    int rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                erfeed = (EditText) findViewById(R.id.et_comment);
                ermail = (EditText) findViewById(R.id.et_email);
                Button submit = (Button) findViewById(R.id.bt_submit);

                submit.setOnClickListener(this);
                ermail.addTextChangedListener(this);

            }

            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.bt_submit) {
                    submitf();
                    Toast.makeText(getBaseContext(),"Your Review has been submitted!",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(FeedbackActivity.this,ProjectActivity.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                if (email.isEmpty() || email.length() < 10 || !email.contains("@") || !email.contains(".com")) {
                    ermail.setError("Please give a correct email address.");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            private void submitf() {
                String str1 = erfeed.getText().toString();
                String str2 = ermail.getText().toString();

                if (str1.isEmpty()) {
                    erfeed.setError("Required");
                    return;
                }
                if (!str2.contains("@") || str2.isEmpty() || str2.length() < 10) {
                    ermail.setError("Please enter a valid email address.");
                    return;
                }
                Intent emailint = new Intent(Intent.ACTION_SEND);
                emailint.setType("text/html");
                emailint.putExtra(Intent.EXTRA_EMAIL, new String[]{"nirbhaysingh1694@gmail.com"});
               // emailint.putExtra(Intent.EXTRA_EMAIL, new String[]{"dixitprashant762@gmail.com"});
                emailint.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                emailint.putExtra(Intent.EXTRA_TEXT, "Hi,\n \t You have got a feedback. And user's thought about the app are - \"" +
                        "" + erfeed.getText().toString() + "\". To write him back please use the email \"" +
                        "" + ermail.getText().toString() + "\". \n\tHave a nice day. \n\tThank You.");
                startActivity(Intent.createChooser(emailint, "Send feedback using..."));
                erfeed.setText("");
                ermail.setText("");
            }
}


