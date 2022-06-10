package com.example.kotlinwebservice.domain.user.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email

@Entity
data class User(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    var name : String? = null,
    var userId : String? = null,
    var pwd : String? = null,

    @field:Email
    var email : String? = null

)
