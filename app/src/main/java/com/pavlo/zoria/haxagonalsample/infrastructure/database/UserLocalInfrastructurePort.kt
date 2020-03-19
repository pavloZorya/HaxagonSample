package com.pavlo.zoria.haxagonalsample.infrastructure.database

import com.pavlo.zoria.haxagonalsample.domain.model.User
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort
import io.reactivex.Completable

interface UserLocalInfrastructurePort : UserInfrastructurePort {
    fun save(user: User): Completable
}
