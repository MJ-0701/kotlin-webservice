package com.example.kotlinwebservice.global.config.auth.dto

import java.util.Objects

data class OAuthAttributes(

    var attributes: Map<String, Any>,
    var nameAttributeKey : String,
    var name : String,
    var email : String,
    var picture : String
){
    companion object {

        fun of(registrationId: String, userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            if (registrationId == "naver") {
                return ofNaver("id", attributes)
            }
            return ofGoogle(userNameAttributeName, attributes)
        }


        fun ofGoogle(
            userNameAttributeName : String,
            attributes: Map<String, Any>
        ) : OAuthAttributes{

            return OAuthAttributes(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                picture = attributes["picture"] as String,
                attributes = attributes,
                nameAttributeKey = userNameAttributeName
            )

        }

        @Suppress("UNCHECKED_CAST")
        fun ofNaver(
            userNameAttributeName : String,
            attributes: Map<String, Any>
        ) : OAuthAttributes{

            var response : Map<String, Any> = attributes["response"] as Map<String, Any>
            return OAuthAttributes(
                name = response["name"] as String,
                email = response["email"] as String,
                picture = response.getOrDefault("profile_image", "") as String,
                attributes = response,
                nameAttributeKey = userNameAttributeName
            )



        }

    }



}
