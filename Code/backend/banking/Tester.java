package backend.banking;

import backend.banking.builder.AccountData;
import backend.banking.dto.AccountDTO;
import backend.banking.service.BankingAccountService;
import backend.commons.*;

public class Tester {
    public static void main(String[] args) {
        AccountService accountService = BankingAccountService.getInstance();

        Customer renuka = new Customer(1, "Renuka Mohanraj", "1000N Fourth Street",
                "Fairfield", "Iowa", 52557, "renuka@miu.edu");
        Customer sabi = new Customer(1, "Sabi Shresthaq", "147 Hillcrest Avenue",
                "West Hartford", "Connecticut", 06110, "care.sabi@gmail.com");
        // create 2 accounts;
        AccountData accountData1 = AccountData.builder()
                .accountNumber("1263862")
                .accountType("Savings")
                .customer(renuka).build();

        AccountData accountData2 = AccountData.builder()
                .accountNumber("4253892")
                .accountType("Checking")
                .customer(sabi).build();
        accountService.createAccount(accountData1);
        accountService.createAccount(accountData2);
        // use account 1;
        accountService.deposit("1263862", 240);
        accountService.deposit("1263862", 529);
        accountService.withdraw("1263862", 230);
        // use account 2;
        accountService.deposit("4253892", 12450);
        accountService.transferFunds("4253892", "1263862", 100, "payment of invoice 10232");
        // show balances

        for (Account account : accountService.getAllAccounts()) {
            Customer customer = account.getCustomer();
            Log.getLogger().write("Statement for Account: " + account.getAccountNumber());
            Log.getLogger().write("Account Holder: " + customer.getName());

            Log.getLogger().write("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");

            for (AccountEntry entry : account.getAccountEntries()) {
                System.out.printf("%30s%30s%20.2f\n",
                        entry.getDate().toString(),
                        entry.getDescription(),
                        entry.getAmount());
            }

            Log.getLogger().write("----------------------------------------" + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
        }
    }

}
