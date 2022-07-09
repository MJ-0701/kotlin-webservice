package com.example.kotlinwebservice.domain.user.entity

enum class Role(val key: String, val title: String) {
    GUEST("ROLE_GUEST","비회원"),
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자")


}