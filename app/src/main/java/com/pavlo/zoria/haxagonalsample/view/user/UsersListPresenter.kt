package com.pavlo.zoria.haxagonalsample.view.user

import android.annotation.SuppressLint
import android.util.Log
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort
import com.pavlo.zoria.haxagonalsample.view.user.model.UserModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class UsersListPresenter @Inject constructor(
    private var view: UsersListContract.View,
    private val port: UserInfrastructurePort
) : UsersListContract.Presenter {

    private var handling = false
    private var disposable: Disposable? = null

    override fun subscribeView(view: UsersListContract.View) {
        this.view = view
        handleEvents()
    }

    override fun toggleHandling(checked: Boolean) {
        if (checked) {
            handleEvents()
        } else {
            stopHandling()
        }
    }

    override fun handleEvents() {
        handling = true
        disposable = port.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
            .subscribe {
                view.showUsers(it)
            }
    }

    override fun stopHandling() {
        Log.d("UsersListPresenter", "stopHandling")
        handling = false
        disposable?.dispose()
        port.getAll().unsubscribeOn(AndroidSchedulers.mainThread())
    }
}