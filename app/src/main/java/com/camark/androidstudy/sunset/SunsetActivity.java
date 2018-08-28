package com.camark.androidstudy.sunset;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.camark.androidstudy.R;
import com.camark.androidstudy.criminalintent.SingleFragmentActivity;

public class SunsetActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SunsetFragment.newInstance();
    }
}
