package com.project.workgroup.partyplay.model.rest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.workgroup.partyplay.model.entities.Event;
import com.project.workgroup.partyplay.model.repository.Repository;
import com.project.workgroup.partyplay.model.rest.exceptions.ServerErrorException;
import com.project.workgroup.partyplay.model.rest.exceptions.UknownErrorException;
import com.project.workgroup.partyplay.model.rest.utils.deserializers.PartyResultEventDeserialiser;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import javax.inject.Inject;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;


/**
 * Created by Junior on 10/11/2015.
 */
public class RestRepository implements Repository {


    private static final String TAG = RestRepository.class.getName();
    private final PartyApi mPartyApi;
    public final static int MAX_ATTEMPTS = 3;


    @Inject public RestRepository(){
        OkHttpClient cliente = new OkHttpClient();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<Event>>() {}.getType(), new PartyResultEventDeserialiser())
                .create();

        Retrofit partyApiAdapter =  new Retrofit.Builder()
                .baseUrl(PartyApi.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(cliente).build();


        mPartyApi = partyApiAdapter.create(PartyApi.class);

        if(mPartyApi!=null){
            Log.e(TAG,"Se inyecto el repositorio :D ");
        }


    }

    @Override
    public Observable<Event> getEvent(int eventId) {
        return mPartyApi.getEventById(eventId).flatMap(
                event -> Observable.just(event.get(0)));
    }

    @Override
    public Observable<List<Event>> getEvents() {

        return mPartyApi.getEvents()
                .onErrorResumeNext(throwable -> {
                    boolean serverError = throwable.getMessage().equals(HttpErrors.SERVER_ERROR);
                    Log.e("error obtubiendo eventos", "" + throwable.toString());
                    return Observable.error((serverError) ? new ServerErrorException() : new UknownErrorException());
                });

    }


}
