package com.example.kotlinwebservice.global.config.auth

import com.example.kotlinwebservice.global.config.auth.dto.SessionUser
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpSession

@Component
class LoginUserArgumentResolver(private val httpSession: HttpSession) : HandlerMethodArgumentResolver {


    override fun supportsParameter(parameter: MethodParameter): Boolean {
        var isLoginUserAnnotaion = parameter.getParameterAnnotation(LoginUser::class.java) != null
        var isUserClass = SessionUser::class.java == parameter.parameterType

        return isLoginUserAnnotaion && isUserClass
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        return httpSession.getAttribute("user")
    }
}