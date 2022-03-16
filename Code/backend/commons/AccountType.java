package backend.commons;

public enum AccountType {
    CHECKING ("Checking"),
    SAVINGS ("Savings");

    public final String name;
    private AccountType(String name){
        this.name = name;
    }
}
