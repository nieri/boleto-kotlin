package br.com.munieri.kotlin.boleto.domain

import br.com.munieri.kotlin.boleto.domain.service.Status
import br.com.munieri.kotlin.boleto.infraestructure.entity.BoletoEntity
import java.time.LocalDate

class BoletoBuilder {

    private var dataVencimento: LocalDate? = null
    private var valor: Double? = null
    private var nome: String? = null
    private var status: Status? = null

    fun aBuilder(): BoletoBuilder {
        return BoletoBuilder()
    }

    fun dataVencimento(dataVencimento: LocalDate?): BoletoBuilder {
        this.dataVencimento = dataVencimento
        return this
    }

    fun valor(valor: Double?): BoletoBuilder {
        this.valor = valor
        return this
    }

    fun nome(nome: String?): BoletoBuilder {
        this.nome = nome
        return this
    }

    fun status(status: Status?): BoletoBuilder {
        this.status = status
        return this
    }

    fun build() = BoletoEntity(dataVencimento, valor, nome, status)
}