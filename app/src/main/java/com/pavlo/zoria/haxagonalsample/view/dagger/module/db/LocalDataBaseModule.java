package com.pavlo.zoria.haxagonalsample.view.dagger.module.db;

import android.database.sqlite.SQLiteDatabase;

import com.pavlo.zoria.haxagonalsample.domain.UserCacher;
import com.pavlo.zoria.haxagonalsample.infrastructure.database.UserDao;
import com.pavlo.zoria.haxagonalsample.infrastructure.database.UserDaoAdapter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DbModule.class})
public class LocalDataBaseModule {

    @Provides
    UserDaoAdapter localRepository(UserCacher cacher) {
        return new UserDaoAdapter(cacher);
    }

    @Provides
    UserDao localDao(SQLiteDatabase writableDatabase) {
        return new UserDao(writableDatabase);
    }

    @Provides
    UserCacher userCacher(UserDao dao) {
        return new UserCacher(dao);
    }
}