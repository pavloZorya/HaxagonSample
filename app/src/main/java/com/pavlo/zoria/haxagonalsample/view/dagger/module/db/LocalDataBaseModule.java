package com.pavlo.zoria.haxagonalsample.view.dagger.module.db;

import android.database.sqlite.SQLiteDatabase;

import com.pavlo.zoria.haxagonalsample.domain.UserCacher;
import com.pavlo.zoria.haxagonalsample.database.UserDao;
import com.pavlo.zoria.haxagonalsample.database.UserDaoController;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DbModule.class})
public class LocalDataBaseModule {

    @Provides
    UserDaoController localRepository(UserCacher cacher) {
        return new UserDaoController(cacher);
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