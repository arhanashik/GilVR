package com.rujel.gilvr.gilvr.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rujel.gilvr.R;

import yanzhikai.textpath.SyncPathView;
import yanzhikai.textpath.TextPathView;

import static com.rujel.gilvr.util.helper.AppConstant.REQUEST_CODE;

public class InitialActivity extends AppCompatActivity {

    private TextPathView mPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        mPathView = findViewById(R.id.stpv_2017);
        mPathView.startAnimation(0, 1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkPermission()) {
                    startActivity(new Intent(InitialActivity.this, MainActivity.class));
                    finish();
                }
                else requestPermission();
            }
        }, 4000);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) requestPermission();
                else {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
        }
    }

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
    }
}
