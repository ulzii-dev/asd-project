package backend.creditcard.commons;

import backend.banking.visitor.Visitor;
import backend.commons.Account;
import backend.commons.AccountEntry;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreditAccount extends Account {
    public CreditAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public double accept(Visitor visitor) {
        return 0;
    }

    public void getPaymentDueDate(){

    }


    public double getPreviousBalance() {
        LocalDate todaydate = LocalDate.now();
        return this.getAccountEntries().stream()
                .filter(accountEntry -> accountEntry.getDate().isBefore(todaydate.withDayOfMonth(1)))
                .mapToDouble(AccountEntry::getAmount).sum();

        //.......................................................

        ArrayList<String> listOfAccntNumbers = new ArrayList<String>();

        for (AccountEntry value : getAccountEntries()) {
             if(value.getDate().isBefore(todaydate.withDayOfMonth(1))){

        }

        return listOfAccntNumbers;
    }

    }


}
