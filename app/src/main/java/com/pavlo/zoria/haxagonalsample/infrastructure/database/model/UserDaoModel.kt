package com.pavlo.zoria.haxagonalsample.infrastructure.database.model

import com.pavlo.zoria.haxagonalsample.domain.model.User


const val TABLE_NAME = "user_dao"

data class UserDaoModel(
    override val id: Long, override val name: String, override val profileImage: String
) : User {
    companion object {
        const val ID = "id"
        const val COLUMN_NAME_USER_NAME = "name"
        const val COLUMN_NAME_PROFILE_IMAGE = "title"
    }
}
