package com.example.kotlinwebservice.domain.user.domain.dto

import javax.validation.constraints.Email


data class UserRequestDto (
    var name : String? = null,
    var pwd : String? = null,
    var id : String? = null,
    @field:Email
    var email : String? = null
    )