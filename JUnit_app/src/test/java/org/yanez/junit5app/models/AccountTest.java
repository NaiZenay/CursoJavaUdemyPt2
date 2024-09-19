package org.yanez.junit5app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class AccountTest {
    @Test
    void test() {
        Account account = new Account("Andres", new BigDecimal("1000.0923"));
        String expected = "Andres";
        String result = account.getPerson();

        Assertions.assertEquals(expected, result);
        Assertions.assertEquals("Andres", result);
    }
}