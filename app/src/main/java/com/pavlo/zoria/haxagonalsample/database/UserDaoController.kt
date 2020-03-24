package com.pavlo.zoria.haxagonalsample.database

import com.pavlo.zoria.haxagonalsample.domain.UserCacher
import com.pavlo.zoria.haxagonalsample.domain.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserDaoController @Inject constructor(
    private val dataBase: UserCacher
) : UserLocalInfrastructurePort {

    override fun getAllDataEmitter(): Observable<List<User>> {
        return Observable.create { emitter ->
            val users = dataBase.getAll()
            emitter.onNext(users)
        }
    }

    override fun getUserByIdEmitter(id: String): Observable<User?> {
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