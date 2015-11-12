package com.project.workgroup.partyplay.mvp.views;

import com.project.workgroup.partyplay.model.entities.Event;

import java.util.List;

/**
 * Created by Junior on 11/11/2015.
 */
public interface EventsView extends View {

    void bindEventList(List<Event> events);
    void showEventList();
    void hideEventsList();
    void hideLoadingIndicator();
    void showLoadingView();
    void hideLoadingView();
    void showErrorView(String errorMessage);
    void hideErrorView();
    void showEmptyIndicator();
    void hideEmptyIndicator();


}
