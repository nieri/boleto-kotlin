package br.com.munieri.kotlin.boleto.controller

import br.com.munieri.kotlin.boleto.controller.dto.BoletoDTO
import br.com.munieri.kotlin.boleto.domain.Boleto

interface BoletoController {

    fun getAll(): List<Boleto>?

    fun get(id: String): Boleto?

    fun post(dto: BoletoDTO): Boleto

    fun put(id: String, dto: BoletoDTO): Boleto

    fun delete(id: String)
}