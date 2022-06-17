package com.example.kotlinwebservice.domain.todo.entity.respository

import com.example.kotlinwebservice.domain.todo.entity.Todo
import com.example.kotlinwebservice.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>{

    fun findByUserIdAndId(userId : Long?, id : Long?) : Todo
}