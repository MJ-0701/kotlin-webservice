package com.example.kotlinwebservice.global.utils.encoding

data class Encoder(private var iEncoder : IEncoder) {


//    fun Encoder(iEncoder: IEncoder){
//        this.iEncoder = iEncoder
//    }

    fun setIEncoder(iEncoder: IEncoder) {
        this.iEncoder = iEncoder
    }

    fun endcode(msg : String) : String{
        return iEncoder.encode(msg)
    }

}