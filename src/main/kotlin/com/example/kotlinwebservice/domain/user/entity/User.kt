package com.example.kotlinwebservice.domain.user.entity

import com.example.kotlinwebservice.global.entity.BaseTimeEntity
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email

@Entity
class User  (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(insertable = false, updatable = false)
    private var id : Long? = null,

    private var name : String? = null,


    @field:JsonProperty("user_id")
    private var userId : String? = null,

    private var pwd : String? = null,

    @field:Email
    private var email : String? = null

) : BaseTimeEntity()
