package com.project.workgroup.partyplay.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.project.workgroup.partyplay.views.adapter.EventsAdapter;
import com.project.workgroup.partyplay.views.custom_views.RecyclerInsetsDecoration;

import java.util.List;

public class EventsFragment extends Fragment implements EventsView, RecyclerClickListener {


    public static final String ARG_SECTION_TITLE = "section_number";

    private static final String TAG = EventsFragment.class.getName() ;

    RecyclerView mRecyclerView;

    EventsAdapter mEventsAdapter;

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
    public void onStop() {
        super.onStop();
        MainActivity.eventListPresenter.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MainActivity.eventListPresenter.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.eventListPresenter.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_eents, container, false);
        initializeRecyclerView(rootView);

        return rootView;
    }

    public void initializeRecyclerView(View view){
        mRecyclerView = (RecyclerView)view.findViewById(R.id.fragment_event_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecyclerInsetsDecoration(getContext()));
        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleItemsCount   = layoutManager.getChildCount();
            int totalItemsCount     = layoutManager.getItemCount();
            int firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition();

            if (visibleItemsCount + firstVisibleItemPos >= totalItemsCount) {
                MainActivity.eventListPresenter.onListEndReached();
            }
        }
    };


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
        mEventsAdapter = new EventsAdapter(events, getContext());
        mEventsAdapter.setmRecyclerListListener(this);
        mRecyclerView.setAdapter(mEventsAdapter);
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
