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
    Map<String,WidgetAccount> account = new HashMap<>();

    String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    WidgetAccount getAccount(WidgetAccount fromAccount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
