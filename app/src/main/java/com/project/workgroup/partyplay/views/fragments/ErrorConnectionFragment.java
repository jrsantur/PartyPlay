package com.project.workgroup.partyplay.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.workgroup.partyplay.R;

/**
 * Created by Junior on 20/11/2015.
 */
public class ErrorConnectionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.error_connection, container, false);

        return rootView;
    }
}
