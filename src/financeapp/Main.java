package financeapp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Praćenje ličnih finansija");
            FinanceTrackerForm form = new FinanceTrackerForm();

            frame.setContentPane(new FinanceTrackerForm().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setSize(700, 550);
            frame.setVisible(true);

        });
    }
}
