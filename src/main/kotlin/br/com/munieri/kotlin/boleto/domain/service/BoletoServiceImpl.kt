package br.com.munieri.kotlin.boleto.domain.service

import br.com.munieri.kotlin.boleto.domain.Boleto
import br.com.munieri.kotlin.boleto.infraestructure.BoletoRepository
import br.com.munieri.kotlin.boleto.infraestructure.entity.BoletoEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BoletoServiceImpl : BoletoService {

    @Autowired
    lateinit var repository: BoletoRepository

    override fun findAll(): List<Boleto> {
        return repository.findAll()
    }

    override fun findOne(id: String): Boleto? {
        val boleto = repository.findById(id)
        if (boleto.isPresent) return boleto.get()
        return null
    }

    override fun delete(id: String) {
        return repository.deleteById(id)
    }

    override fun update(id: String, boleto: Boleto): Boleto {
        checkIfExists(boleto)
        persist(boleto)
        return boleto
    }

    private fun persist(boleto: Boleto): Boleto {
        return repository.save(boleto as BoletoEntity)
    }

    private fun checkIfExists(boleto: Boleto) {
        findOne(boleto.id()!!)
    }

    override fun create(boleto: Boleto): Boleto {
        return persist(boleto)
    }
}