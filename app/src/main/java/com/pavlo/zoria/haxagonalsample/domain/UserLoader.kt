package com.pavlo.zoria.haxagonalsample.domain

import com.pavlo.zoria.haxagonalsample.domain.model.User
import com.pavlo.zoria.haxagonalsample.domain.port.UserPort
import javax.inject.Inject

open class UserLoader @Inject constructor(private val port: UserPort) : UserPort {

    override fun getAll(): List<User> {
        return port.getAll()
    }

    override fun getUserById(id: String): User? {
        return port.getUserById(id)
    }
}