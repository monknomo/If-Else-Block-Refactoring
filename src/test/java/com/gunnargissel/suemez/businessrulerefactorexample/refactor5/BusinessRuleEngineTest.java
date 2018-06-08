/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor5;

import com.gunnargissel.suemez.businessrulerefactorexample.BaseBusinessRuleEngineTest;
import com.gunnargissel.suemez.businessrulerefactorexample.Customer;
import com.gunnargissel.suemez.businessrulerefactorexample.WidgetAccount;
import com.gunnargissel.suemez.businessrulerefactorexample.WidgetTransfer;
import static junit.framework.Assert.*;
import org.junit.Test;

/**
 *
 * @author monknomo
 */
public class BusinessRuleEngineTest extends BaseBusinessRuleEngineTest {

    @Test
    public void okTransfer(){
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(getOkTransfer());
       assertFalse( result.hasErrors());
    }

    @Test
    public void insufficientBalance(){
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(getInsufficientBalanceTransfer());
        assertTrue(result.hasErrors());
        assertEquals("Insufficient balance to transfer ; ", result.getErrors());
    }

    @Test
    public void type200BadArea(){
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(getType200BadAreaTransfer());
        assertTrue(result.hasErrors());
        assertEquals("This area is not a transfer eligible area. ; ", result.getErrors());
    }

    @Test
    public void dirigibleTransferRule(){
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(getDirigibleEligibleTransfer());
        assertTrue(result.hasErrors());
        assertEquals("D Category Transferer can only be transferred in transfer area 213. ; ", result.getErrors());
    }

    @Test
    public void type710TransferRule(){
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(getType710TransferRuleTransfer());
        assertTrue(result.hasErrors());
        assertEquals("This area is not a transfer eligible area. ; ", result.getErrors());
    }

    @Test
    public void typeITransferRule(){
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(getTypeITransferRuleTransfer());
        assertTrue(result.hasErrors());
        assertEquals("Amount is too small for I type transfer. ; ", result.getErrors());
    }

    @Test
    public void typeITransferTooBigRule(){
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(getTypeITransferTooBigTransfer());
        assertTrue(result.hasErrors());
        assertEquals("This transfer is too large. ; ", result.getErrors());
    }

    @Test
    public void multiError(){
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(getMultiErrorTransfer());
        assertTrue(result.hasErrors());
        assertEquals("Insufficient balance to transfer ; This transfer is too large. ; ", result.getErrors());
    }
}
