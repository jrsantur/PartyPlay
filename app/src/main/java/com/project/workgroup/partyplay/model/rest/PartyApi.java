package com.project.workgroup.partyplay.model.rest;


import com.project.workgroup.partyplay.model.entities.Event;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Junior on 10/11/2015.
 */
public interface PartyApi {

    String END_POINT        = "piuradivierte.hol.es/PiuraDivierte/index.php/";
    String PARAM_API_KEY    = "apikey";
    String PARAM_HASH       = "hash";
    String PARAM_TIMESTAMP  = "ts";


    @GET("/events")
    Observable<List<Event>> getEvents();

    @GET("/event/{idEvent}")
    Observable<List<Event>> getEventById(@Query("id") int id);

}
