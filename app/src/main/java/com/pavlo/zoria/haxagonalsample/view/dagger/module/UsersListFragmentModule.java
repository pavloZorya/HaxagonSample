package com.pavlo.zoria.haxagonalsample.view.dagger.module;

import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort;
import com.pavlo.zoria.haxagonalsample.view.user.UsersListContract;
import com.pavlo.zoria.haxagonalsample.view.user.UsersListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersListFragmentModule {

    private final UsersListContract.View view;

    public UsersListFragmentModule(UsersListContract.View view) {
        this.view = view;
    }

    @Provides
    @UserListFragmentScope
    public UsersListContract.Presenter provideDetailPresenter(UserInfrastructurePort repository) {
        return new UsersListPresenter(view, repository);
    }
}
