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

    var schedule : String? = null,

    @field:JsonProperty("user_id")
    @field:JoinColumn(name = "user_id")
    var userId : Long? = null
)
