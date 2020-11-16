package ru.indraft.utils;

import org.junit.jupiter.api.Test;
import ru.indraft.model.Currency;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyUtilsTest {

    @Test
    void format() {
        var value = new BigDecimal("1.90");
        assertEquals(MoneyUtils.format(value, Currency.RUB), "1,90 ₽");
    }
}