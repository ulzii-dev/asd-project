package edu.miu.cs525.creditcard;

import edu.miu.cs525.commons.Account;
import edu.miu.cs525.creditcard.constant.CreditCardReportColumnConstant;
import edu.miu.cs525.creditcard.constant.CreditCardType;
import edu.miu.cs525.framework.ui.UIConfig;

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
        return Arrays.asList(CreditCardReportColumnConstant.get());
    }

    @Override
    public int getIdColumnIndex() {
        return 1;
    }

    @Override
    public Object[] buildRow(Account account) {
        Object[] rowData = new Object[5];
        rowData[0] = account.getCustomer().getName();
        rowData[1] = account.getAccountNumber();
        rowData[2] = "N/A";
        rowData[3] = account.getAccountType();
        rowData[4] = String.valueOf(account.getBalance());
        return rowData;
    }

    @Override
    public boolean hasReport() {
        return true;
    }
}
