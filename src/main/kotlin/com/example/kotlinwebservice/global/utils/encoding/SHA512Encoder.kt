package com.example.kotlinwebservice.global.utils.encoding

import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

class SHA512Encoder : IEncoder {


    override fun encode(msg: String): String {

        var random : SecureRandom = SecureRandom.getInstance("SHA1PRNG")
        var bytes : ByteArray = byteArrayOf(16)

        random.nextBytes(bytes)
        var salt : String = String(Base64.getEncoder().encode(bytes))


        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        md.update(salt.toByteArray())
        md.update(msg.toByteArray())
        return String.format("%0128x", BigInteger(1, md.digest()))
    }
}