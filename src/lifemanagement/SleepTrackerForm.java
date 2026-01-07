package lifemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SleepTrackerForm {
    private JPanel mainPanel;
    private JTextField hoursField;
    private JButton spremiButton;
    private JTable sleepTable;
    private JScrollPane scrollPane;
    private JButton nazadButton;
    private JLabel naslov;
    private JLabel averageLabel;

    private Korisnik korisnik;
    private SleepManager manager;
    private DefaultTableModel tableModel;

    public SleepTrackerForm(Korisnik korisnik) {
        this.korisnik = korisnik;
        manager = new SleepManager();

        naslov.setText("Sleep Tracker");

        tableModel = new DefaultTableModel(
                new Object[]{"Sati spavanja:"}, 0
        );
        sleepTable.setModel(tableModel);

        loadData();

        spremiButton.addActionListener(e -> saveSleep());

        nazadButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new MyTrackersForm(korisnik).getMainPanel());
            frame.revalidate();
        });
    }

    private void saveSleep() {
        double sati;
        try {
            sati = Double.parseDouble(hoursField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Unesite broj sati spavanja!");
            return;
        }

        manager.addUnos(new SleepUnos(
                korisnik.getUsername(), sati
        ));

        hoursField.setText("");
        loadData();
    }

    private void loadData() {
        List<SleepUnos> list =
                manager.getUnosiZaKorisnika(korisnik.getUsername());

        tableModel.setRowCount(0);
        for (SleepUnos u : list) {
            tableModel.addRow(new Object[]{u.getSati()});
        }

        double prosjek = manager.getProsjekSpavanja(korisnik.getUsername());
        averageLabel.setText("Prosječno spavanje: " + prosjek + " h");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
