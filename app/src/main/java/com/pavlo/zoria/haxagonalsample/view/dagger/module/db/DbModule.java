package com.pavlo.zoria.haxagonalsample.view.dagger.module.db;

import android.database.sqlite.SQLiteDatabase;

import com.pavlo.zoria.haxagonalsample.database.UserDaoDbHelper;
import com.pavlo.zoria.haxagonalsample.view.dagger.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @DatabaseInfo
    public String provideDatabaseName() {
        return "user-hexagon.db";
    }

    @Provides
    @DatabaseInfo
    public int provideDatabaseVersion() {
        return 1;
    }

    @Provides
    public SQLiteDatabase provideWritableDatabase(UserDaoDbHelper sqLiteDatabase) {
        return sqLiteDatabase.getWritableDatabase();
    }

}