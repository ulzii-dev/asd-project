package edu.miu.cs525.commons.observer;

import edu.miu.cs525.commons.*;
import edu.miu.cs525.creditcard.service.CreditCardAccountService;
import edu.miu.cs525.framework.Observer;
import edu.miu.cs525.framework.domain.CompanyAccount;
import edu.miu.cs525.framework.domain.PersonalAccount;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EmailSender implements Observer {
    private final AccountService accountService;

    public EmailSender(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void update() {
        boolean emailHeaderAdded = false;
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Account, ArrayList<AccountTransaction>> entry : accountService.getAccountTransactions().entrySet()) {
            Account account = entry.getKey();
            List<AccountTransaction> transactions = entry.getValue();

            if (account.getCustomer() instanceof CompanyAccount || account.getCustomer() instanceof PersonalAccount) {

                for (Iterator<AccountTransaction> it = transactions.iterator(); it.hasNext(); ) {
                    AccountTransaction transaction = it.next();

                    if(!transaction.getTransactionAction().equals(Action.WITHDRAW) && !transaction.getTransactionAction().equals(Action.DEPOSIT)){
                        continue;
                    }

                    if (!emailHeaderAdded) {
                        sb.append("\n");
                        sb.append("OBSERVER_PATTERN: Pulling changed accounts from AccountService\n");
                        sb.append(" ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ Sending transaction emails ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿\n");
                        sb.append("|                                                                                       |\n");

                        emailHeaderAdded = true;
                    }

                    if(accountService instanceof CreditCardAccountService && transaction.getTransactionAction().equals(Action.WITHDRAW) && transaction.getTranxAmount() > 400) {
                        sb.append("       More than $400 withdraw amount!\n");
                        sb.append(generateEmailBody(account, transaction));
                    } else {
                        if (account.getCustomer() instanceof CompanyAccount) {
                            sb.append("       CompanyAccount\n");
                            sb.append(generateEmailBody(account, transaction));
                        } else if ((account.getCustomer() instanceof PersonalAccount && account.getBalance() < 0) || (account.getCustomer() instanceof PersonalAccount && transaction.getTranxAmount() > accountService.getCheckBalance())) {
                            sb.append("       PersonalAccount\n");
                            sb.append(generateEmailBody(account, transaction));
                        }
                    }

                    if (it.hasNext()) {
                        sb.append("|       ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿       |\n");
                    }
                }
            }
        }

        if (emailHeaderAdded) {
            sb.append("⎩＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ SENT ALL EMAILS ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿⎭\n");
        }
        Log.getLogger().write(sb.toString());
        accountService.clearChangedAccountList();
    }

    public String generateEmailBody(Account account, AccountTransaction accountTransaction) {
        StringBuilder sb = new StringBuilder();

        sb.append("       " + account.getCustomer().getName() + " [AccNO: " + account.getAccountNumber() + "]\n");
        sb.append("       Sending email to => " + account.getCustomer().getEmail() + " | " + accountTransaction + "\n");
        sb.append(account.getBalance() < 0 ? "       ❌ Negative BALANCE ❌\n" : "\n");

        return sb.toString();
    }
}