package com.project.workgroup.partyplay.domain;

import android.util.Log;

import com.project.workgroup.partyplay.model.entities.Event;
import com.project.workgroup.partyplay.model.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Junior on 11/11/2015.
 */
public class GetEventsUsecase implements Usecase {

    public static final String TAG = GetEventsUsecase.class.getName();

    public final static int EVENTS_LIMIT = 50;
    private final Repository mRepository;

    @Inject public GetEventsUsecase(Repository repository){
        mRepository = repository;
        Log.e(TAG, "se injecto GetEventsUsecase");
    }

    @Override
    public Observable<List<Event>> execute() {
        return mRepository.getEvents()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
