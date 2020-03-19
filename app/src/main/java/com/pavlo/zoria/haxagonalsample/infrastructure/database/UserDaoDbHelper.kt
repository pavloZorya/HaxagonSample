package com.pavlo.zoria.haxagonalsample.infrastructure.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.pavlo.zoria.haxagonalsample.infrastructure.database.UserDao.USER_DAO_CREATE_ENTRIES
import com.pavlo.zoria.haxagonalsample.infrastructure.database.UserDao.USER_DAO_DELETE_ENTRIES
import com.pavlo.zoria.haxagonalsample.view.dagger.ApplicationContext
import com.pavlo.zoria.haxagonalsample.view.dagger.DatabaseInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDaoDbHelper @Inject constructor(
    @ApplicationContext context: Context,
    @DatabaseInfo name: String,
    @DatabaseInfo version: Int
) : SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            it.execSQL(USER_DAO_CREATE_ENTRIES)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let {
            it.execSQL(USER_DAO_DELETE_ENTRIES)
            onCreate(it)
        }
    }
}