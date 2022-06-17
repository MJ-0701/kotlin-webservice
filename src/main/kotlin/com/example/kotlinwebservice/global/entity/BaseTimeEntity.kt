package com.example.kotlinwebservice.global.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseTimeEntity (

    @field:CreatedDate
    @field:Column(nullable = false, updatable = false)
    private var createdAt : LocalDateTime? = null,

    @field:LastModifiedBy
    private var updatedAt : LocalDateTime? = null

)