package backend.banking.commands;

import ui.UIControl;

public class NoCommand implements Command {
    public void execute(UIControl uiControl) { }
    public void undo() { }
}
