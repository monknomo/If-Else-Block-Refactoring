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
public class BusinessRulesEngine {

    public static final String checkWidgetTransfer(WidgetTransfer transfer) {
        String businessRuleErrors = "";
        
        if (transfer.getTransferer().getAccount(transfer.getFromAccount()).getBalance().compareTo(transfer.getAmount()) > 0){
            businessRuleErrors += "Insufficient balance to transfer ; ";
        }
        {
            if (transfer.getTransferTypeCode().equals("200")
                    && !transfer.getAreaCode().matches("907|412|213")) {
                businessRuleErrors += "This area is not a transfer eligible area. ; ";
            }
        }

        if (transfer.getTransferTypeCode().equals("200")
                && transfer.getAreaCode().matches("213")
                && transfer.getTransferer().getCategory().equals("D")) {
            businessRuleErrors += "D Category Transferer can only be transferred in transfer area 213. ; ";
        }

        if (transfer.getTransferTypeCode().equals("710")
                && !transfer.getAreaCode().matches("574|213|363|510")) {
            businessRuleErrors += "This area is not an eligible area. ; ";

        }

        if (!transfer.getTypeCode().equals("I")
                && !isBlockSize(transfer)) {
            businessRuleErrors += "Amount is too small for I type transfer. ; ";
        }

        if (!transfer.getTypeCode().equals("I")
                && isTotalOverCap(transfer)) {
            businessRuleErrors += "This transfer is too large. ; ";
        }

        return businessRuleErrors;
    }

    public static boolean isBlockSize(WidgetTransfer transfer) {
        return transfer.getAmount().compareTo(1000) < 0;
    }

    public static boolean isTotalOverCap(WidgetTransfer transfer) {
       return transfer.getAmount().compareTo(1000000) > 0;
    }
}
