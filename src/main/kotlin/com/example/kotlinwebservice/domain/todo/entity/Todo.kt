package com.example.kotlinwebservice.domain.todo.entity

import com.example.kotlinwebservice.global.entity.BaseTimeEntity
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Todo (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false)
    var id : Long? = null,
    var title : String? = null,
    var description : String? = null,
    var schedule : LocalDateTime? = null


        ) : BaseTimeEntity()