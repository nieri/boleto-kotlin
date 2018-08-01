package br.com.munieri.kotlin.boleto.controller.dto

import java.io.Serializable

class ErrorDTO: Serializable {

    var timestamp: String? = null
    var uri: String? = null
    var code: String? = null
    var description: String? = null

    constructor(timestamp: String, uri: String, code: String, description: String) {
        this.timestamp = timestamp
        this.uri = uri
        this.code = code
        this.description = description
    }

    override fun toString(): String {
        return "ErrorDTO(timestamp=$timestamp, uri=$uri, code=$code, description=$description)"
    }
}