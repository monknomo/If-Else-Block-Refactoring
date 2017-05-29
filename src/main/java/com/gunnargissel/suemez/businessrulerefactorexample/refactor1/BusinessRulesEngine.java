/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor1;

import com.gunnargissel.suemez.businessrulerefactorexample.*;

/**
 *
 * @author monknomo
 */
public class BusinessRulesEngine {

    public static final String checkWidgetTransfer(WidgetTransfer transfer) {
        String businessRuleErrors = "";
        
        String transferTypeCode = transfer.getTransferTypeCode();
        String areaCode = transfer.getAreaCode();
        String category = transfer.getTransferer().getCategory();
        String typeCode = transfer.getTypeCode();

        if (transfer.getTransferer().getAccount(transfer.getFromAccount()).getBalance().compareTo(transfer.getAmount()) > 0) {
            businessRuleErrors += "Insufficient balance to transfer ; ";
        }

        {
            if (transferTypeCode.equals("200")
                    && !areaCode.matches("907|412|213")) {
                businessRuleErrors += "This area is not a transfer eligible area. ; ";
            }
        }

        if (transferTypeCode.equals("200")
                && areaCode.matches("213")
                && category.equals("D")) {
            businessRuleErrors += "D Category Transferer can only be transferred in transfer area 213. ; ";
        }

        if (transferTypeCode.equals("710")
                && !areaCode.matches("574|213|363|510")) {
            businessRuleErrors += "This area is not an eligible area. ; ";

        }

        if (!typeCode.equals("I")
                && !isBlockSize(transfer)) {
            businessRuleErrors += "Amount is too small for I type transfer. ; ";
        }

        if (!typeCode.equals("I")
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
