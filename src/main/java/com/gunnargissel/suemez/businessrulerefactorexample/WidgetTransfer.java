/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample;

/**
 *
 * @author monknomo
 */
public class WidgetTransfer {
    
    Customer transferer;
    WidgetAccount fromAccount;
    Customer transferee;
    WidgetAccount toAccount;
    Integer amount;

    public Customer getTransferer() {
        return transferer;
    }

    public void setTransferer(Customer transferer) {
        this.transferer = transferer;
    }

    public WidgetAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(WidgetAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Customer getTransferee() {
        return transferee;
    }

    public void setTransferee(Customer transferee) {
        this.transferee = transferee;
    }

    public WidgetAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(WidgetAccount toAccount) {
        this.toAccount = toAccount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    
    
    String getTransferTypeCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getAreaCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Customer getTrasferer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getTypeCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}
