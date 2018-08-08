package com.camark.androidstudy.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camark.androidstudy.R;
import com.camark.androidstudy.databinding.FragmentBeatBoxBinding;

/**
 * Created by camark on 2018-08-09.
 */

public class BeatBoxFragment extends Fragment {
    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        return binding.getRoot();
    }
}
