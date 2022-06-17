package com.example.kotlinwebservice.global.config.auth.dto

import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.domain.user.web.dto.req.UserReqDto
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email

data class SessionUser(
    val name: String?, val email: String?, val picture: String?

) : java.io.Serializable
{
    constructor(user: User) : this(user.name, user.email, user.picture)

}