package com.pavlo.zoria.haxagonalsample.view.dagger.module.application;

import android.app.Application;
import android.content.Context;

import com.pavlo.zoria.haxagonalsample.view.dagger.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }

}