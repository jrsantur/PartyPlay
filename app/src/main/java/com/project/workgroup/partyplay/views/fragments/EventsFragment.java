package com.project.workgroup.partyplay.views.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.model.entities.Event;
import com.project.workgroup.partyplay.mvp.presenter.EventListPresenter;
import com.project.workgroup.partyplay.mvp.views.EventsView;
import com.project.workgroup.partyplay.views.RecyclerClickListener;

import java.util.List;

import javax.inject.Inject;

public class EventsFragment extends Fragment  implements EventsView, RecyclerClickListener {


    private static final String TAG = EventsFragment.class.getName() ;
    @Inject  EventListPresenter eventListPresenter;


    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
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
        eventListPresenter.attachView(this);
        eventListPresenter.onCreate();
        Log.e(TAG,"Se inicializo el presenter");
    }

    @Override
    public void onElementClick(int position, View sharedView, ImageView characterImageView) {

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
