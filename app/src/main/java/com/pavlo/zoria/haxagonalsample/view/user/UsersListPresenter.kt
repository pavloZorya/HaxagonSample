package com.pavlo.zoria.haxagonalsample.view.user

import android.annotation.SuppressLint
import android.util.Log
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort
import com.pavlo.zoria.haxagonalsample.utils.BaseSchedulerProvider
import com.pavlo.zoria.haxagonalsample.utils.SchedulerProvider
import com.pavlo.zoria.haxagonalsample.view.user.model.UserModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@SuppressLint("CheckResult")
class UsersListPresenter @Inject constructor(
    private var view: UsersListContract.View,
    private val port: UserInfrastructurePort,
    private val schedulers: BaseSchedulerProvider = SchedulerProvider()
) : UsersListContract.Presenter {

    private var handling = false
    private var disposable: Disposable? = null

    override fun subscribeView(view: UsersListContract.View) {
        this.view = view
        handleEvents()
    }

    fun toggleHandling() {
        toggleHandling(!handling)
    }

    override fun toggleHandling(needHandle: Boolean) {
        if (needHandle) {
            handleEvents()
        } else {
            stopHandling()
        }
    }

    override fun handleEvents() {
        handling = true
        disposable = port.getAllDataEmitter()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .map {
                it.map { user ->
                    UserModel(user.id, user.name, user.profileImage)
                }
            }
            .doOnError {
                Log.d("UsersListPresenter", "error occurred: ${it.message}")
            }.doOnNext {
                Log.d("UsersListPresenter", "data fetched: ${it.size}")
            }
            .subscribe ({
                view.showUsers(it)
            }, {
                Log.d("UsersListPresenter", "error occurred: ${it.message}")
            })
    }

    override fun stopHandling() {
        Log.d("UsersListPresenter", "stopHandling")
        handling = false
        disposable?.dispose()
    }
}