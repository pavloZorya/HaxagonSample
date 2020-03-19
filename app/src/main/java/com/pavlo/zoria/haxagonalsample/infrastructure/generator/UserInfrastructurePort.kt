package com.pavlo.zoria.haxagonalsample.infrastructure.generator

import com.pavlo.zoria.haxagonalsample.domain.model.User
import io.reactivex.Observable

interface UserInfrastructurePort {
    fun getAll(): Observable<List<User>>
    fun getUserById(id: String): Observable<User?>
}
