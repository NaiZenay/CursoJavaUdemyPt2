package org.yanez.junit5app.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;
    private String nombre;

    public Bank() {
        this.accounts= new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account){
        accounts.add(account);
        account.setBank(this);
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void transfer(Account origin, Account destination, BigDecimal amount){
        origin.debit(amount);
        destination.credit(amount);
    }
}
