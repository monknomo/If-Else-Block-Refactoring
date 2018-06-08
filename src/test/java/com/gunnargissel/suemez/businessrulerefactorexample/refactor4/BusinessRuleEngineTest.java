/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor4;

import com.gunnargissel.suemez.businessrulerefactorexample.BaseBusinessRuleEngineTest;
import com.gunnargissel.suemez.businessrulerefactorexample.Customer;
import com.gunnargissel.suemez.businessrulerefactorexample.WidgetAccount;
import com.gunnargissel.suemez.businessrulerefactorexample.WidgetTransfer;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author monknomo
 */
public class BusinessRuleEngineTest extends BaseBusinessRuleEngineTest{

    @Test
    public void okTransfer() {
        String error = BusinessRulesEngine.checkWidgetTransfer(getOkTransfer());
        assertEquals("", error);
    }

    @Test
    public void insufficientBalance() {
        String error = BusinessRulesEngine.checkWidgetTransfer(getInsufficientBalanceTransfer());
        assertEquals("Insufficient balance to transfer ; ", error);
    }

    @Test
    public void type200BadArea() {
        String error = BusinessRulesEngine.checkWidgetTransfer(getType200BadAreaTransfer());
        assertEquals("This area is not a transfer eligible area. ; ", error);
    }

    @Test
    public void dirigibleTransferRule() {
        String error = BusinessRulesEngine.checkWidgetTransfer(getDirigibleEligibleTransfer());
        assertEquals("D Category Transferer can only be transferred in transfer area 213. ; ", error);
    }

    @Test
    public void type710TransferRule() {
        String error = BusinessRulesEngine.checkWidgetTransfer(getType710TransferRuleTransfer());
        assertEquals("This area is not a transfer eligible area. ; ", error);
    }

    @Test
    public void typeITransferRule() {
        String error = BusinessRulesEngine.checkWidgetTransfer(getTypeITransferRuleTransfer());
        assertEquals("Amount is too small for I type transfer. ; ", error);
    }

    @Test
    public void typeITransferTooBigRule() {
        String error = BusinessRulesEngine.checkWidgetTransfer(getTypeITransferTooBigTransfer());
        assertEquals("This transfer is too large. ; ", error);
    }

    @Test
    public void multiError() {
        String error = BusinessRulesEngine.checkWidgetTransfer(getMultiErrorTransfer());
        assertEquals("Insufficient balance to transfer ; This transfer is too large. ; ", error);
    }
}
