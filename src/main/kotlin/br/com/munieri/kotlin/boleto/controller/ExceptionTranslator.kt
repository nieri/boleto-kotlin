package br.com.munieri.kotlin.boleto.controller

import br.com.munieri.kotlin.boleto.controller.dto.ErrorDTO
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@ControllerAdvice
@RestController
class ExceptionTranslator : ResponseEntityExceptionHandler() {

    private val log = LoggerFactory.getLogger(ExceptionTranslator::class.java)

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {


        var path = (request as ServletWebRequest).request.requestURI

        val errorDTO = ErrorDTO(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE), path, status.toString(),
                ex!!.bindingResult.fieldError!!.defaultMessage!!)
        log.debug(errorDTO.toString())
        return ResponseEntity(errorDTO, status)
    }
}