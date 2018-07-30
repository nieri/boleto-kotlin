package br.com.munieri.kotlin.boleto.infraestructure.entity

import br.com.munieri.kotlin.boleto.domain.Boleto
import br.com.munieri.kotlin.boleto.domain.service.Status
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate

@Document(collection = "boletos")
@TypeAlias("boleto")
class BoletoEntity : Boleto {

    constructor(dataVencimento: LocalDate?, valor: Double?, nome: String?, status: Status?) {
        this.dataVencimento = dataVencimento
        this.valor = valor
        this.nome = nome
        this.status = status
    }

    @Id
    @Field("id_boleto")
    var id: String? = null

    @Field("data_vencimento")
    var dataVencimento: LocalDate? = null

    @Field("valor")
    var valor: Double? = null

    @Field("nome")
    var nome: String? = null

    @Field("status")
    var status: Status? = null

    override fun id(): String? {
        return id
    }

    override fun dataVencimento(): LocalDate? {
        return dataVencimento
    }

    override fun valor(): Double? {
        return valor
    }

    override fun nome(): String? {
        return nome
    }

    override fun status(): Status? {
        return status
    }
}