package com.pavlo.zoria.haxagonalsample.domain.port

import com.pavlo.zoria.haxagonalsample.domain.model.User

interface UserPort {
    fun getAll(): List<User>
    fun getUserById(id: String): User?
}
