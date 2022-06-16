package com.example.kotlinwebservice.domain.todo.service

import com.example.kotlinwebservice.domain.todo.entity.Todo
import com.example.kotlinwebservice.domain.todo.entity.respository.TodoRepository
import com.example.kotlinwebservice.domain.todo.web.dto.req.TodoReqDto
import com.example.kotlinwebservice.domain.todo.web.dto.res.TodoResDto
import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.domain.user.entity.repository.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
    private val modelMapper: ModelMapper
) {

    @Transactional
    fun create(todoReqDto: TodoReqDto) : TodoReqDto {

        // 1. Client -> Controller -> DTO -> Entity -> DB
        // 2. Entity -> DTO -> Client

        val todo = Todo().apply {
            this.user = userRepository.findById(todoReqDto.userId)
            this.description = todoReqDto.description
            this.schedule = todoReqDto.schedule
            this.title = todoReqDto.title
        }
        todoRepository.save(todo)

        return TodoReqDto().apply {
            this.userId = todo.user.id
            this.schedule = todo.schedule
            this.title = todo.title
            this.description = todo.description
        }

        //        return modelMapper.map(todo, TodoReqDto::class.java) // Entity -> DTO
    }

    @Transactional
    fun read(id: Long) : TodoResDto{



//        val result = todoRepository.findById(id)
//        println("result : ${result.toString()}")
//
//        return modelMapper.map(result, TodoResDto::class.java)

        val result =  todoRepository.findById(id)

        return modelMapper.map(result, TodoResDto::class.java)
    }

    @Transactional
    fun update(todoReqDto: TodoReqDto) : TodoReqDto {

        val todo = Todo().apply {
            this.user = userRepository.findById(todoReqDto.userId)
            this.description = todoReqDto.description
            this.schedule = todoReqDto.schedule
            this.title = todoReqDto.title
        }
        todoRepository.save(todo)

        return TodoReqDto().apply {
            this.userId = todo.user.id
            this.schedule = todo.schedule
            this.title = todo.title
            this.description = todo.description
        }
    }

}