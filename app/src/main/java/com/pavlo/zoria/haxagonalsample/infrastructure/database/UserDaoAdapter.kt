package com.pavlo.zoria.haxagonalsample.infrastructure.database

import com.pavlo.zoria.haxagonalsample.domain.UserCacher
import com.pavlo.zoria.haxagonalsample.domain.model.User
import com.pavlo.zoria.haxagonalsample.domain.port.UserDaoPort
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserDaoAdapter @Inject constructor(
    private val dataBase: UserCacher
) : UserLocalInfrastructurePort {

    override fun getAll(): Observable<List<User>> {
        return Observable.create { emitter ->
            val users = dataBase.getAll()
            emitter.onNext(users)
        }
    }

    override fun getUserById(id: String): Observable<User?> {
        return Observable.create { emitter ->
            run {
                val userById = dataBase.getUserById(id)
                userById?.let {
                    emitter.onNext(userById)
                    emitter.onComplete()
                } ?: kotlin.run {
                    emitter.onError(Throwable("Something went wrong"))
                }
            }
        }
    }

    override fun save(user: User): Completable {
        return Completable.create {
            dataBase.save(user)
            it.onComplete()
        }
    }
}