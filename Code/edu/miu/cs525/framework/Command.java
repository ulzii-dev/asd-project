package edu.miu.cs525.framework;

import edu.miu.cs525.framework.ui.UIControl;
import edu.miu.cs525.framework.ui.pages.GenerateReport;

public interface Command {
    void execute(UIControl control);
}
