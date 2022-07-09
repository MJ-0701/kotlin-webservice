package com.example.kotlinwebservice.domain.user.config

import com.example.kotlinwebservice.domain.user.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
    private var userService: UserService,
    private var objectMapper : ObjectMapper
    ) : UsernamePasswordAuthenticationFilter() {

        fun JWTLoginFilter(authenticationManager : AuthenticationManager, userService: UserService){
            super.setAuthenticationManager(authenticationManager)
            this.userService = userService
            setFilterProcessesUrl("/api/v1/user/login/**")
        }
}