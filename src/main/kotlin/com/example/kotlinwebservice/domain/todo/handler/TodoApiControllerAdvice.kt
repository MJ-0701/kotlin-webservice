package com.example.kotlinwebservice.domain.todo.handler

import com.example.kotlinwebservice.domain.todo.web.controller.TodoApiController
import com.example.kotlinwebservice.global.web.dto.res.ErrorField
import com.example.kotlinwebservice.global.web.dto.res.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(basePackageClasses = [TodoApiController::class])
class TodoApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e : MethodArgumentNotValidException, request : HttpServletRequest): ResponseEntity<ErrorResponse> {

        val errors = mutableListOf<ErrorField>()

        e.bindingResult.allErrors.forEach { errorObject ->

            val error = ErrorField().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue

            }
            errors.add(error)
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = ""
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        return ResponseEntity.badRequest().body(errorResponse)

    }
}