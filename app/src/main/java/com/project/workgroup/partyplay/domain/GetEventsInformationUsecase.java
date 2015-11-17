package com.project.workgroup.partyplay.domain;


import android.util.Log;

import com.project.workgroup.partyplay.model.entities.Event;
import com.project.workgroup.partyplay.model.repository.Repository;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Junior on 11/11/2015.
 */
public class GetEventsInformationUsecase implements Usecase<Event> {

    public static final String TAG = GetEventsInformationUsecase.class.getName();
    private final Repository mRepository;
    private int mEventId;

    @Inject
    public GetEventsInformationUsecase(int mEventId, Repository mRepository){
        this.mEventId = mEventId;
        this.mRepository = mRepository;

        Log.e(TAG, "se inyecto GetEventsInformationUsecas ");
    }


    @Override
    public Observable<Event> execute() {
        return mRepository.getEvent(mEventId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
