package org.yanez.junit5app.models;

import org.yanez.junit5app.exceptions.NotEnoughMoneyException;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private String person;
    private BigDecimal balance;
    private Bank bank;

    public Account(String person, BigDecimal balance) {
        this.person = person;
        this.balance = balance;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void debit(BigDecimal decimal) {
        BigDecimal newBalance = this.balance.subtract(decimal);
        if (newBalance.intValue() < 1) {
            throw new NotEnoughMoneyException("Not enough money");
        }
        this.balance = newBalance;
    }

    public void credit(BigDecimal decimal) {
        BigDecimal newBalance = this.balance.add(decimal);
        if (newBalance.intValue() < 1) {
            throw new NotEnoughMoneyException("Not enough money");
        }
        this.balance = newBalance;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return Objects.equals(person, account.person) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, balance);
    }
}
