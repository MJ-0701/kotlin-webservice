package com.example.kotlinwebservice.domain.user.web.controller

import com.example.kotlinwebservice.domain.user.service.UserService
import com.example.kotlinwebservice.domain.user.web.dto.req.UserReqDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/user")
class UserApiController(private val userService: UserService) {

    @PostMapping("/save")
    fun create(@RequestBody @Valid userReqDto: UserReqDto): ResponseEntity<UserReqDto> {

        return ResponseEntity.ok().body(userService.create(userReqDto))

    }

}