/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample;

/**
 * This is a Customer's account with their Widget Balance
 * @author monknomo
 */
public class WidgetAccount {

    Integer balance;

    public WidgetAccount(Integer balance) {
        this.balance = balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBalance() {
        return balance;
    }
}
