package com.example.kotlinwebservice.global.config.auth

import com.example.kotlinwebservice.domain.user.config.JWTCheckFilter
import com.example.kotlinwebservice.domain.user.config.JWTLoginFilter
import com.example.kotlinwebservice.domain.user.entity.Role
import com.example.kotlinwebservice.domain.user.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val userService: UserService

    ) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder() : PasswordEncoder{
        return NoOpPasswordEncoder.getInstance()
    }

    override fun configure(http: HttpSecurity) {
        val loginFilter : JWTLoginFilter = JWTLoginFilter(authenticationManager(),userService)
        val checkFilter : JWTCheckFilter = JWTCheckFilter(authenticationManager(), userService)

        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and().headers().frameOptions().disable()
            .and().authorizeRequests()
            .antMatchers("/", "/css/**", "/images/**","/js/**","/h2-console/**","/profile","/swagger-ui.html#/**").permitAll()
            .antMatchers("/api//v1/todo").hasRole(Role.USER.name)
//            .antMatchers("/api/v1/**").hasRole(Role.USER.name)
//            .anyRequest().authenticated()
//            .and().logout().logoutSuccessUrl("/")
//            .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService) // TODO :: YML 에 설정 추가
            .and()
            .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter::class.java) // 세션을 사용하지 않고 토큰을 사용하여 인증. -> 로그인 처리
            .addFilterAt(checkFilter, BasicAuthenticationFilter::class.java) // // -> 토큰 검증

    }
}