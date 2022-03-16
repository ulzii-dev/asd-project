package backend.creditcard;

import backend.commons.Account;
import framework.ui.UIConfig;

import java.util.Arrays;
import java.util.Collection;

public class CreditCardUIConfig implements UIConfig {
    @Override
    public Collection<String> getAccountTypes() {
        return Arrays.asList(
                CreditCardType.GOLD.name(),
                CreditCardType.SILVER.name(),
                CreditCardType.BRONZE.name());
    }

    @Override
    public Collection<String> getReportColumnNames() {
        return Arrays.asList("Name","CC number","Exp Date","Type","Balance");
    }

    @Override
    public int getIdColumnIndex() {
        return 1;
    }

    @Override
    public Object[] buildRow(Account account) {
        Object[] rowdata = new Object[5];
        rowdata[0] = account.getCustomer().getName();
        rowdata[1] = account.getAccountNumber();
		rowdata[2] = "N/A";
		rowdata[3] = account.getAccountType();
		rowdata[4] = String.valueOf(account.getBalance());
        return rowdata;
    }

    @Override
    public boolean hasReport() {
        return true;
    }
}
