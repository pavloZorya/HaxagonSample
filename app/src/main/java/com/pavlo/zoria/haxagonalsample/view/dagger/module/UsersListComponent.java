package com.pavlo.zoria.haxagonalsample.view.dagger.module;

import com.pavlo.zoria.haxagonalsample.view.dagger.module.application.ApplicationComponent;
import com.pavlo.zoria.haxagonalsample.view.user.UsersListContract;

import dagger.Component;

@Component(modules = UsersListFragmentModule.class, dependencies = ApplicationComponent.class)
@UserListFragmentScope
public interface UsersListComponent {
    UsersListContract.Presenter getPresenter();
}