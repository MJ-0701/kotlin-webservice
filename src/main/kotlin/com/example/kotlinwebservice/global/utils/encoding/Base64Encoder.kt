package com.example.kotlinwebservice.global.utils.encoding

import org.springframework.stereotype.Component
import java.util.Base64

@Component
class Base64Encoder : IEncoder {


    override fun encode(msg: String): String {
        return Base64.getEncoder().encodeToString(msg.toByteArray())
    }

    fun decode(msg : String) : String{
        val decodeBytes = Base64.getDecoder().decode(msg)
        return String(decodeBytes)
    }


}