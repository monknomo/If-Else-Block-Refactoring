/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor5;

import com.gunnargissel.suemez.businessrulerefactorexample.refactor4.*;
import com.gunnargissel.suemez.businessrulerefactorexample.refactor3.*;
import com.gunnargissel.suemez.businessrulerefactorexample.*;
import java.util.function.Predicate;

/**
 *
 * @author monknomo
 */
public class BusinessRulesEngine {

    static final Predicate<WidgetTransfer> suffientAmount = trans -> trans.getTransferer().getAccount(trans.getFromAccount()).getBalance().compareTo(trans.getAmount()) > 0;
    static final Predicate<WidgetTransfer> isPartner = trans -> trans.getTransferTypeCode().equals("200");
    static final Predicate<WidgetTransfer> isFriendsAndFamily = trans -> trans.getTransferTypeCode().equals("710");
    static final Predicate<WidgetTransfer> isFriendAndFamilyDiscountLegal = trans -> trans.getAreaCode().matches("574|213|363|510");
    static final Predicate<WidgetTransfer> isPartneringArea = trans -> trans.getAreaCode().matches("907|412|213");
    static final Predicate<WidgetTransfer> isDirigibleArea = trans -> trans.getAreaCode().matches("213");
    static final Predicate<WidgetTransfer> isDirigibleCategory = trans -> trans.getTransferer().getCategory().equals("D");
    static final Predicate<WidgetTransfer> isInternal = trans -> trans.getTypeCode().equals("I");
    static final Predicate<WidgetTransfer> isBlockSize = trans -> isBlockSize(trans);
    static final Predicate<WidgetTransfer> isTotalOverCap = trans -> isTotalOverCap(trans);
    
    static final Predicate<WidgetTransfer> parterTransferReqs = trans -> isPartner.and(isPartneringArea.negate()).test(trans);
    static final Predicate<WidgetTransfer> dirigibleTransferReqs = trans -> isPartner.and(isDirigibleArea).and(isDirigibleCategory).test(trans);
    static final Predicate<WidgetTransfer> friendsAndFamilyReqs = trans -> isFriendsAndFamily.and(isFriendAndFamilyDiscountLegal.negate()).test(trans);
    static final Predicate<WidgetTransfer> internalBlockReqs = trans -> isInternal.negate().and(isBlockSize.negate()).test(trans);
    static final Predicate<WidgetTransfer> internalTotalCapReqs = trans -> isInternal.negate().and(isTotalOverCap.negate()).test(trans);
    
    public static final Result<WidgetTransfer> checkWidgetTransfer(WidgetTransfer transfer) {
        Validator<WidgetTransfer> widgetTransferValidator = new Validator();
        widgetTransferValidator.addRule(suffientAmount, "Insufficient balance to transfer.");
        widgetTransferValidator.addRule(parterTransferReqs, "This area is not a transfer eligible area.");
        widgetTransferValidator.addRule(dirigibleTransferReqs, "D Category Transferer can only be transferred in transfer area 213.");
        widgetTransferValidator.addRule(friendsAndFamilyReqs, "This area is not an eligible area.");
        widgetTransferValidator.addRule(internalBlockReqs, "Amount is too small for I type transfer.");
        widgetTransferValidator.addRule(internalTotalCapReqs, "This transfer is too large.");
        return widgetTransferValidator.validate(transfer);
    }

    public static boolean isBlockSize(WidgetTransfer transfer) {
        return transfer.getAmount().compareTo(1000) < 0;
    }

    public static boolean isTotalOverCap(WidgetTransfer transfer) {
        return transfer.getAmount().compareTo(1000000) > 0;
    }
}
