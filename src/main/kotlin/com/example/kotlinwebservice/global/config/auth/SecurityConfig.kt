package com.example.kotlinwebservice.global.config.auth

import com.example.kotlinwebservice.domain.user.entity.Role
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
@Configuration
class SecurityConfig(private val customOAuth2UserService: CustomOAuth2UserService) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().headers().frameOptions().disable()
            .and().authorizeRequests()
            .antMatchers("/", "/css/**", "/images/**","/js/**","/h2-console/**","/profile","/swagger-ui.html#/**").permitAll()
//            .antMatchers("/api/v1/**").hasRole(Role.USER.name)
            .anyRequest().authenticated()
            .and().logout().logoutSuccessUrl("/")
//            .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService) // TODO :: YML 에 설정 추가
    }
}