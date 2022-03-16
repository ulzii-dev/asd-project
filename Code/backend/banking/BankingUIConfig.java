package backend.banking;

import backend.commons.Account;
import framework.domain.PersonalAccount;
import framework.ui.UIConfig;

import java.util.Arrays;
import java.util.Collection;

//TODO: WHAT DOES THIS DO?
public class BankingUIConfig implements UIConfig {
    @Override
    public Collection<String> getAccountTypes() {
        return Arrays.asList(BankingAccountType.CHECKING.name(), BankingAccountType.SAVINGS.name());
    }

    @Override
    public Collection<String> getReportColumnNames() {
        return Arrays.asList("AccountNumber","Name","City","P/C","Ch/S","Amount");
    }

    @Override
    public int getIdColumnIndex() {
        return 0;
    }

    @Override
    public Object[] buildRow(Account account) {
        Object[] rowdata = new Object[6];
        rowdata[0] = account.getAccountNumber();
		rowdata[1] = account.getCustomer().getName();
		rowdata[2] = account.getCustomer().getCity();
		rowdata[3] = account.getCustomer() instanceof PersonalAccount ? "P" : "C";
		rowdata[4] = account.getAccountType();
		rowdata[5] = String.valueOf(account.getBalance());
        return rowdata;
    }

    @Override
    public boolean hasReport() {
        return false;
    }
}
