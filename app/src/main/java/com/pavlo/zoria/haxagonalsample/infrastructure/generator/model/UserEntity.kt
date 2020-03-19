package com.pavlo.zoria.haxagonalsample.infrastructure.generator.model

import com.pavlo.zoria.haxagonalsample.domain.model.User

class UserEntity(
    override val id: Long,
    override val name: String,
    override val profileImage: String
) : User