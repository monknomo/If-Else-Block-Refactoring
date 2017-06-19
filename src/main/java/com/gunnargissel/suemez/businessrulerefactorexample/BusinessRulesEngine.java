/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample;

/**
 * This is the original business rule method.  It analyzes a WidgetTransfer object for rule violation, and returns any errors that may be present
 * @author monknomo
 */
public class BusinessRulesEngine {

    public static final String checkWidgetTransfer(WidgetTransfer transfer) {
        String businessRuleErrors = "";

        if (transfer.getTransferer().getAccount(transfer.getFromAccount()).getBalance().compareTo(transfer.getAmount()) < 0) {
            businessRuleErrors += "Insufficient balance to transfer ; ";
        }

        if (transfer.getTransferTypeCode().equals("200")) {
            if (!transfer.getAreaCode().matches("907|412|213")) {
                businessRuleErrors += "This area is not a transfer eligible area. ; ";
            } else if (!transfer.getAreaCode().matches("213")) {
                if (transfer.getTransferer().getCategory().equals("D")) {
                    businessRuleErrors += "D Category Transferer can only be transferred in transfer area 213. ; ";
                }
            }
        } else if (transfer.getTransferTypeCode().equals("710")) {
            if (!transfer.getAreaCode().matches("574|213|363|510")) {
                businessRuleErrors += "This area is not a transfer eligible area. ; ";
            }
        }

        if (transfer.getTypeCode().equals("I")) {
            if (isBlockSize(transfer)) {
                businessRuleErrors += "Amount is too small for I type transfer. ; ";
            }
            if (isTotalOverCap(transfer)) {
                businessRuleErrors += "This transfer is too large. ; ";
            }

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
