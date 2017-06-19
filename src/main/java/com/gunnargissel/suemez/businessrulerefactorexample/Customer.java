/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a Customer, with all their WidgetAccounts, a name and a category
 * @author monknomo
 */
public class Customer {

    private final Map<String, WidgetAccount> accounts = new HashMap<>();
    private final String name;
    private final String category;

    public Customer(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
    
    public void addAccount(String accountName, WidgetAccount account){
        accounts.put(accountName, account);
    }

    public WidgetAccount getAccount(String fromAccount) {
        return accounts.get(fromAccount);
    }
}
