/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor2;

import com.gunnargissel.suemez.businessrulerefactorexample.Customer;
import com.gunnargissel.suemez.businessrulerefactorexample.WidgetAccount;
import com.gunnargissel.suemez.businessrulerefactorexample.WidgetTransfer;
import static junit.framework.Assert.assertEquals;
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
        String error = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertEquals("", error);
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
        String error = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertEquals("Insufficient balance to transfer ; ", error);
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
        String error = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertEquals("This area is not a transfer eligible area. ; ", error);
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
        String error = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertEquals("D Category Transferer can only be transferred in transfer area 213. ; ", error);
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
        String error = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertEquals("This area is not a transfer eligible area. ; ", error);
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
        String error = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertEquals("Amount is too small for I type transfer. ; ", error);
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
        String error = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertEquals("This transfer is too large. ; ", error);
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
        String error = BusinessRulesEngine.checkWidgetTransfer(xfer);
        assertEquals("Insufficient balance to transfer ; This transfer is too large. ; ", error);
    }
}
