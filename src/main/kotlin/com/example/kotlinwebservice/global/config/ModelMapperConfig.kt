package com.example.kotlinwebservice.global.config

import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfig {

    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.isFieldMatchingEnabled = true // isFieldMatchingEnabled를 true로 하면 필드 이름이 같은 것끼리 매칭
        modelMapper.configuration.fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE // 그리고 fieldAccessLevel을 PRIVATE로 해놓으면 private 필드여도 접근할 수 있도록 하게 해줍니다.
        modelMapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
        return modelMapper
    }
}