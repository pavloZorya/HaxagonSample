package com.pavlo.zoria.haxagonalsample.view.dagger.module.db;

import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort;
import com.pavlo.zoria.haxagonalsample.view.dagger.module.application.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class, ApplicationModule.class})
public interface RepositoryComponent {

    UserInfrastructurePort getUserRepository();
}