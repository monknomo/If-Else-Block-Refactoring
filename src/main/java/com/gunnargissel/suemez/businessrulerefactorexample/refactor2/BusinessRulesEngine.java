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

    static final Predicate<WidgetTransfer> suffientAmount = trans -> trans.getTransferer().getAccount(trans.getFromAccount()).getBalance().compareTo(trans.getAmount()) < 0;
    static final Predicate<String> isPartner = ttc -> ttc.equals("200");
    static final Predicate<String> isFriendsAndFamily = ttc -> ttc.equals("710");
    static final Predicate<String> isFriendAndFamilyDiscountLegal = ac -> ac.matches("574|213|363|510");
    static final Predicate<String> isPartneringArea = ac -> ac.matches("907|412|213");
    static final Predicate<String> dirigiblesForbiddenArea = ac -> ac.matches("213");
    static final Predicate<String> isDirigibleCategory = cat -> cat.equals("D");
    static final Predicate<String> isInternal = tc -> tc.equals("I");

    public static final String checkWidgetTransfer(WidgetTransfer transfer) {
        String businessRuleErrors = "";

        String transferTypeCode = transfer.getTransferTypeCode();
        String areaCode = transfer.getAreaCode();
        String category = transfer.getTransferer().getCategory();
        String typeCode = transfer.getTypeCode();

        if (suffientAmount.test(transfer)) {
            businessRuleErrors += "Insufficient balance to transfer ; ";
        }

        if (isPartner.test(transferTypeCode)
                && isPartneringArea.negate().test(areaCode)) {
            businessRuleErrors += "This area is not a transfer eligible area. ; ";
        }

        if (isPartner.test(transferTypeCode)
                && dirigiblesForbiddenArea.negate().test(areaCode)
                && isDirigibleCategory.test(category)) {
            businessRuleErrors += "D Category Transferer can only be transferred in transfer area 213. ; ";
        }

        if (isFriendsAndFamily.test(transferTypeCode)
                && isFriendAndFamilyDiscountLegal.negate().test(areaCode)) {
            businessRuleErrors += "This area is not a transfer eligible area. ; ";

        }

        if (isInternal.test(typeCode)
                && isBlockSize(transfer)) {
            businessRuleErrors += "Amount is too small for I type transfer. ; ";
        }

        if (isInternal.test(typeCode)
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
