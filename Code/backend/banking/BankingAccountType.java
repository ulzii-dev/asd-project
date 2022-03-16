package backend.banking;

public enum BankingAccountType {
    CHECKING ("Checking"),
    SAVINGS ("Savings");

    public final String name;
    private BankingAccountType(String name){
        this.name = name;
    }
}


