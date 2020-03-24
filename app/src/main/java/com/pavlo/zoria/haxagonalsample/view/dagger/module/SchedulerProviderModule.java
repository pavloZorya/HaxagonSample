package com.pavlo.zoria.haxagonalsample.view.dagger.module;

import com.pavlo.zoria.haxagonalsample.utils.BaseSchedulerProvider;
import com.pavlo.zoria.haxagonalsample.utils.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerProviderModule {

    @Provides
    public BaseSchedulerProvider getSchedulerProvider() {
        return new SchedulerProvider();
    }
}
