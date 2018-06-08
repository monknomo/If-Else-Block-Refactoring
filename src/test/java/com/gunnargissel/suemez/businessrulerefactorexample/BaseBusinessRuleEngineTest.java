package com.gunnargissel.suemez.businessrulerefactorexample;

public class BaseBusinessRuleEngineTest {


    public static WidgetTransfer getOkTransfer() {
        Customer transferer = new Customer("Alice", "A");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);

        Customer transferee = new Customer("Bob", "A");
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);

        return new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 999, "200", "213");
    }

    public static WidgetTransfer getInsufficientBalanceTransfer() {
        Customer transferer = new Customer("Alice", "A");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);

        Customer transferee = new Customer("Bob", "A");
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);

        return new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 1001, "200", "213");
    }

    public static WidgetTransfer getType200BadAreaTransfer() {
        Customer transferer = new Customer("Alice", "A");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);

        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);

        return new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 500, "200", "999");
    }

    public static WidgetTransfer getDirigibleEligibleTransfer(){
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);

        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);

        return new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 500, "200", "907");
    }

    public static WidgetTransfer getType710TransferRuleTransfer(){
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);

        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);

        return new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 500, "710", "907");
    }

    public static WidgetTransfer getTypeITransferRuleTransfer() {
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(1000);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);

        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);

        return new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 500, "I", "907");
    }

    public static WidgetTransfer getTypeITransferTooBigTransfer() {
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(1000002);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);

        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);

        return new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 1000001, "I", "907");
    }

    public static WidgetTransfer getMultiErrorTransfer(){
        Customer transferer = new Customer("Alice", "D");
        WidgetAccount fromAccount = new WidgetAccount(0);
        String fromAccountNum = "a0001";
        transferer.addAccount(fromAccountNum, fromAccount);

        Customer transferee = new Customer("Bob", "A");
        WidgetAccount toAccount = new WidgetAccount(500);
        String toAccountNum = "b0001";
        transferer.addAccount(toAccountNum, fromAccount);

        return new WidgetTransfer(transferer, fromAccountNum, transferee, toAccountNum, 1000001, "I", "907");
    }

}
