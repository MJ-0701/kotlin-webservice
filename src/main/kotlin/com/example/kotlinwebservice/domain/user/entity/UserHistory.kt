package com.example.kotlinwebservice.domain.user.entity

import com.example.kotlinwebservice.global.entity.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class UserHistory(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    var id : Long,

    var name : String,

    var email : String,

    @field:ManyToOne
    @field:JoinColumn(name = "user_id")
    var user : User

) : BaseTimeEntity() {
}