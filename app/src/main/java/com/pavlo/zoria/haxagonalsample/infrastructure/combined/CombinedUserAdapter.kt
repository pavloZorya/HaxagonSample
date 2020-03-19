package com.pavlo.zoria.haxagonalsample.infrastructure.combined

import com.pavlo.zoria.haxagonalsample.domain.model.User
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort
import com.pavlo.zoria.haxagonalsample.infrastructure.database.UserLocalInfrastructurePort
import io.reactivex.Observable
import javax.inject.Inject

class CombinedUserAdapter @Inject constructor(
    private val local: UserLocalInfrastructurePort,
    private val generator: UserInfrastructurePort
) : UserInfrastructurePort {
    override fun getAll(): Observable<List<User>> {
        return generator.getAll().doOnNext {
            it.forEach { user ->
                local.save(user)
            }
        }
    }

    override fun getUserById(id: String): Observable<User?> {
        return generator.getUserById(id).doOnNext {
            it?.let { local.save(it) }
        }
    }
}