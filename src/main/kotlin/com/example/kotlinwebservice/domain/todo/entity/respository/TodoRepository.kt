package com.example.kotlinwebservice.domain.todo.entity.respository

import com.example.kotlinwebservice.domain.todo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>{
}