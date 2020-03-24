package com.pavlo.zoria.haxagonalsample.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pavlo.zoria.haxagonalsample.domain.model.User;
import com.pavlo.zoria.haxagonalsample.domain.port.UserDaoPort;
import com.pavlo.zoria.haxagonalsample.database.model.UserDaoModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.pavlo.zoria.haxagonalsample.database.model.UserDaoModel.COLUMN_NAME_PROFILE_IMAGE;
import static com.pavlo.zoria.haxagonalsample.database.model.UserDaoModel.COLUMN_NAME_USER_NAME;
import static com.pavlo.zoria.haxagonalsample.database.model.UserDaoModel.ID;
import static com.pavlo.zoria.haxagonalsample.database.model.UserDaoModelKt.TABLE_NAME;

public class UserDao implements UserDaoPort {
    private SQLiteDatabase writableDatabase;

    @Inject
    public UserDao(SQLiteDatabase dataBase) {
        writableDatabase = dataBase;
    }

    public static final String USER_DAO_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME +  " ("
                    + ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME_USER_NAME + " TEXT, " +
                    COLUMN_NAME_PROFILE_IMAGE + " TEXT)";

    public static final String USER_DAO_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    @NotNull
    @Override
    public List<User> getAll() {
        Cursor cursor = writableDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            users.add(parseUser(cursor));
        }
        return users;
    }

    @Nullable
    @Override
    public User getUserById(@NonNull String id) {
        String selection = ID + " = ?";
        String[] selectionArgs = {id};

        Cursor cursor = writableDatabase.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            return parseUser(cursor);
        }
        return null;
    }


    @Override
    public void save(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USER_NAME, "DB: " + user.getName());
        values.put(COLUMN_NAME_PROFILE_IMAGE, user.getProfileImage());
        writableDatabase.insert(TABLE_NAME, null, values);
    }

    private User parseUser(Cursor cursor) {
        long userId = cursor.getLong(cursor.getColumnIndexOrThrow(ID));
        String userName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_USER_NAME));
        String profileImage =
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_PROFILE_IMAGE));
        return new UserDaoModel(userId, userName, profileImage);
    }
}