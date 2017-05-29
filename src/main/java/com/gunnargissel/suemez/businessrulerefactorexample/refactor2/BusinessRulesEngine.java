/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor2;

import com.gunnargissel.suemez.businessrulerefactorexample.*;
import java.util.function.Predicate;

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

        Predicate<String> isPartner = ttc -> ttc.equals("200");
        Predicate<String> isFriendsAndFamily = ttc -> ttc.equals("710");
        Predicate<String> isFriendAndFamilyDiscountLegal = ac -> ac.matches("574|213|363|510");
        Predicate<String> isPartneringArea = ac -> ac.matches("907|412|213");
        Predicate<String> isDirigibleArea = ac -> ac.matches("213");
        Predicate<String> isDirigibleCategory = cat -> cat.equals("D");
        Predicate<String> isInternal = tc -> tc.equals("I");

        if (transfer.getTransferer().getAccount(transfer.getFromAccount()).getBalance().compareTo(transfer.getAmount()) > 0) {
            businessRuleErrors += "Insufficient balance to transfer ; ";
        }

        if (isPartner.test(transferTypeCode)
                && isPartneringArea.negate().test(areaCode)) {
            businessRuleErrors += "This area is not a transfer eligible area. ; ";
        }

        if (isPartner.test(transferTypeCode)
                && isDirigibleArea.test(areaCode)
                && isDirigibleCategory.test(category)) {
            businessRuleErrors += "D Category Transferer can only be transferred in transfer area 213. ; ";
        }

        if (isFriendsAndFamily.test(transferTypeCode)
                && isFriendAndFamilyDiscountLegal.negate().test(areaCode)) {
            businessRuleErrors += "This area is not an eligible area. ; ";

        }

        if (isInternal.negate().test(typeCode)
                && !isBlockSize(transfer)) {
            businessRuleErrors += "Amount is too small for I type transfer. ; ";
        }

        if (isInternal.negate().test(typeCode)
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
