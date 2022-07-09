package com.example.kotlinwebservice.domain.user.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table


@Entity
@Table(name = "user_authority")
@IdClass(
    UserAuthority::class
)
data class UserAuthority(
    @field:Id
    private val idx: Long? = null,

    @field:Id
    private val authority: String? = null
) : GrantedAuthority {

    override fun getAuthority(): String? {
        return authority
    }

}