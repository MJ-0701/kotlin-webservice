package com.example.kotlinwebservice.domain.todo.service

import com.example.kotlinwebservice.domain.todo.entity.Todo
import com.example.kotlinwebservice.domain.todo.entity.respository.TodoRepository
import com.example.kotlinwebservice.domain.todo.web.dto.req.TodoReqDto
import com.example.kotlinwebservice.domain.todo.web.dto.res.TodoResDto
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

        val result = todoRepository.save(modelMapper.map(todoReqDto, Todo::class.java))
        println("서비스 결과 :${result.toString()}")
        return modelMapper.map(result, TodoReqDto::class.java)
    }

    @Transactional
    fun read(id: Long) : TodoResDto{

        val result = todoRepository.findById(id)

        return modelMapper.map(result, TodoResDto::class.java)
    }

}