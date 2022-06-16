package com.example.kotlinwebservice.domain.todo.web.controller

import com.example.kotlinwebservice.domain.todo.service.TodoService
import com.example.kotlinwebservice.domain.todo.web.dto.req.TodoReqDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/vi/todo")
class TodoApiController(private val todoService: TodoService) {


    @PostMapping("/save")
    fun create(@Valid @RequestBody todoReqDto: TodoReqDto) : TodoReqDto{

        println("컨트롤러 결과 : ${todoReqDto.toString()}")
        return todoService.create(todoReqDto)
    }

    @GetMapping("/read")
    fun read(@RequestParam id : Long?) : ResponseEntity<Any>{
        return id?.let {
            todoService.read(it)
        }?.let {// 있을때
            return ResponseEntity.ok(it)
        }?: kotlin.run {
            return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "/api/todo/all")
                .build()
        }
    }


}