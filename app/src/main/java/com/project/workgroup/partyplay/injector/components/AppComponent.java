package com.project.workgroup.partyplay.injector.components;

import com.project.workgroup.partyplay.PartyApplication;
import com.project.workgroup.partyplay.injector.AppModule;
import com.project.workgroup.partyplay.model.repository.Repository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Junior on 11/11/2015.
 */
@Singleton @Component(modules = AppModule.class)
public interface AppComponent {
    PartyApplication app();
    Repository dataRepository();
}
