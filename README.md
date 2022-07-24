# cs525_project

Two Java Swing applications BankingAccount and CreditCard that the common part should abstract out to  framework.

For the framework design, use the account pattern, the party pattern, and the observer pattern.
We will call the framework the observable party account

### Requirements

#### Use cases to the banking system
* Create a personal account
* Create a Company account (checking or savings)
* Deposit money
* Withdraw money
* Add interest
* Generate a report of accounts

#### The operations for each action are:

* When the add-interest button is pressed, the system adds interest to all accounts in the bank.
* When a deposit or withdrawal is done to a company account, the system sends the company an
Email about the transaction.
* When a deposit or withdrawal is done to a personal account, and the amount was larger than $500
or the resulting amount is negative, the bank sends the person an Email about the transaction.
* Customers can have more than one account. The bank system needs to keep track of the history of
deposits and withdrawals.

#### Use cases to the credit card system
* Create a personal account
* Create a Company account (checking or savings)
* Deposit money
* Withdraw money
* Add interest
* Generate a report of accounts

#### The operations for each action are:

* When a card is charged for more than $400, the system sends the cardholder an email about the
transaction.
* Customers can have more than one credit card (account)
* The system needs to keep track of the history of charges and payments. For every payment or
charge, the system needs to store the date, name, and amount of the transaction. Frameworks Project (Lab 1)
Cs525: Project 2
* The monthly billing report (generated from the monthly report button) should show:
    - previous balance: balance from last month
    - total charges: total of all charges for this month
    - total credits: total of all payments for this month
    - new balance = previous balance – total credits + total charges + MI * (previous balance – total credits)
    - total due = MP * new balance
* When a deposit or withdrawal is done to a company account, the system sends the company an
  Email about the transaction.
* When a deposit or withdrawal is done to a personal account, and the amount was larger than $400
  or the resulting amount is negative, the bank sends the person an Email about the transaction.
* Customers can have more than one account. The bank system needs to keep track of the history of
  deposits and withdrawals.

### Class Diagram
<p>
    <img src="Analysis/class diagram.png" alt="Logo" width="1000" height="500">
</p>

### Design Patterns

1.  Strategy Pattern:
    - Use when we want to calculate interest for each type of account (Personal Checking/Saving, Company Checking/Saving). Use InterestCalculator strategy.
    - Use when we want to calculate due payment of each type credit card account (Bronze, Silver, Gold). Use PaymentCalculator strategy.

2. Singleton Pattern:
    - Create only one instance of Main Form when each app want to use.
    - Create only one instance BankingAccountService, CreditAccountService. These look like service in the project should only need to instantiate one time.

3. Factory Method Pattern
   - To initialize account type when creating account. Using it mixing with strategy account to create account type for Personal Saving/Checking account, Company Saving/Checking account, Credit card Bronze/Silver/Gold.

4. Observer Pattern
   - Hook the Email sender to send email when there are change of balance in account.
   - Hook to Main Form to listen to the balance changing of account to update the grid on the UI.

5. Template method pattern
   - Use in FormTemplate to generate form with many settings, defer some step to the subclass to initialize the settings like tables, button, panel, scroll panel
    - generateForm
6. Command Pattern
   - Each command in the UI is a command and will execute the service: AddNewPersonalCommand, AddNewCompanyCommand, DepositCommand, WithdrawCommand, AddIntersestCommand, ReportCommand
   - Main Form is the Invoker, the AccounService is the receiver to implement the action of the execute command.


# Collaborators
1. Saruul-Ulzii Sampilnorov
2. Bijay Shrestha
3. Hussein Mazaar
4. Yohannes Woldemichael
5. Suraphel
