package edu.miu.cs525.creditcard;

import edu.miu.cs525.banking.constant.BankReportColumnConstant;
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
