package com.example.kotlinwebservice.domain.todo.entity.respository

import com.example.kotlinwebservice.domain.todo.entity.Todo
import com.example.kotlinwebservice.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TodoRepository : JpaRepository<Todo, Long>{

    fun findByUserIdAndId(userId : Long?, id : Long?) : Todo

//    @Query("SELECT t FROM Todo t where t.user.id = User.id")
    fun findByUserId(userId : Long?) : MutableList<Todo>
}