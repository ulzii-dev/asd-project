package backend.commons;

public class AccountTransaction {
    Action tranxAction;
    double tranxAmount = 0d;

    public AccountTransaction(Action action, double amount){
        tranxAction = action;
        tranxAmount = amount;
    }

    public String toString(){
        return tranxAction + ": [" + tranxAmount +"]";
    }
}
