package com.example.kotlinwebservice.domain.todo.web.dto.req

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class TodoReqDto(

    @field:NotBlank
    var title : String? = null,

    @field:NotBlank
    var description : String? = null,

    @field:NotBlank
    var schedule : LocalDateTime? = null

)
