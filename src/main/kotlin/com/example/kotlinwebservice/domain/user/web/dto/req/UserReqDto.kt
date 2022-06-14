package com.example.kotlinwebservice.domain.user.web.dto.req

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email

data class UserReqDto(
    private var name : String? = null,


    @field:JsonProperty("user_id")
    private var userId : String? = null,
    private var pwd : String? = null,

    @field:Email
    private var email : String? = null
)
