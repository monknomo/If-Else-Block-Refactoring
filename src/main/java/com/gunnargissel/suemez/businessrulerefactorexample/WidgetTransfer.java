/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample;

/**
 * The represents a transfer of widgets between Customer WidgetAccounts
 * @author monknomo
 */
public class WidgetTransfer {

    Customer transferer;
    String fromAccount;
    Customer transferee;
    String toAccount;
    Integer amount;
    private String typeCode;
    private String areaCode;

    public WidgetTransfer(Customer transferer, String fromAccount, Customer transferee, String toAccount, Integer amount, String typeCode, String areaCode) {
        this.transferer = transferer;
        this.fromAccount = fromAccount;
        this.transferee = transferee;
        this.toAccount = toAccount;
        this.amount = amount;
        this.typeCode = typeCode;
        this.areaCode = areaCode;
    }

    public Customer getTransferer() {
        return transferer;
    }

    public void setTransferer(Customer transferer) {
        this.transferer = transferer;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Customer getTransferee() {
        return transferee;
    }

    public void setTransferee(Customer transferee) {
        this.transferee = transferee;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getTransferTypeCode() {
        return typeCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

}
