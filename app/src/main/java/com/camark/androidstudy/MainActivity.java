package com.camark.androidstudy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.camark.androidstudy.beatbox.BeatBoxActivity;
import com.camark.androidstudy.com.camark.geoquiz.QuizActivity;
import com.camark.androidstudy.criminalintent.CrimeActivity;
import com.camark.androidstudy.criminalintent.CrimeListActivity;
import com.camark.androidstudy.testsetarguments.FramentTestActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_WRITE = 1;//申请权限的请求码
    private static final int REQUEST_READ_CONTACTS = 2;//申请读取联系人

    private Button mGeoQuizButton;
    private Button mCrimeButton;
    private Button mSetArgumentsButton;
    private Button mBeatboxButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasWriteExternalStoragePermission()) {
            if (hasReadContactsPermission()) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
            }


        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE);
        }


        mGeoQuizButton = (Button) findViewById(R.id.geo_quiz_button);

        mGeoQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        mCrimeButton = (Button) findViewById(R.id.crime_button);

        mCrimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrimeListActivity.class);
                startActivity(intent);
            }
        });

        mSetArgumentsButton = (Button) findViewById(R.id.set_arguments_button);
        mSetArgumentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FramentTestActivity.class);
                startActivity(intent);
            }
        });

        mBeatboxButton = (Button) findViewById(R.id.beat_box_button);
        mBeatboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BeatBoxActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean hasWriteExternalStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean hasReadContactsPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_WRITE:
                if (hasWriteExternalStoragePermission()) {
                    if (hasReadContactsPermission()) {

                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
                    }
                } else {
                    Toast.makeText(this, "需要读写SD卡权限!", 1).show();
                }
                break;
            case REQUEST_READ_CONTACTS:
                if (hasReadContactsPermission()) {
                } else {
                    Toast.makeText(this, "需要读取联系人权限!", 1).show();
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
