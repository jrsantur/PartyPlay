package com.project.workgroup.partyplay.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mingle.widget.LoadingView;
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
    //@Bind(R.id.loadView)
    private static final String TAG = EventsFragment.class.getName() ;

    RecyclerView mRecyclerView;
    EventsAdapter mEventsAdapter;
    LoadingView loadingView;
    //LoadingView loadingView = new LoadingView(getContext());

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
        View rootView =  inflater.inflate(R.layout.fragment_events, container, false);
        loadingView = (LoadingView) rootView.findViewById(R.id.loadView);
        initializeRecyclerView(rootView);
        initializePresenter();

        return rootView;
    }

    public void initializeRecyclerView(View view){
        mRecyclerView = (RecyclerView)view.findViewById(R.id.fragment_event_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecyclerInsetsDecoration(getContext()));
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        Log.e("Fragment Manager","Se inicializo el frgamento");
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
        Log.e("EventsFragment", "se inicialializo el presenter");
    }


    @Override
    public void onElementClick(int position, View view  ,ImageView characterImageView) {

        Fragment detailsFragment = new EventDetailsFragment().newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.frag_slide_in_from_bottom, 0);
        transaction.replace(R.id.main_content, detailsFragment);
        transaction.commit();
        MainActivity.isCurrentFragmentChild = true;
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void bindEventList(List<Event> events) {
        mEventsAdapter = new EventsAdapter(events, getContext());
        mEventsAdapter.setmRecyclerListListener(this);
        mRecyclerView.setAdapter(mEventsAdapter);
        Log.e("EventsFragment", "Estas en el adaptador");
    }

    @Override
    public void showEventList() {
        if(mRecyclerView.getVisibility()==View.GONE || mRecyclerView.getVisibility() == View.INVISIBLE ){
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideEventsList() {
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void showLoadingView() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        loadingView.setVisibility(View.GONE);
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
