package com.project.workgroup.partyplay.injector.components;

import android.content.Context;

import com.project.workgroup.partyplay.injector.Activity;
import com.project.workgroup.partyplay.injector.modules.ActivityModule;
import com.project.workgroup.partyplay.views.activities.MainActivity;

import dagger.Component;

/**
 * Created by Junior on 11/11/2015.
 */
@Activity
@Component(dependencies = AppComponent.class , modules = {ActivityModule.class})
public interface PartyComponent  extends ActivityComponent{
    void inject (MainActivity activity ) ;

    Context activityContext();
}
