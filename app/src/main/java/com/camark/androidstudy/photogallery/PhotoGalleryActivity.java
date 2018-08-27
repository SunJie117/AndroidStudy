package com.camark.androidstudy.photogallery;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.camark.androidstudy.R;
import com.camark.androidstudy.criminalintent.SingleFragmentActivity;

public class PhotoGalleryActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, PhotoGalleryActivity.class);

    }


    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }

}
