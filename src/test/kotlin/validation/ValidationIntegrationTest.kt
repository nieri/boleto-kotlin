package validation

import br.com.munieri.kotlin.boleto.controller.dto.BoletoDTO
import br.com.munieri.kotlin.boleto.domain.service.Status
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.validation.Validation
import javax.validation.Validator

class ValidationIntegrationTest {

    var validator: Validator? = null

    @Before
    fun setup() {
        var factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    private fun mock(): BoletoDTO {
        var dto = BoletoDTO()
        dto.status = Status.PENDING
        dto.nome = "Teste"
        dto.valor = 10.00
        dto.dataVencimento = getLocalDate()
        return dto
    }

    private fun getLocalDate() = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    @Test
    fun ifNameIsNull_nameValidationFails() {
        var dto = BoletoDTO()
        dto.status = Status.PENDING
        dto.valor = 10.00
        dto.dataVencimento = getLocalDate()

        val violations = validator!!.validate(dto)
        assertEquals(violations.isEmpty(), false)
    }

    @Test
    fun ifNameSizeLassThan3_nameValidationFails() {
        var dto = BoletoDTO()
        dto.nome = "aa"
        dto.status = Status.PENDING
        dto.valor = 10.00
        dto.dataVencimento = getLocalDate()

        val violations = validator!!.validate(dto)
        assertEquals(violations.isEmpty(), false)
    }

    @Test
    fun ifNameSizeGreaterThan25_nameValidationFails() {
        var dto = BoletoDTO()
        dto.nome = "aaaaaaaaaaaaaaaaaaaaaaaaab"
        dto.status = Status.PENDING
        dto.valor = 10.00
        dto.dataVencimento = getLocalDate()

        val violations = validator!!.validate(dto)
        assertEquals(violations.isEmpty(), false)
    }

    @Test
    fun ifAmountSizeNotInRange_valorValidationFails() {
        var dto = mock()
        dto.valor = 1111111.00

        val violations = validator!!.validate(dto)
        assertEquals(violations.isEmpty(), false)
    }

    @Test
    fun ifAmounIsNegative_valorValidationFails() {
        var dto = mock()
        dto.valor = -1.00

        val violations = validator!!.validate(dto)
        assertEquals(violations.isEmpty(), false)
    }

    @Test
    fun givenPastDate_thenValidationFaills() {
        var dto = mock()
        dto.dataVencimento?.minusDays(1L)

        val violations = validator!!.validate(dto)
        assertEquals(violations.isEmpty(), false)
    }

    @Test(expected = DateTimeParseException::class)
    fun givenWrongDateFormat_thenValidationFaills() {
        var dto = mock()
        dto.dataVencimento = LocalDate.parse("31-10-2018")
    }

    @Test
    fun givenPresentDate_thenValidationSuccess() {
        var dto = mock()
        dto.dataVencimento = LocalDate.now()

        val violations = validator!!.validate(dto)
        assertEquals(violations.isEmpty(), true)
    }

    @Test
    fun givenFutureDate_thenValidationSuccess() {
        var dto = mock()
        dto.dataVencimento?.plusDays(1L)

        val violations = validator!!.validate(dto)
        assertEquals(violations.isEmpty(), true)
    }

    @Test
    fun ifStatusIsNull_nameValidationFails() {
        var dto = mock()
        dto.status = null

        val violations = validator!!.validate(dto)
        assertEquals(violations.isEmpty(), false)
    }
}