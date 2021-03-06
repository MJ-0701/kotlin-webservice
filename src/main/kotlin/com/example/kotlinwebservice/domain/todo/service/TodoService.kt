package com.example.kotlinwebservice.domain.todo.service

import com.example.kotlinwebservice.domain.todo.entity.Todo
import com.example.kotlinwebservice.domain.todo.entity.respository.TodoRepository
import com.example.kotlinwebservice.domain.todo.web.dto.req.TodoReqDto
import com.example.kotlinwebservice.domain.todo.web.dto.res.TodoResDto
import com.example.kotlinwebservice.domain.user.entity.repository.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

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
        } // 커밋용

//                return modelMapper.map(todo, TodoReqDto::class.java) // Entity -> DTO
    }

    @Transactional
    fun read(id: Long) : TodoResDto{



//        val result = todoRepository.findById(id)
//        println("result : ${result.toString()}")
//
//        return modelMapper.map(result, TodoResDto::class.java)

        val result =  todoRepository.findById(id).get()

        return TodoResDto().apply {
            this.userId = result.user.id
            this.schedule = result.schedule
            this.title = result.title
            this.description = result.description
        }

//        return modelMapper.map(result, TodoResDto::class.java)
    }

    @Transactional(readOnly = true)
    fun userList(id : Long?) : MutableList<TodoResDto> {

       val userList = todoRepository.findByUserId(id)
       return userList.stream().map {
           TodoResDto().apply {
               this.title = it.title
               this.description = it.description
               this.schedule = it.schedule
               this.userId = it.user.id
           }
       }.collect(Collectors.toList())


    }

    @Transactional(readOnly = true)
    fun readAll() : MutableList<TodoResDto> {

//        val user = userRepository.findById(id)
//        val todo = todoRepository.findByUserId(user.id)

        val todo = todoRepository.findAll()
        return todo.stream().map {
            TodoResDto().apply {
                this.title = it.title
                this.description = it.description
                this.schedule = it.schedule
                this.userId = it.user.id
            }
        }.collect(Collectors.toList())
    }

    @Transactional
    fun update(todoReqDto: TodoReqDto, id : Long) : TodoReqDto {

        val todo = todoRepository.findByUserIdAndId(todoReqDto.userId, id)

        todo.apply {
            this.schedule = todoReqDto.schedule
            this.title = todoReqDto.title
            this.description = todoReqDto.description
        }

        todoRepository.save(todo)

        return modelMapper.map(todo, TodoReqDto::class.java)
    }

    @Transactional
    fun delete(id : Long){
        val todo = todoRepository.findById(id).get()
        todoRepository.delete(todo)
    }

}