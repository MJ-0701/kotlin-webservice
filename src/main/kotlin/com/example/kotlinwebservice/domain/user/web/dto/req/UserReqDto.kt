package com.example.kotlinwebservice.domain.user.web.dto.req

import com.example.kotlinwebservice.domain.user.entity.Role
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserReqDto(
    @field:NotBlank
    @field:Size(min = 2, max = 10)
    var name : String? = null,

    @field:JsonProperty("user_id")
    @field:NotBlank
    @field:Size(min = 4, max = 15)
    var userId : String? = null,

    @field:NotBlank
    @field:Size(min = 4, max = 15)
    var pwd : String? = null,

    @field:Email
    var email : String? = null,

    var picture : String? = null,

    var role : Role? = null
)
