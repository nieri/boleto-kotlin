package br.com.munieri.kotlin.boleto.controller

import br.com.munieri.kotlin.boleto.controller.dto.BoletoDTO
import br.com.munieri.kotlin.boleto.domain.Boleto
import br.com.munieri.kotlin.boleto.domain.BoletoBuilder
import br.com.munieri.kotlin.boleto.domain.service.BoletoService
import br.com.munieri.kotlin.boleto.domain.service.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import javax.validation.Valid

@RestController
class BoletoEndpoint : BoletoController {

    @Autowired
    lateinit var service: BoletoService

    @GetMapping("/boletos")
    override fun getAll(): List<Boleto>? {
        return service.findAll()
    }

    @GetMapping("/boletos/{id}")
    override fun get(@PathVariable(value = "id") id: String): Boleto? {
        return service.findOne(id)
    }

    @PostMapping("/boletos")
    override fun post(@RequestBody @Valid dto: BoletoDTO): Boleto {

        var boleto = BoletoBuilder()
                .aBuilder()
                .dataVencimento(LocalDate.parse(dto.dataVencimento))
                .nome(dto.nome)
                .status(Status.valueOf(dto.status!!))
                .valor(dto.valor)
                .build()

        return service.create(boleto)
    }

    @PutMapping("/boletos")
    override fun put(@PathVariable id: String, @RequestBody @Valid dto: BoletoDTO): Boleto {

        var boleto = BoletoBuilder()
                .aBuilder()
                .dataVencimento(LocalDate.parse(dto.dataVencimento))
                .nome(dto.nome)
                .status(Status.valueOf(dto.status!!))
                .valor(dto.valor)
                .build()

        return service.update(id, boleto)
    }

    @DeleteMapping("/boletos/{id}")
    override fun delete(@PathVariable(value = "id") id: String) {
        return service.delete(id)
    }
}