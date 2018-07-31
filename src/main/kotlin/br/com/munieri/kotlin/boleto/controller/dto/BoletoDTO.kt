package br.com.munieri.kotlin.boleto.controller.dto

import br.com.munieri.kotlin.boleto.domain.Boleto
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
        this.dataVencimento = boleto.dataVencimento().toString()
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

    @FutureOrPresent(message = "{boleto.data.vencimento.range.message}")
    private var _dataVencimento: LocalDate? = null

    @NotNull(message = "{boleto.data.vencimento.null.message}")
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message = "Data deve ter o formato [yyyy-MM-dd]")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("data_vencimento")
    var dataVencimento: String? = null
        set(value) {
            field = value
            if (!value.isNullOrEmpty()) {
                this._dataVencimento = LocalDate.parse(value)
            }
        }

    @NotNull(message = "{boleto.valor.null.message}")
    @Positive(message = "{boleto.valor.negativo.message}")
    @Digits(integer = 6, fraction = 2, message = "{boleto.valor.digitos.message}")
    @JsonProperty("valor")
    var valor: Double? = null

    @NotNull(message = "{boleto.status.null.message}")
    @Pattern(regexp = "(PENDING|PAID|CANCELED)", message = "{boleto.status.pattern.value}")
    @JsonProperty("status")
    var status: String? = null
}