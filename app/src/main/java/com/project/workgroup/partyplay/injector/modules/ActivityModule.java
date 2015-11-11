package com.project.workgroup.partyplay.injector.modules;

import android.content.Context;

import com.project.workgroup.partyplay.injector.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Junior on 11/11/2015.
 */
@Module
public class ActivityModule {

    private final Context mContext;

    public ActivityModule(Context mContext){
        this.mContext = mContext;
    }
    @Provides @Activity
    Context providerActivityContext(){
        return mContext;
    }
}
