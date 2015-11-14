package com.project.workgroup.partyplay.model.repository;


import com.project.workgroup.partyplay.model.entities.Event;
import java.util.List;
import rx.Observable;

/**
 * Created by Junior on 10/11/2015.
 */
public interface Repository {

    Observable<Event> getEvent(final int eventId);
    Observable<List<Event>> getEvents();
}
