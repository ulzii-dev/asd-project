package framework;

import framework.ui.UIControl;

public interface Command {
    void execute(UIControl control);
}
