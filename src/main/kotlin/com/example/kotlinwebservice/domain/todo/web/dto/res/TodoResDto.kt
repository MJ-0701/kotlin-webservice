package com.example.kotlinwebservice.domain.todo.web.dto.res

import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.global.utils.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class TodoResDto(

    @field:NotBlank
    var title : String? = null,

    @field:NotBlank
    var description : String? = null,

    @field:NotBlank
    @field:StringFormatDateTime
    var schedule : String? = null,

    @field:JsonProperty("user_id")
    var userId : Long
)
