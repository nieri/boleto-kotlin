package br.com.munieri.kotlin.boleto.repository

import br.com.munieri.kotlin.boleto.domain.service.Status
import br.com.munieri.kotlin.boleto.infraestructure.BoletoRepository
import br.com.munieri.kotlin.boleto.infraestructure.entity.BoletoEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@RunWith(SpringRunner::class)
@SpringBootTest
class BoletoRepositoryTest {

    @Autowired
    lateinit var repository: BoletoRepository

    @Before
    fun setUp() {
        repository!!.deleteAll()
    }

    @Test
    fun should_set_id_on_save() {
        val boleto = repository!!.save(mockBoletoEntity())
        print("#######" + boleto.id() + "#######")
        assertThat(boleto.id()).isNotNull()
    }

    @Test
    fun should_update_boleto() {
        var boleto = repository!!.save(mockBoletoEntity())

        boleto.status = Status.PAID
        var boletoAtualizado = repository!!.save(boleto)
        assertThat(boletoAtualizado.status()).isEqualTo(Status.PAID)
    }

    @Test
    fun should_delete_boleto() {
        var boleto = repository!!.save(mockBoletoEntity())
        assertThat(boleto.id()).isNotNull()

        repository.delete(boleto)

        boleto = repository!!.findById(boleto.id!!).orElse(null)
        assertThat(boleto).isNull()
    }

    private fun mockBoletoEntity() = BoletoEntity(LocalDate.now(), 10.0, "Joao da Silva", Status.PENDING)
}