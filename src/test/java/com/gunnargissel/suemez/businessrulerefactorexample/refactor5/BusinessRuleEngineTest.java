/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor5;

import com.gunnargissel.suemez.businessrulerefactorexample.Customer;
import com.gunnargissel.suemez.businessrulerefactorexample.WidgetAccount;
import com.gunnargissel.suemez.businessrulerefactorexample.WidgetTransfer;
import static junit.framework.Assert.*;
import org.junit.Test;

/**
 *
 * @author monknomo
 */
public class BusinessRuleEngineTest {
    
    @Test
    public void okTransfer(){
        Customer transferer = new Customer("Alice", "A");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);
        
        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);
        
        WidgetTransfer xfer = new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 999, "200", "213");
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(xfer);
       assertFalse( result.hasErrors());
    }
    
    @Test
    public void insufficientBalance(){
        Customer transferer = new Customer("Alice", "A");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);
        
        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);
        
        WidgetTransfer xfer = new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 1001, "200", "213");
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertTrue(result.hasErrors());
        assertEquals("Insufficient balance to transfer ; ", result.getErrors());
    }
    
    @Test
    public void type200BadArea(){
        Customer transferer = new Customer("Alice", "A");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);
        
        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);
        
        WidgetTransfer xfer = new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 500, "200", "999");
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertTrue(result.hasErrors());
        assertEquals("This area is not a transfer eligible area. ; ", result.getErrors());
    }
    
    @Test
    public void dirigibleTransferRule(){
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);
        
        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);
        
        WidgetTransfer xfer = new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 500, "200", "907");
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertTrue(result.hasErrors());
        assertEquals("D Category Transferer can only be transferred in transfer area 213. ; ", result.getErrors());
    }
    
    @Test
    public void type710TransferRule(){
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);
        
        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);
        
        WidgetTransfer xfer = new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 500, "710", "907");
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertTrue(result.hasErrors());
        assertEquals("This area is not a transfer eligible area. ; ", result.getErrors());
    }
    
    @Test
    public void typeITransferRule(){
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);
        
        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);
        
        WidgetTransfer xfer = new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 500, "I", "907");
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertTrue(result.hasErrors());
        assertEquals("Amount is too small for I type transfer. ; ", result.getErrors());
    }

    @Test
    public void typeITransferTooBigRule(){
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(1000002);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);
        
        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);
        
        WidgetTransfer xfer = new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 1000001, "I", "907");
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertTrue(result.hasErrors());
        assertEquals("This transfer is too large. ; ", result.getErrors());
    }
    
    @Test
    public void multiError(){
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(0);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);
        
        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);
        
        WidgetTransfer xfer = new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 1000001, "I", "907");
        Result<WidgetTransfer> result = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertTrue(result.hasErrors());
        assertEquals("Insufficient balance to transfer ; This transfer is too large. ; ", result.getErrors());
    }
}
