package edu.miu.cs525.banking.visitor;

import edu.miu.cs525.banking.domain.CheckingAccount;
import edu.miu.cs525.banking.domain.SavingsAccount;
import edu.miu.cs525.creditcard.constant.CreditCardType;
import edu.miu.cs525.creditcard.domain.CreditCardAccount;

import java.text.DecimalFormat;

public class InterestComputerVisitor implements Visitor {
    //DecimalFormat df = new DecimalFormat("##.##");

    @Override
    public double visit(SavingsAccount savingsAccount) {
        return (savingsAccount.getBalance() < 50000) ? savingsAccount.getBalance() * .015 : savingsAccount.getBalance() * .025;
    }

    @Override
    public double visit(CheckingAccount checkingAccounts) {
        return (checkingAccounts.getBalance() < 50000) ? checkingAccounts.getBalance() * .015 : checkingAccounts.getBalance() * .025;
    }

    @Override
    public double visit(CreditCardAccount creditCardAccount) {
        return creditCardAccount.getAccountType().equals(CreditCardType.GOLD.name()) ? creditCardAccount.getBalance() * 0.06 :
               creditCardAccount.getAccountType().equals(CreditCardType.SILVER.name()) ? creditCardAccount.getBalance() * 0.08 :
               creditCardAccount.getAccountType().equals(CreditCardType.BRONZE.name()) ? creditCardAccount.getBalance() * 0.1 : 0;
    }
}
