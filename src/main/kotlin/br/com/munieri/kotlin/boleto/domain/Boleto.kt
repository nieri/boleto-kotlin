package br.com.munieri.kotlin.boleto.domain

import br.com.munieri.kotlin.boleto.domain.service.Status
import java.time.LocalDate

interface Boleto {

    fun id(): String?
    fun dataVencimento(): LocalDate?
    fun valor(): Double?
    fun nome(): String?
    fun status(): Status?
}