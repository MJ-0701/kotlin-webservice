package com.example.kotlinwebservice.domain.todo.web.controller

import com.example.kotlinwebservice.domain.todo.service.TodoService
import com.example.kotlinwebservice.domain.todo.web.dto.req.TodoReqDto
import com.example.kotlinwebservice.domain.todo.web.dto.res.TodoResDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/todo")
class TodoApiController(private val todoService: TodoService) {


    @PostMapping("/save")
    fun create(@Valid @RequestBody todoReqDto: TodoReqDto) : TodoReqDto{

        return todoService.create(todoReqDto)
    }

    @GetMapping("/read")
    fun read(@RequestParam id : Long) : TodoResDto{

        return todoService.read(id)

    //        return id?.let {
//            todoService.read(it)
//        }?.let {// 있을때
//            return ResponseEntity.ok(it)
//        }?: kotlin.run {
//            return ResponseEntity
//                .status(HttpStatus.MOVED_PERMANENTLY)
////                .header(HttpHeaders.LOCATION, "/api/todo/all")
//                .build()
//        }
    }

    @GetMapping("/read/user-list")
    fun userList(@RequestParam id: Long) : MutableList<TodoResDto>{
        return todoService.userList(id)
    }

    @GetMapping("/list")
    fun readAll(@RequestParam id : Long?) : MutableList<TodoResDto> {

        return todoService.readAll(id)
    }

    @PutMapping("/update/{id}")
    fun update (reqDto: TodoReqDto, @PathVariable id : Long) : TodoReqDto {
        return todoService.update(reqDto, id) // 커밋용
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id : Long) : ResponseEntity<Any>{

        return ResponseEntity.ok().body(todoService.delete(id))
    }





}