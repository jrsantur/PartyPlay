package com.project.workgroup.partyplay.injector;

import com.project.workgroup.partyplay.PartyApplication;
import com.project.workgroup.partyplay.model.repository.Repository;
import com.project.workgroup.partyplay.model.rest.RestRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Junior on 11/11/2015.
 */
@Module
public class AppModule {
    private final PartyApplication mPartyApplication;

    public AppModule(PartyApplication partyApplication){
        this.mPartyApplication = partyApplication;
    }
    @Provides @Singleton
    PartyApplication providePartyApplicarion(){
        return mPartyApplication;
    }
    @Provides @Singleton Repository provideDataRepository(RestRepository restRepository){
        return restRepository;
    }

}
