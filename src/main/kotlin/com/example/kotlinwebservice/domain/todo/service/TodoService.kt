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

        val todo = Todo().apply {
            this.user = userRepository.findByUserId(todoReqDto.userId)
            this.description = todoReqDto.description
            this.schedule = todoReqDto.schedule
            this.title = todoReqDto.title
        }

        todoRepository.save(todo)

        return modelMapper.map(todo, TodoReqDto::class.java)
    }

    @Transactional
    fun read(id: Long) : TodoResDto{

      return todoRepository.findByUserId(id).let { // 유저의 아이디값을 넣어줘야함.
          modelMapper.map(it, TodoResDto::class.java)
      }
    }

}