package edu.miu.cs525.shared.utils;

import edu.miu.cs525.framework.Command;
import edu.miu.cs525.framework.ui.UIControl;

public class NoCommand implements Command {
    @Override
    public void execute(UIControl uiControl) {}
}