package com.camark.androidstudy.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2018-07-27.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
