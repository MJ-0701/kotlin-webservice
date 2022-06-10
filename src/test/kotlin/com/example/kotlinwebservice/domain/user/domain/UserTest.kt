package com.example.kotlinwebservice.domain.user.domain

import com.example.kotlinwebservice.domain.user.domain.dto.UserRequestDto
import com.example.kotlinwebservice.domain.user.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserTest{

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun createTest(){
        val user = User().apply {
            this.name = "채명정"
            this.email = "jack2718@naver.com"
            this.pwd = "테스트123"
            this.userId = "jack2718"
        }

        userRepository.save(user)

        Assertions.assertEquals(1 , user.id)
        Assertions.assertEquals("채명정", user.name)


    }


}