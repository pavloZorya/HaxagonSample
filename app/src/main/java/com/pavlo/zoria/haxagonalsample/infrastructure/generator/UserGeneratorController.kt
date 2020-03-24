package com.pavlo.zoria.haxagonalsample.infrastructure.generator

import android.annotation.SuppressLint
import android.util.Log
import com.jakewharton.rxrelay2.PublishRelay
import com.pavlo.zoria.haxagonalsample.domain.UserLoader
import com.pavlo.zoria.haxagonalsample.domain.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@SuppressLint("CheckResult")
class UserGeneratorController @Inject constructor(private val dataGenerator: UserLoader) :
    UserInfrastructurePort {

    private val observable = PublishRelay.create<List<User>>()

    init {
        generateUsers()
    }

    override fun getAllDataEmitter(): Observable<List<User>> {
        return observable
    }

    override fun getUserByIdEmitter(id: String): Observable<User?> {
        val observable = PublishRelay.create<User?>()
        return generateUser(observable)
    }

    private fun generateUser(observable: PublishRelay<User?>): Observable<User?> {
        observable.accept(dataGenerator.getUserById("")!!)
        return observable
    }

    private fun generateUsers() {
        Observable.interval(3_000, 10_000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val users = dataGenerator.getAll()
                Log.d("UserGeneratorRepository", "Generate data: ${users.size}")

                observable.accept(users)
            }
    }
}