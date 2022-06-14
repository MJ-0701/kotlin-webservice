package com.example.kotlinwebservice.domain.user.web.dto.req

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserReqDto(
    @field:NotBlank
    @field:Size(min = 2, max = 10)
    private var name : String? = null,

    @field:JsonProperty("user_id")
    @field:NotBlank
    @field:Size(min = 4, max = 15)
    private var userId : String? = null,

    @field:NotBlank
    @field:Size(min = 4, max = 15)
    private var pwd : String? = null,

    @field:Email
    private var email : String? = null
)
