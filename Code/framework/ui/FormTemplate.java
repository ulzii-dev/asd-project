package framework.ui;

import backend.banking.commands.NoCommand;
import framework.Command;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;

public abstract class FormTemplate extends JFrame {
    protected boolean newAccount;
    protected DefaultTableModel model;
    protected JTable JTable1;
    protected JPanel JPanel1;
    protected JScrollPane JScrollPane1;
    protected Object[] rowData;

    public final void generateFormTemplate(String title, UIConfig uiConfig,
                                           Map<String, ActionListener> buttons) {
        setupJPanel(title, uiConfig.getReportColumnNames());
        pSetSize();
        panelBounds();
        scrollPanelBounds();
        tableBounds();
        setUpButtons(buttons);
        notCommon();
    }

    private void setupJPanel(String title, Collection<String> columns) {
        JPanel1 = new JPanel();
        setTitle(title);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setVisible(false);
        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);

        JScrollPane1 = new JScrollPane();
        model = new DefaultTableModel();
        JTable1 = new JTable(model);

        for (String cols : columns) {
            model.addColumn(cols);
        }

        rowData = new Object[columns.size()];
        newAccount = false;
        JPanel1.add(JScrollPane1);
        JScrollPane1.getViewport().add(JTable1);

    }

    private void setUpButtons(Map<String, ActionListener> buttons) {
        int y = getInitialY();
        for (Map.Entry<String, ActionListener> button : buttons.entrySet()) {
            System.out.println("-->" + button.getKey());
            JButton btn = new JButton();
            btn.setText(button.getKey());
            JPanel1.add(btn);
            btn.addActionListener(button.getValue());
            setBtnBounds(btn, y);
            y += yIncrementBy();
        }

    }

    private void initCommands(Collection<Command> commands) {
        for (Command command :
                commands) {
            command = new NoCommand();
        }
    }

    protected abstract void notCommon();

    protected abstract void setBtnBounds(JButton btn, int y);

    protected abstract void panelBounds();

    protected abstract void pSetSize();

    protected abstract void tableBounds();

    protected abstract void scrollPanelBounds();

    protected int getInitialY() {
        return 24;
    }

    protected int yIncrementBy() {
        return 40;
    }
}
