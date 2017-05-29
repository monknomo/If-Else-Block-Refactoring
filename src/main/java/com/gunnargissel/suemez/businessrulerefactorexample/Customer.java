/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author monknomo
 */
public class Customer {

    Map<String, WidgetAccount> account = new HashMap<>();
    String name;
    private String category;

    public Customer(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public WidgetAccount getAccount(WidgetAccount fromAccount) {
        return account.get(this);
    }
}
