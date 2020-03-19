package com.pavlo.zoria.haxagonalsample.domain.port

import com.pavlo.zoria.haxagonalsample.domain.model.User

interface UserDaoPort : UserPort {
    fun save(user: User)
}
