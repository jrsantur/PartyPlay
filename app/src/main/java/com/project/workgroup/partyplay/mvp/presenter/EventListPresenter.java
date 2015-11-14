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
public class EventListPresenter  implements Presenter{

    public static final String TAG = EventListPresenter.class.getName();
    private final GetEventsUsecase mEventsUsecase;
    private Context mContext;
    Subscription mEventsSubscription;
    private List<Event> mEvents;
    private EventsView mEventsView;
    private boolean mIsTheCharacterRequestRunning;

    @Inject EventListPresenter(Context context, GetEventsUsecase mEventsUsecase){
        this.mContext = context;
        this.mEventsUsecase = mEventsUsecase;
        mEvents = new ArrayList<>();
        Log.e("EventListPresenter", "Se injecto EventListPresenter ");
    }

    @Override
    public void onCreate() {
        askPorEvents();
        Log.e("EventListpresenter","estas en onCreate()");
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

    public void onListEndReached(){
        if(!mIsTheCharacterRequestRunning){
            //askForNewEvents();
            Log.e(TAG, "estas en onListEndReached, final de la lista");
        }
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }
    @SuppressWarnings("Convert2MethodRef")
    private void askPorEvents() {
        mIsTheCharacterRequestRunning = true;
        showLoadingUI();
        mEventsSubscription = mEventsUsecase.execute().subscribe(events -> {
            Log.e("EventListPresenter.mEventsSubscription","se ejecuto el metodo");
            mEvents.addAll(events);
            mEventsView.bindEventList(mEvents);
            mEventsView.showEventList();
            mEventsView.hideEmptyIndicator();
            mIsTheCharacterRequestRunning = false ;
        }, this::showErrorView);
        if(mEventsSubscription==null || mEventsUsecase == null ){
            Log.e("EventListPresenter","mEventsSubscription es nulo");
        }

        Log.e("EventListPresenter","Preguntando por los eventos");
    }

    private void showLoadingUI() {
        mEventsView.hideErrorView();
        mEventsView.showLoadingView();
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

}
