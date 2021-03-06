/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor3;

import com.gunnargissel.suemez.businessrulerefactorexample.*;
import java.util.function.Predicate;

/**
 *
 * @author monknomo
 */
public class BusinessRulesEngine {

    static final Predicate<WidgetTransfer> suffientAmount = trans -> trans.getTransferer().getAccount(trans.getFromAccount()).getBalance().compareTo(trans.getAmount()) < 0;
    static final Predicate<WidgetTransfer> isPartner = trans -> trans.getTransferTypeCode().equals("200");
    static final Predicate<WidgetTransfer> isFriendsAndFamily = trans -> trans.getTransferTypeCode().equals("710");
    static final Predicate<WidgetTransfer> isFriendAndFamilyDiscountLegal = trans -> trans.getAreaCode().matches("574|213|363|510");
    static final Predicate<WidgetTransfer> isPartneringArea = trans -> trans.getAreaCode().matches("907|412|213");
    static final Predicate<WidgetTransfer> isDirigibleForbiddenArea = trans -> trans.getAreaCode().matches("213");
    static final Predicate<WidgetTransfer> isDirigibleCategory = trans -> trans.getTransferer().getCategory().equals("D");
    static final Predicate<WidgetTransfer> isInternal = trans -> trans.getTypeCode().equals("I");
    static final Predicate<WidgetTransfer> isBlockSize = trans -> isBlockSize(trans);
    static final Predicate<WidgetTransfer> isTotalOverCap = trans -> isTotalOverCap(trans);

    public static final String checkWidgetTransfer(WidgetTransfer transfer) {
        String businessRuleErrors = "";

        if (suffientAmount.test(transfer)) {
            businessRuleErrors += "Insufficient balance to transfer ; ";
        }

        if (isPartner.and(isPartneringArea.negate()).test(transfer)) {
            businessRuleErrors += "This area is not a transfer eligible area. ; ";
        }

        if (isPartner.and(isDirigibleForbiddenArea.negate()).and(isDirigibleCategory).test(transfer)) {
            businessRuleErrors += "D Category Transferer can only be transferred in transfer area 213. ; ";
        }

        if (isFriendsAndFamily.and(isFriendAndFamilyDiscountLegal.negate()).test(transfer)) {
            businessRuleErrors += "This area is not a transfer eligible area. ; ";
        }

        if (isInternal.and(isBlockSize).test(transfer)) {
            businessRuleErrors += "Amount is too small for I type transfer. ; ";
        }

        if (isInternal.and(isTotalOverCap).test(transfer)) {
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
