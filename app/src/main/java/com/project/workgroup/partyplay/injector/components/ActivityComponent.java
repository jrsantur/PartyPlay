package com.project.workgroup.partyplay.injector.components;

import android.content.Context;

import com.project.workgroup.partyplay.injector.Activity;
import com.project.workgroup.partyplay.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Junior on 11/11/2015.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Context context();
}
