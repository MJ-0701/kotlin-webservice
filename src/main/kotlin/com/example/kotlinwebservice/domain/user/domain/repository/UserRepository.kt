package com.example.kotlinwebservice.domain.user.domain.repository

import com.example.kotlinwebservice.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User,Long> {
}