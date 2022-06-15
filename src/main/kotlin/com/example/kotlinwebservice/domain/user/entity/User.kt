package com.example.kotlinwebservice.domain.user.entity

import com.example.kotlinwebservice.global.entity.BaseTimeEntity
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
class User  (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(insertable = false, updatable = false)
    private val id : Long? = null,

    var name : String,


    @field:JsonProperty("user_id")
    var userId : String,

    var pwd : String,

    @field:Email
    var email : String,

    var picture : String,

    @field:Enumerated(EnumType.STRING)
    @field:Column(nullable = false)
    var role : Role

) : BaseTimeEntity()
{
    fun update(name : String, picture : String) : User{
        this.name = name
        this.picture = picture

        return this
    }

    fun getRoleKey() : String = this.role.key
}
