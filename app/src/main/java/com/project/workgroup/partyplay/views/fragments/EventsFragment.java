package com.project.workgroup.partyplay.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.model.entities.Event;
import com.project.workgroup.partyplay.mvp.views.EventsView;
import com.project.workgroup.partyplay.views.RecyclerClickListener;
import com.project.workgroup.partyplay.views.activities.MainActivity;

import java.util.List;

public class EventsFragment extends Fragment implements EventsView, RecyclerClickListener {


    public static final String ARG_SECTION_TITLE = "section_number";

    private static final String TAG = EventsFragment.class.getName() ;
    //@Inject  EventListPresenter eventListPresenter;


    public static EventsFragment newInstance(String sectionTitle) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.getString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public EventsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializePresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eents, container, false);
    }



    private void initializePresenter(){
        MainActivity.eventListPresenter.attachView(this);
        MainActivity.eventListPresenter.onCreate();
        Log.e(TAG,"Se creo el presenter");
    }


    @Override
    public void onElementClick(int position, ImageView characterImageView) {

    }

    @Override
    public void bindEventList(List<Event> events) {

    }

    @Override
    public void showEventList() {

    }

    @Override
    public void hideEventsList() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showErrorView(String errorMessage) {

    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showEmptyIndicator() {

    }

    @Override
    public void hideEmptyIndicator() {

    }
}
