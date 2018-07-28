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

import com.camark.androidstudy.com.camark.geoquiz.QuizActivity;
import com.camark.androidstudy.criminalintent.CrimeActivity;
import com.camark.androidstudy.criminalintent.CrimeListActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_WRITE = 1;//申请权限的请求码

    private Button mGeoQuizButton;
    private Button mCrimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasWriteExternalStoragePermission()) {

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
    }

    private boolean hasWriteExternalStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_WRITE:
                if (hasWriteExternalStoragePermission()) {

                } else {
                    Toast.makeText(this, "需要读写SD卡权限!", 1).show();
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
