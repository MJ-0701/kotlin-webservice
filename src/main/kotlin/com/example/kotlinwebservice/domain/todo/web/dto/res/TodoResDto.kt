package com.example.kotlinwebservice.domain.todo.web.dto.res

import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.global.utils.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.persistence.JoinColumn
import javax.validation.constraints.NotBlank

data class TodoResDto(

    var title : String? = null,
    var description : String? = null,
    @field:StringFormatDateTime
    var schedule : String? = null,

    @field:JoinColumn(name = "user_id")
    @field:JsonProperty("user_id")
    var userId : Long? = null
)
