package ru.paskal.laba2.controllers

import com.auth0.jwt.exceptions.TokenExpiredException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.slf4j.event.Level
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import ru.paskal.laba2.exceptions.*
import kotlin.reflect.KClass
import kotlin.reflect.full.isSuperclassOf

private val log = KotlinLogging.logger {}

@ControllerAdvice
@RestControllerAdvice
class GlobalAdvice {

    companion object {
        private val exceptionMappings: Map<KClass<out Exception>, Pair<HttpStatus, Level>> = HashMap(
            mapOf(
                BaseNotCreatedException::class to Pair(HttpStatus.BAD_REQUEST, Level.WARN),
                BaseNotFoundException::class to Pair(HttpStatus.NOT_FOUND, Level.WARN),
                BaseNotUpdatedException::class to Pair(HttpStatus.BAD_REQUEST, Level.WARN),
                BaseNotDeletedException::class to Pair(HttpStatus.BAD_REQUEST, Level.WARN),
                NoHandlerFoundException::class to Pair(HttpStatus.NOT_FOUND, Level.WARN),
                BadCredentialsException::class to Pair(HttpStatus.BAD_REQUEST, Level.WARN),
                HttpMessageNotReadableException::class to Pair(HttpStatus.BAD_REQUEST, Level.INFO),
                HttpRequestMethodNotSupportedException::class to Pair(HttpStatus.METHOD_NOT_ALLOWED, Level.WARN),
                AccessForbiddenException::class to Pair(HttpStatus.FORBIDDEN, Level.WARN),
                BadRequestException::class to Pair(HttpStatus.BAD_REQUEST, Level.WARN),
                TokenExpiredException::class to Pair(HttpStatus.FORBIDDEN, Level.WARN),
                IllegalStateException::class to Pair(HttpStatus.BAD_REQUEST, Level.WARN),
                Exception::class to Pair(HttpStatus.INTERNAL_SERVER_ERROR, Level.ERROR)
            )
        )
    }

    @ExceptionHandler(
        value = [
            BaseNotCreatedException::class,
            BaseNotFoundException::class,
            BaseNotUpdatedException::class,
            BaseNotDeletedException::class,
            NoHandlerFoundException::class,
            BadCredentialsException::class,
            HttpMessageNotReadableException::class,
            HttpRequestMethodNotSupportedException::class,
            AccessForbiddenException::class,
            TokenExpiredException::class,
            BadRequestException::class,
            IllegalStateException::class
        ]
    )
    fun handleExceptionByDefault(exception: Exception): ResponseEntity<ErrorResponse> {
        log.info { "Exception class: ${exception.javaClass.kotlin.simpleName}" }
        val mapping = findMappingForException(exception.javaClass.kotlin)
        val message = "Handled by default handler --> ${exception.message}"
        log.info { mapping.first }
        when (mapping.second) {
            Level.ERROR -> log.error { message }
            Level.WARN -> log.warn { message }
            Level.INFO -> log.info { message }
            Level.DEBUG -> log.debug { message }
            Level.TRACE -> log.trace { message }
        }
        return ResponseEntity
            .status(mapping.first)
            .body(ErrorResponse(exception))
    }

    @ExceptionHandler(java.lang.Exception::class)
    fun handleException(ex: java.lang.Exception): ResponseEntity<ErrorResponse> {
        log.error { ex.message }
        ex.printStackTrace()
        return ResponseEntity(ErrorResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private fun findMappingForException(exceptionClass: KClass<out Exception>): Pair<HttpStatus, Level> {
        for ((key, value) in exceptionMappings) {
            if (key.isSuperclassOf(exceptionClass)) {
                log.info { value }
                return value
            }
        }
        return Pair(HttpStatus.INTERNAL_SERVER_ERROR, Level.ERROR)
    }



}