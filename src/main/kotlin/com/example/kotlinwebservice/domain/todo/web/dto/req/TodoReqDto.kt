package com.example.kotlinwebservice.domain.todo.web.dto.req

import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.global.utils.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.persistence.JoinColumn
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class TodoReqDto(

    @field:NotBlank
    var title : String? = null,

    @field:NotBlank
    var description : String? = null,

    @field:NotBlank
    @field:StringFormatDateTime
    var schedule : String? = null,

    @field:JoinColumn(name = "user_id")
    @field:JsonProperty("user_id")
    var userId : Long? = null


)
