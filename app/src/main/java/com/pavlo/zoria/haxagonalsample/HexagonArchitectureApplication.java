package com.pavlo.zoria.haxagonalsample;

import android.app.Application;
import android.content.Context;

import com.pavlo.zoria.haxagonalsample.view.dagger.module.application.ApplicationComponent;
import com.pavlo.zoria.haxagonalsample.view.dagger.module.application.ApplicationModule;
import com.pavlo.zoria.haxagonalsample.view.dagger.module.application.DaggerApplicationComponent;

public class HexagonArchitectureApplication extends Application {

    static ApplicationComponent applicationComponent;

    public static HexagonArchitectureApplication get(Context context) {
        return (HexagonArchitectureApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        DaggerRepositoryComponent.builder().build();
//        DaggerApplicationComponent

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
//        applicationComponent.inject(this);
    }

    public static ApplicationComponent getComponent() {
        return applicationComponent;
    }
}