package com.pavlo.zoria.haxagonalsample

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pavlo.zoria.haxagonalsample.database.UserDao
import com.pavlo.zoria.haxagonalsample.database.UserDaoDbHelper
import com.pavlo.zoria.haxagonalsample.database.model.UserDaoModel
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.model.UserEntity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var userDao: UserDao
    private lateinit var dbHelper: UserDaoDbHelper

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dbHelper = UserDaoDbHelper(context, "some_name", 1)
        userDao = UserDao(dbHelper.writableDatabase)
        dbHelper.dropAndRecreate()
    }

    @After
    fun tearDown() {
        dbHelper.dropTables()
    }

    @Test
    @Throws(Exception::class)
    fun testDbInsertion() {
        val name = "name"
        val profileImage = "https://some.data.com"
        val id: Long = 1

        val userEntity = UserEntity(id, name, profileImage)

        userDao.save(userEntity)

        val userById = userDao.getUserById(id.toString())
        assertEquals(userById?.name, "DB: ${userEntity.name}")
        assertEquals(userById?.profileImage, userEntity.profileImage)
    }


    @Test
    @Throws(Exception::class)
    fun testDbRetrievingUser() {
        val id: Long = 1
        val userEntity = UserEntity(1, "name", "https://some.data.com")
        userDao.save(userEntity)

        val userById = userDao.getUserById(id.toString())
        assertTrue(userById is UserDaoModel)
    }


    @Test
    @Throws(Exception::class)
    fun testDbGetAll() {
        dbHelper.dropAndRecreate()

        val id: Long = 1
        val userEntity = UserEntity(1, "name", "https://some.data.com")
        userDao.save(userEntity)
        val allUsers = userDao.getAll()

        assertTrue(allUsers.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun testDbGetAllEmptyList() {
        dbHelper.dropAndRecreate()
        val allUsers = userDao.getAll()
        assertTrue(allUsers.isEmpty())
        assertTrue(allUsers is ArrayList)
    }
}