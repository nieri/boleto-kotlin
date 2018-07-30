package br.com.munieri.kotlin.boleto.controller.dto

import br.com.munieri.kotlin.boleto.domain.Boleto
import br.com.munieri.kotlin.boleto.domain.service.Status
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class BoletoDTO {

    constructor()

    constructor(boleto: Boleto) {
        this.id = boleto.id()
        this.dataVencimento = boleto.dataVencimento()
        this.valor = boleto.valor()
        this.nome = boleto.nome()
        this.status = boleto.status()!!.name
    }

    @JsonProperty("id")
    var id: String? = null

    @NotNull(message = "{boleto.name.null.message}")
    @Size(min = 3, max = 25, message = "{boleto.name.size.message}")
    @JsonProperty("nome")
    var nome: String? = null

    @NotNull(message = "{boleto.data.vencimento.null.message}")
    @FutureOrPresent(message = "{boleto.data.vencimento.range.message}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("data_vencimento")
    var dataVencimento: LocalDate? = null

    @NotNull(message = "{boleto.valor.null.message}")
    @Positive(message = "{boleto.valor.negativo.message}")
    @Digits(integer = 6, fraction = 2, message = "{boleto.valor.digitos.message}")
    @JsonProperty("valor")
    var valor: Double? = null

    @NotNull(message = "{boleto.status.null.message}")
    @Pattern(regexp="(PENDING|PAID|CANCELED)", message = "{boleto.status.pattern.value}")
    @JsonProperty("status")
    var status: String? = null
}