package com.example.nirbhay.major_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appinvite.AppInviteReferral;

public class DeepLinkActivity extends AppCompatActivity {
    private static final String TAG = "DeepLinkActivity";

    Button button_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button_ok= (Button) findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
finish();

            }

        });


    }
    @Override
    public void onStart() {
        super.onStart();
        Intent intent=getIntent();
        if(AppInviteReferral.hasReferral(intent)){
            processReferralIntent(intent);

        }

    }

    private void processReferralIntent(Intent intent) {
        String invitationId=AppInviteReferral.getInvitationId(intent);
        String deeplink=AppInviteReferral.getDeepLink(intent);
        Log.d(TAG,"found referral:"+invitationId+":"+deeplink);
        ((TextView) findViewById(R.id.Deep_link_text))
        .setText("Deep link: {deepLink}");
        ((TextView) findViewById(R.id.invitation_id_text)).setText("Invitation Id: {invitationId}");
    }


}
