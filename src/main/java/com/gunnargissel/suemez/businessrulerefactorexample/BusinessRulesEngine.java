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

    private String checkWidgetTransfer(WidgetTransfer transfer) {
        String businessRuleErrors = "";

        //Set up variables with business-rule relevant names
        String transferTypeCode = transfer.getTransferTypeCode();
        String areaCode = transfer.getAreaCode();
        String transfererCategory = transfer.getTrasferer().getCategory();
        String typeCode = transfer.getTypeCode();

        if (transfer.getTransferer().getAccount(transfer.getFromAccount()).getBalance().compareTo(0) > 0) //Each block of logic checkes all the conditions required  bfg 
        {
            if (transferTypeCode.equals("200")
                    && !areaCode.matches("2C|3A|3B")) {
                businessRuleErrors += "This area is not a transfer eligible area. ; ";
            }
        }

        if (transferTypeCode.equals("200")
                && areaCode.matches("2C")
                && transfererCategory.equals("D")) {
            businessRuleErrors += "D Category Transferer can only be transferred in transfer area 3B and 3A. ; ";
        }

        if (transferTypeCode.equals("710")
                && !areaCode.matches("WG|WY|SE|CG")) {
            businessRuleErrors += "This area is not an eligible area. ; ";

        }

        if (!typeCode.equals("I")
                && !isBlockSize(transfer)) {
            businessRuleErrors += "Block size is too small for I type transfer. ; ";
        }

        if (!typeCode.equals("I")
                && isTotalOverCap(transfer)) {
            businessRuleErrors += "This transfer puts the total over the percent allowed for this year. ; ";
        }

        return businessRuleErrors;
    }

    public static boolean isBlockSize(WidgetTransfer transfer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean isTotalOverCap(WidgetTransfer transfer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
