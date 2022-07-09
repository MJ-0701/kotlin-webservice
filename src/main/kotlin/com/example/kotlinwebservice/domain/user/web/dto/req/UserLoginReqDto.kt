package com.example.kotlinwebservice.domain.user.web.dto.req

import com.fasterxml.jackson.annotation.JsonProperty

data class UserLoginReqDto(
    @JsonProperty("account")
    var account : String? = null,
    var pwd : String? = null,
    @JsonProperty("refresh_token")
    var refreshToken : String? = null
)
