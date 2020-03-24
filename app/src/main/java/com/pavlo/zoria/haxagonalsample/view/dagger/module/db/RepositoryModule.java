package com.pavlo.zoria.haxagonalsample.view.dagger.module.db;

import com.pavlo.zoria.haxagonalsample.domain.UserLoader;
import com.pavlo.zoria.haxagonalsample.infrastructure.combined.CombinedUserAdapter;
import com.pavlo.zoria.haxagonalsample.database.UserDaoController;
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserGeneratorController;
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserGeneratorPort;
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort;
import com.pavlo.zoria.haxagonalsample.view.dagger.module.application.ApplicationModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {LocalDataBaseModule.class, ApplicationModule.class})
public class RepositoryModule {

    @Provides
    public UserInfrastructurePort provideRepository(UserDaoController dao,
                                                    UserGeneratorController generator) {
        return new CombinedUserAdapter(dao, generator);
    }

    @Provides
    public UserLoader userLoader(UserGeneratorPort generator) {
        return new UserLoader(generator);
    }

    @Provides
    public UserGeneratorPort userGenerator() {
        return new UserGeneratorPort();
    }
}