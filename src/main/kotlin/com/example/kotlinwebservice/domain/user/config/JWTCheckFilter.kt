package com.example.kotlinwebservice.domain.user.config

import com.auth0.jwt.exceptions.TokenExpiredException
import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.domain.user.service.UserService
import com.example.kotlinwebservice.domain.user.web.dto.res.TokenVerifyResult
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTCheckFilter(
    authenticationManager: AuthenticationManager?, // 매번 리퀘스트가 올때 마다 토큰을 검사를해서 시큐리티 컨텍스트 홀더에 유저 principal 정보를 채워주는 역할
    private val userService: UserService
) :
    BasicAuthenticationFilter(authenticationManager) {

    // 토큰검사
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val bearer = request.getHeader(HttpHeaders.AUTHORIZATION) //

        if (bearer == null || !bearer.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }
        val token = bearer.substring("Bearer ".length)
        val result: TokenVerifyResult = JWTUtil.verify(token)
        if (result.success == true) {
            val user: User = userService.findByName(result.userName) as User
            val userToken = UsernamePasswordAuthenticationToken(
                user.name, null, user.authorities
            )
            SecurityContextHolder.getContext().authentication = userToken
            chain.doFilter(request, response)
        } else {
            throw TokenExpiredException("Token is not valid")
        }
    }
}