package com.example.kotlinwebservice.domain.todo.entity

import com.example.kotlinwebservice.domain.todo.web.dto.req.TodoReqDto
import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.global.entity.BaseTimeEntity
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity
class Todo (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false)
    private val id : Long? = null ,

    var title : String? = null,

    var description : String? = null,

    var schedule : String?= null,

    user : User? = null
    ) : BaseTimeEntity(){

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var user : User
    }