package com.pavlo.zoria.haxagonalsample.infrastructure.generator

import com.pavlo.zoria.haxagonalsample.domain.model.User
import io.reactivex.Observable

interface UserInfrastructurePort {
    fun getAllDataEmitter(): Observable<List<User>>
    fun getUserByIdEmitter(id: String): Observable<User?>
}
