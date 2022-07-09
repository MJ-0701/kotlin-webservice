package com.example.kotlinwebservice.domain.user.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.domain.user.web.dto.res.TokenVerifyResult
import java.time.Instant

class JWTUtil {
    // 토큰 생성
    companion object { // 코틀린 스태틱 -> 코틀린은 스태틱이 없다.

        private final const val AUTH_TIME : Long = 20*60 // 20분
        private final val ALGORITHM : Algorithm = Algorithm.HMAC512("SpringBoot-Kotlin-WebService")
        private final const val REFRESH_TIME : Long = 60 * 60 * 24 * 7 // 1 주일

        fun makeAuthToken(user : User) : String {
            return JWT.create()
                .withSubject(user.name)
                .withClaim("exp", Instant.now().epochSecond + AUTH_TIME)
                .sign(ALGORITHM)
        }

        fun makeRefreshToken(user : User) : String {
            return JWT.create()
                .withSubject(user.name)
                .withClaim("exp", Instant.now().epochSecond + REFRESH_TIME)
                .sign(ALGORITHM)
        }

        fun verify(token : String) : TokenVerifyResult{ // 토큰 유효성 검사
            try {
                val verify : DecodedJWT = JWT.require(ALGORITHM).build().verify(token)
                return TokenVerifyResult().apply {
                    this.success = true
                    this.userName = verify.subject
                }
            }catch (e : Exception){
                val decode : DecodedJWT = JWT.require(ALGORITHM).build().verify(token)
                return TokenVerifyResult().apply {
                    this.success = false
                    this.userName = decode.subject
                }

            }
        }



    }

}