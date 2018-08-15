package com.camark.androidstudy.testsetarguments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.camark.androidstudy.R;

public class FramentTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {

            fragment = new TestFragment("param");
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

    public static class TestFragment extends Fragment {
        private static final String ARG = "arg";
        private String mArg = "non-param";

        public TestFragment() {
            Log.i("INFO", "TestFragment non-parameter constructor");
        }

        public TestFragment(String arg){
            mArg = arg;
            Log.i("INFO", "TestFragment construct with parameter");
        }

        public static Fragment newInstance(String arg){
            TestFragment fragment = new TestFragment();
            Bundle bundle = new Bundle();
            bundle.putString( ARG, arg);
            fragment.setArguments(bundle);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_frament_test, container,false);
            TextView tv = rootView.findViewById(R.id.tv);
            tv.setText(mArg);
            return rootView;
        }
    }

}
