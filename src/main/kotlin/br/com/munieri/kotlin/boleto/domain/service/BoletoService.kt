package br.com.munieri.kotlin.boleto.domain.service

import br.com.munieri.kotlin.boleto.domain.Boleto

interface BoletoService {

    fun findAll(): List<Boleto>?

    fun findOne(id: String): Boleto?

    fun delete(id: String)

    fun update(id: String, boleto: Boleto): Boleto

    fun create(boleto: Boleto): Boleto
}