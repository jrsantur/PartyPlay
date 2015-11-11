package com.project.workgroup.partyplay;

import android.app.Application;

import com.project.workgroup.partyplay.injector.AppModule;
import com.project.workgroup.partyplay.injector.components.AppComponent;
import com.project.workgroup.partyplay.injector.components.DaggerAppComponent;

/**
 * Created by Junior on 11/11/2015.
 */
public class PartyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }
}
