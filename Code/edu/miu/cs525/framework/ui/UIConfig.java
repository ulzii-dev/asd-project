package edu.miu.cs525.framework.ui;

import edu.miu.cs525.commons.Account;

import java.util.Collection;

public interface UIConfig {
    Collection<String> getAccountTypes();

    Collection<String> getReportColumnNames();

    int getIdColumnIndex();

    Object[] buildRow(Account account);

    boolean hasReport();
}
