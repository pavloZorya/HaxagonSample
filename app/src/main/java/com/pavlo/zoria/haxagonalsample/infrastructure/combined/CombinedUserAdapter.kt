package com.pavlo.zoria.haxagonalsample.infrastructure.combined

import com.pavlo.zoria.haxagonalsample.domain.model.User
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort
import com.pavlo.zoria.haxagonalsample.database.UserLocalInfrastructurePort
import io.reactivex.Observable
import javax.inject.Inject

class CombinedUserAdapter @Inject constructor(
    private val local: UserLocalInfrastructurePort,
    private val generator: UserInfrastructurePort
) : UserInfrastructurePort {

    override fun getAllDataEmitter(): Observable<List<User>> {
        return Observable.mergeDelayError(
            local.getAllDataEmitter(),
            generator
                .getAllDataEmitter()
                .doOnNext {
                    it.forEach { user ->
                        local.save(user).subscribe()
                    }
                })
    }

    override fun getUserByIdEmitter(id: String): Observable<User?> {
        return generator.getUserByIdEmitter(id).doOnNext {
            it?.let { local.save(it) }
        }
    }
}