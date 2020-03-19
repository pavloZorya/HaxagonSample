package com.pavlo.zoria.haxagonalsample.view.dagger.module.application;

import com.pavlo.zoria.haxagonalsample.HexagonArchitectureApplication;
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort;
import com.pavlo.zoria.haxagonalsample.view.dagger.module.db.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,
        RepositoryModule.class
})
public interface ApplicationComponent {

    void inject(HexagonArchitectureApplication application);

    UserInfrastructurePort getUserRepository();
}