package com.example.kotlinwebservice.domain.user.config

import com.auth0.jwt.exceptions.TokenExpiredException
import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.domain.user.service.UserService
import com.example.kotlinwebservice.domain.user.web.dto.req.UserLoginReqDto
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.SneakyThrows
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.naming.AuthenticationException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTLoginFilter(authenticationManager: AuthenticationManager?, private val userService: UserService) :
    UsernamePasswordAuthenticationFilter(authenticationManager) {
    // Username,Password 를 받아서 유효하면 인증 토큰을 내려주는 필터.
    private val objectMapper: ObjectMapper = ObjectMapper()

    init {
        setFilterProcessesUrl("/api/v1/user/login/*")
    }

    @SneakyThrows
    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication { // 사용자 인증 처리
        val userLogin: UserLoginReqDto =
            objectMapper.readValue(request.inputStream, UserLoginReqDto::class.java)
        return if (userLogin.refreshToken == null) {
            val token = UsernamePasswordAuthenticationToken( // 토큰  인증되기 전이기 때문에  Authorities null
                userLogin.account, userLogin.pwd, null
            )
            // user details...
            authenticationManager.authenticate(token) // getAuthenticationManager 토큰 검증 요청
        } else { // 리프레쉬 토큰으로 들어오면 토큰이 유효한지 검증 한다.
            val verify = JWTUtil.verify(userLogin.refreshToken!!)
            if (verify.success == true) {
                val user: User = userService.findByName(verify.userName) as User
                UsernamePasswordAuthenticationToken(
                    user, user.authorities
                )
            } else {
                throw TokenExpiredException("refresh token expired")
            }
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication( // 검증이 제대로 됐다면 해당 메소드가 호출됨.
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val user: User = authResult.getPrincipal() as User
        //        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer" + JWTUtil.makeAuthToken(user)); // 토큰 발행 ->  Bearer 토큰이라고 명시해줌(규약)
//        response.setHeader(HttpHeaders.AUTHORIZATION,"Bearer" + JWTUtil.makeRefreshToken(user)); // 리프레쉬 토큰도 함께 발행 하여 주고 리프레쉬 토큰은 요청 될때 마다 재 발행되는 식으로 설계.
//        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE); // json 값을 받기위한 설정.
//        response.getOutputStream().write(objectMapper.writeValueAsBytes(user));
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer") // 인증 테스트 -> KEY : Authorization Value : Bearer + 토큰 값.
        response.setHeader("auth_token", JWTUtil.makeAuthToken(user))
        response.setHeader("refresh_token", JWTUtil.makeRefreshToken(user))
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        response.outputStream.write(objectMapper.writeValueAsBytes(user)) // 인증된 토큰을 유저객체에 발행
    }
}