package com.pavlo.zoria.haxagonalsample.view.user

import com.pavlo.zoria.haxagonalsample.view.user.model.UserModel

interface UsersListContract {

    interface Presenter {
        fun subscribeView(view: View)
        fun handleEvents()
        fun toggleHandling(checked: Boolean)
        fun stopHandling()
    }

    interface View {
        fun showUsers(users: List<UserModel>)
    }
}