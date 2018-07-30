package br.com.munieri.kotlin.boleto.infraestructure

import br.com.munieri.kotlin.boleto.infraestructure.entity.BoletoEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface BoletoRepository : MongoRepository<BoletoEntity, String>