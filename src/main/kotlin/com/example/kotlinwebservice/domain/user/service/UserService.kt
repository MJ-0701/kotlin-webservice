package com.example.kotlinwebservice.domain.user.service

import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.domain.user.entity.repository.UserRepository
import com.example.kotlinwebservice.domain.user.web.dto.req.UserReqDto
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository,
                  private val modelMapper: ModelMapper
                  ) {


    @Transactional
    fun create(userReqDto: UserReqDto) : UserReqDto{
        val result = userRepository.save(modelMapper.map(userReqDto, User::class.java)) // jvm 대문에 자바 클래스로 설정 해 줘야함.
        println("결과 :$result")
        return modelMapper.map(result, UserReqDto::class.java)
    }

}