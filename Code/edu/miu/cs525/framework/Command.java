package edu.miu.cs525.framework;

import edu.miu.cs525.framework.ui.UIControl;

public interface Command {
    void execute(UIControl control);
}
