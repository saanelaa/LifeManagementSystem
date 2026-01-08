package financeapp;

import lifemanagement.Korisnik;
import lifemanagement.MainMenuForm;
import lifemanagement.UIStyle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class FinanceTrackerForm {
    private JPanel mainPanel;
    private JTextField amountField;
    private JTextField descriptionField;
    private JComboBox<String> typeCombo;
    private JButton addButton;
    private JTable transactionTable;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JLabel balanceLabel;
    private JLabel iznos;
    private JLabel opis;
    private JScrollPane scrollPane;
    private JButton nazadButton;

    private TransactionManager manager;
    private DefaultTableModel tableModel;
    private Korisnik korisnik;

    public FinanceTrackerForm(Korisnik korisnik) {
        this.korisnik = korisnik;

        manager = new TransactionManager();

        mainPanel.setBackground(UIStyle.CURRENT_BACKGROUND);

        UIStyle.styleTextField(amountField);
        UIStyle.styleTextField(descriptionField);
        UIStyle.styleButton(nazadButton);
        UIStyle.styleButton(addButton);

        tableModel = new DefaultTableModel(
                new Object[]{"Vrsta", "Iznos", "Opis"}, 0
        );
        transactionTable.setModel(tableModel);

        Color BG_MAIN      = new Color(248, 246, 241); // glavna pozadina
        Color BG_FIELD     = new Color(242, 239, 231); // inputi i combo
        Color GREEN_SOFT   = new Color(214, 232, 214); // prihod
        Color RED_SOFT     = new Color(245, 214, 214); // rashod
        Color GREEN_BTN    = new Color(162, 193, 132); // dugme
        Color BORDER_SOFT  = new Color(205, 200, 190); // okvir
        Color TEXT_DARK    = new Color(70, 70, 70);    // tekst
        Color HEADER_BG    = new Color(232, 228, 218); // header tabele

        amountField.setForeground(TEXT_DARK);
        descriptionField.setForeground(TEXT_DARK);

        amountField.setBorder(BorderFactory.createLineBorder(BORDER_SOFT));
        descriptionField.setBorder(BorderFactory.createLineBorder(BORDER_SOFT));

        typeCombo.setBackground(BG_FIELD);
        typeCombo.setForeground(TEXT_DARK);
        typeCombo.setOpaque(true);
        typeCombo.setBorder(BorderFactory.createLineBorder(BORDER_SOFT));

        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setOpaque(true);
        addButton.setFont(addButton.getFont().deriveFont(Font.BOLD));

        transactionTable.setBackground(BG_MAIN);
        transactionTable.setForeground(TEXT_DARK);
        transactionTable.setRowHeight(24);
        transactionTable.setGridColor(BORDER_SOFT);

        transactionTable.setDefaultRenderer(Object.class,
                new javax.swing.table.DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {

                        Component c = super.getTableCellRendererComponent(
                                table, value, isSelected, hasFocus, row, column);

                        String type = table.getValueAt(row, 0).toString().toLowerCase();

                        if (type.startsWith("prihod")) {
                            c.setBackground(GREEN_SOFT);
                        } else if (type.startsWith("rashod")) {
                            c.setBackground(RED_SOFT);
                        } else {
                            c.setBackground(BG_FIELD);
                        }

                        if (isSelected) {
                            c.setBackground(new Color(200, 210, 200));
                        }

                        c.setForeground(TEXT_DARK);
                        return c;
                    }
                });
        JTableHeader header = transactionTable.getTableHeader();
        header.setBackground(HEADER_BG);
        header.setForeground(TEXT_DARK);
        header.setFont(header.getFont().deriveFont(Font.BOLD));

        loadDataIntoTable();
        updateSummary();

        addButton.addActionListener(e -> addTransaction());

        scrollPane.setViewportView(transactionTable);

        loadDataIntoTable();
        updateSummary();

        addButton.addActionListener(e -> addTransaction());

        nazadButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new MainMenuForm(korisnik).getMainPanel());
            frame.revalidate();
        });
    }

    private void addTransaction() {

        String rawText = amountField.getText();

        double amount;
        try {
            amount = Double.parseDouble(rawText.trim().replace(",", "."));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Iznos mora biti broj");
            return;
        }

        String type = (String) typeCombo.getSelectedItem();
        if (type == null || type.equals("Odaberite")) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Odaberite tip transakcije");
            return;
        }

        String description = descriptionField.getText();
        if (description == null || description.trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Opis ne može biti prazan");
            return;
        }

        manager.addTransaction(new Transaction(type, amount, description));

        loadDataIntoTable();
        updateSummary();

        amountField.setText("");
        descriptionField.setText("");
    }

    private void loadDataIntoTable() {

        List<Transaction> list = manager.getAllTransactions();

        tableModel.setRowCount(0);

        for (Transaction t : list) {
            tableModel.addRow(new Object[]{
                    t.getType(),
                    t.getAmount(),
                    t.getDescription()
            });
        }
    }

    private void updateSummary() {
        double income = manager.getTotalIncome();
        double expense = manager.getTotalExpense();
        double balance = income - expense;

        incomeLabel.setText("Prihod: " + income);
        expenseLabel.setText("Rashod: " + expense);
        balanceLabel.setText("Saldo: " + balance);
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

}
