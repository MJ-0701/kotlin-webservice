package com.example.kotlinwebservice.domain.user.entity

import com.example.kotlinwebservice.domain.todo.entity.Todo
import com.example.kotlinwebservice.global.entity.BaseTimeEntity
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
class User  (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(insertable = false, updatable = false)
    val id : Long? = null,

    var name : String,


    @field:JsonProperty("account")
    var account : String,

    var pwd : String,

    @field:Email
    var email : String,

    var picture : String,

    @field:Enumerated(EnumType.STRING)
    @field:Column(nullable = false)
    var role : Role,

    @field:OneToMany
    var userHistory: MutableList<UserHistory>,

    @field:OneToMany(fetch = FetchType.LAZY)
    var todo : MutableList<Todo>

    var authorities : Set<UserAuthority>

) : BaseTimeEntity()
{
//    fun update(name : String, picture : String) : User{
//        this.name = name
//        this.picture = picture
//
//        return this
//    }

    fun getRoleKey() : String = this.role.key
}
