package com.project.workgroup.partyplay.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.project.workgroup.partyplay.domain.GetEventsUsecase;
import com.project.workgroup.partyplay.model.entities.Event;
import com.project.workgroup.partyplay.mvp.views.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Junior on 11/11/2015.
 */
public class EventListPresenter  implements Presenter{

    private final GetEventsUsecase mEventsUsecase;
    private Context mContext;
    Subscription mEventsSubscription;
    private List<Event> mEvents;

    @Inject EventListPresenter(Context context, GetEventsUsecase mEventsUsecase){
        this.mContext = context;
        this.mEventsUsecase = mEventsUsecase;
        mEvents = new ArrayList<>();
    }



    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {

    }



    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    @Override
    public void onCreate() {

    }
}
