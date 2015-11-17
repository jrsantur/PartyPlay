package com.project.workgroup.partyplay.mvp.presenter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.domain.GetEventsUsecase;
import com.project.workgroup.partyplay.model.entities.Event;
import com.project.workgroup.partyplay.model.rest.exceptions.NetworkErrorException;
import com.project.workgroup.partyplay.model.rest.exceptions.ServerErrorException;
import com.project.workgroup.partyplay.mvp.views.EventsView;
import com.project.workgroup.partyplay.mvp.views.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Junior on 11/11/2015.
 */
public class EventListPresenter implements Presenter {

    public static final String TAG = EventListPresenter.class.getName();
    private final GetEventsUsecase mEventsUsecase;
    private Context mContext;
    Subscription mEventsSubscription;
    private List<Event> mEvents;
    private EventsView mEventsView;
    private boolean mIsTheCharacterRequestRunning;

    @Inject  EventListPresenter(Context context, GetEventsUsecase mEventsUsecase){
        this.mContext = context;
        this.mEventsUsecase = mEventsUsecase;
        mEvents = new ArrayList<>();
        Log.e("EventListPresenter", "Se injecto EventListPresenter ");
    }

    public void onListEndReached(){
        if(!mIsTheCharacterRequestRunning){
            //askForNewEvents();
            Log.e(TAG, "estas en onListEndReached, final de la lista");
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    private void askPorEvents() {
        mIsTheCharacterRequestRunning = true;
        showLoadingUI();
        if(mEventsUsecase==null){
            Log.e(".mEventsSubscription","es nulo");
        }
        mEventsSubscription = mEventsUsecase.execute().subscribe(events -> {
            Log.e("EventListPresenter.mEventsSubscription","se ejecuto el metodo");
            mEvents.addAll(events);
            mEventsView.bindEventList(mEvents);
            mEventsView.showEventList();
            mEventsView.hideEmptyIndicator();
            mIsTheCharacterRequestRunning = false ;
        }, this::showErrorView);

        //Log.e(TAG, "askPorEvents() called with: " + mEvents.get(0).getTitle().toString());
        hideLoadingUI();

    }

    private void showLoadingUI() {
        mEventsView.hideErrorView();
        mEventsView.showLoadingView();
    }

    private void hideLoadingUI(){
        mEventsView.hideLoadingView();
    }

    private void showErrorView(Throwable error){
        if(error instanceof NetworkErrorException){
            String errorMessage = mContext.getString(R.string.error_network_uknownhost);
            mEventsView.showErrorView(errorMessage);
        }else if(error instanceof ServerErrorException){
            String errorMessage = mContext.getString(R.string.error_network_party_server);
            mEventsView.showErrorView(errorMessage);
        }
        mEventsView.hideEmptyIndicator();
        mEventsView.hideEventsList();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {
        mEventsSubscription.unsubscribe();
        mIsTheCharacterRequestRunning = false;
    }

    @Override
    public void attachView(View v) {
        Log.e("EventListPresenter","Se adjunto la vista");
        mEventsView = (EventsView) v;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    @Override
    public void onCreate() {
        askPorEvents();
        Log.e("EventListpresenter","estas en onCreate()");
    }


}
