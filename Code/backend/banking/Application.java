package backend.banking;

import backend.commons.*;
import factory.AccountFactory;
import factory.PersonalAccountFactory;

public class Application {
    public static void main(String[] args) {
        AccountFactory personalAccountFactory = new PersonalAccountFactory();
        AccountService personalAccountService = new AccountServiceImpl(personalAccountFactory);

        // create 2 accounts;
        personalAccountService.createAccount("1263862", "Frank Brown");
        personalAccountService.createAccount("4253892", "John Doe");
        // use account 1;
        personalAccountService.deposit("1263862", 240);
        personalAccountService.deposit("1263862", 529);
        personalAccountService.withdraw("1263862", 230);
        // use account 2;
        personalAccountService.deposit("4253892", 12450);
        personalAccountService.transferFunds("4253892", "1263862", 100, "payment of invoice 10232");
        // show balances

        for (Account account : personalAccountService.getAllAccounts()) {
            Customer customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountNumber());
            System.out.println("Account Holder: " + customer.getName());

            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");

            for (AccountEntry entry : account.getAccountEntries()) {
                System.out.printf("%30s%30s%20.2f\n",
                        entry.getDate().toString(),
                        entry.getDescription(),
                        entry.getAmount());
            }

            System.out.println("----------------------------------------" + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
        }
    }

}
