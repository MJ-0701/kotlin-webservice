package com.example.kotlinwebservice.domain.user.entity.repository

import com.example.kotlinwebservice.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User,Long> {

    fun findByEmail(email : String?) : User
}