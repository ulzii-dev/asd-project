package backend.banking;

import backend.banking.constant.BankReportColumnConstant;
import backend.banking.constant.BankingAccountType;
import backend.commons.Account;
import framework.domain.PersonalAccount;
import framework.ui.UIConfig;

import java.util.Arrays;
import java.util.Collection;

public class BankingUIConfig implements UIConfig {
    @Override
    public Collection<String> getAccountTypes() {
        return Arrays.asList(BankingAccountType.CHECKING.name(), BankingAccountType.SAVINGS.name());
    }

    @Override
    public Collection<String> getReportColumnNames() {
        return Arrays.asList(BankReportColumnConstant.get());
    }

    @Override
    public int getIdColumnIndex() {
        return 0;
    }

    @Override
    public Object[] buildRow(Account account) {
        Object[] rowData = new Object[6];
        rowData[0] = account.getAccountNumber();
        rowData[1] = account.getCustomer().getName();
        rowData[2] = account.getCustomer().getCity();
        rowData[3] = account.getCustomer() instanceof PersonalAccount ? "Personal Ac" : "Company Ac";
        rowData[4] = account.getAccountType();
        rowData[5] = String.valueOf(account.getBalance());
        return rowData;
    }

    @Override
    public boolean hasReport() {
        return false;
    }
}
