package com.pavlo.zoria.haxagonalsample.domain

import com.pavlo.zoria.haxagonalsample.domain.model.User
import com.pavlo.zoria.haxagonalsample.domain.port.UserDaoPort
import javax.inject.Inject

class UserCacher @Inject constructor(private val dao: UserDaoPort) : UserLoader(dao) {

    fun save(user: User) {
        dao.save(user)
    }
}