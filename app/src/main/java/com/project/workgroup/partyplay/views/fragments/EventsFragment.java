package com.project.workgroup.partyplay.views.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.mvp.views.EventsView;
import com.project.workgroup.partyplay.views.RecyclerClickListener;

public class EventsFragment extends Fragment  implements EventsView, RecyclerClickListener {


    //@Inject  EventListPresenter eventListPresenter;


    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public EventsFragment() {
        // Required empty public constructor
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

    /*
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
    public void hideLoadingIndicador() {

    }

    @Override
    public void showLoadinView() {

    }

    @Override
    public void hideLoadinView() {

    }

    @Override
    public void showLightError() {

    }

    @Override
    public void showErrorView(String errorMenssage) {

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

    @Override
    public void updateEventList(int eventsLimit) {

    }

    @Override
    public ActivityOptions getActivityOptions(int position, View clickView) {
        return null;
    }

    @Override
    public void onElementClick(int position, View sharedView, ImageView characterImageView) {

    }
    */

    private void initializePresenter(){
        //eventListPresenter.attachView(this);
        //eventListPresenter.onCreate();
    }

    @Override
    public void onElementClick(int position, View sharedView, ImageView characterImageView) {

    }
}
