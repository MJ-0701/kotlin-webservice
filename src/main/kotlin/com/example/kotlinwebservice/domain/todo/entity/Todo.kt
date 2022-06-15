package com.example.kotlinwebservice.domain.todo.entity

import com.example.kotlinwebservice.domain.user.entity.User
import com.example.kotlinwebservice.global.entity.BaseTimeEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Todo (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false)
    var id : Long? = null,
    var title : String? = null,
    var description : String? = null,
    var schedule : LocalDateTime? = null,

    @field:ManyToOne
    var user : User


        ) : BaseTimeEntity()