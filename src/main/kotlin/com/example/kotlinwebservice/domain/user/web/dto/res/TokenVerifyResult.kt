package com.example.kotlinwebservice.domain.user.web.dto.res

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenVerifyResult(
    var success: Boolean? = null,

    @JsonProperty("user_name")
    var userName : String? = null
)
