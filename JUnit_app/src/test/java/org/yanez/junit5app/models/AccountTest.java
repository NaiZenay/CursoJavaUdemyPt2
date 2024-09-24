package org.yanez.junit5app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.yanez.junit5app.exceptions.NotEnoughMoneyException;

import java.math.BigDecimal;


class AccountTest {
    @Test
    void test() {
        Account account = new Account("Andres", new BigDecimal("1000.0923"));
        String expected = "Andres";
        String result = account.getPerson();
        assertNotNull(result,()->"empty account");
        assertEquals(expected, result,"Account owner does not match");
        assertEquals("Andres", result,"Account owner does not match");
    }

    @Test
    void testBalanceAccount() {
        Account account = new Account("Andres", new BigDecimal("0"));
        assertNotNull(account.getBalance());
        assertEquals(0, account.getBalance().doubleValue());
        assertFalse(account.getBalance().intValue() < 0);
    }

    @Test
    void accountRefference() {
        Account account = new Account("John Doe", new BigDecimal("2734.23"));
        Account account1 = new Account("John Doe", new BigDecimal("2734.23"));

        assertEquals(account, account1);//Refactorizar Equals del obj
    }

    @Test
    void testDebitAccount() {
        Account account = new Account("John Doe", new BigDecimal("2734.23"));
        account.debit(new BigDecimal("2000"));
        assertNotNull(account.getBalance());
        assertEquals(734, account.getBalance().intValue());
        assertEquals("734", account.getBalance().toPlainString());
    }

    @Test
    void testCreditAccount() {
        Account account = new Account("John Doe", new BigDecimal("2000.23"));
        account.credit(new BigDecimal("2000"));
        assertNotNull(account.getBalance());
        assertEquals(4000, account.getBalance().intValue());
        assertEquals("4000", account.getBalance().toPlainString());
    }

    @Test
    void notEnoughMoneyExceptionAccount() {
        Account account = new Account("YO", new BigDecimal("1200.123"));
        Exception exception = assertThrows(NotEnoughMoneyException.class, () -> {
            account.debit(new BigDecimal(1500));
        });
        String msg = exception.getMessage();
        String exp = "Not enough money";
    }

    @Test
    void transferMoneyTest() {
        Account account = new Account("YO", new BigDecimal("1201.123"));
        Account account2 = new Account("Ese", new BigDecimal("1500.123"));
        Bank bank = new Bank();
        bank.setNombre("BBBBA");
        bank.transfer(account, account2, new BigDecimal(1200));
        assertEquals("2700.123", account2.getBalance().toPlainString());
        assertEquals("1.123", account.getBalance().toPlainString());

    }

    @Test
    void BankAccountsTest() {
        Account account = new Account("YO", new BigDecimal("1201.123"));
        Account account2 = new Account("Ese", new BigDecimal("1500.123"));
        Bank bank = new Bank();
        bank.setNombre("BBBBA");
        bank.addAccount(account);
        bank.addAccount(account2);

        bank.transfer(account, account2, new BigDecimal(1200));
        assertAll(
                ()-> assertEquals("2700.123", account2.getBalance().toPlainString()),
                ()-> assertEquals("1.123", account.getBalance().toPlainString()),
                ()-> assertEquals("BBBBA", account.getBank().getNombre()),
                ()-> assertEquals("YO", bank.getAccounts().stream()
                            .filter(c -> c.getPerson().equals("YO"))
                            .findFirst()
                            .get().getPerson()),
                ()-> assertTrue( bank.getAccounts().stream().anyMatch(c -> c.getPerson().equals("YO"))));
    }
}