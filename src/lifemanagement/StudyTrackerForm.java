package lifemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StudyTrackerForm {
    private JPanel mainPanel;
    private JTextField hoursField;
    private JScrollPane scrollPane;
    private JTable studyTable;
    private JButton spremiButton;
    private JLabel naslov;
    private JLabel averageLabel;
    private JButton nazadButton;
    private JLabel study;
    private JLabel datum;
    private JTextField datumField;

    private Korisnik korisnik;
    private StudyManager manager;
    private DefaultTableModel tableModel;

    public StudyTrackerForm(Korisnik korisnik) {
        this.korisnik = korisnik;
        manager = new StudyManager();

        mainPanel.setBackground(UIStyle.CURRENT_BACKGROUND);

        UIStyle.styleTextField(datumField);
        UIStyle.styleTextField(hoursField);
        UIStyle.styleButton(spremiButton);
        UIStyle.styleButton(nazadButton);

        naslov.setText("Study Tracker");

        tableModel = new DefaultTableModel(
                new Object[]{"Datum", "Sati učenja"}, 0
        );
        studyTable.setModel(tableModel);

        loadData();

        spremiButton.addActionListener(e -> saveStudy());

        nazadButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new MyTrackersForm(korisnik).getMainPanel());
            frame.revalidate();
        });
    }

    private void saveStudy() {
        String datum = datumField.getText();
        double sati;
        try {
            sati = Double.parseDouble(hoursField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Unesite broj sati učenja!");
            return;
        }
        if (datum.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Unesite datum!");
            return;
        }

        manager.addUnos(new StudyUnos(
                korisnik.getUsername(), sati, datum
        ));

        datumField.setText("");
        hoursField.setText("");
        loadData();
    }

    private void loadData() {
        List<StudyUnos> list =
                manager.getUnosiZaKorisnika(korisnik.getUsername());

        tableModel.setRowCount(0);
        for (StudyUnos u : list) {
            tableModel.addRow(new Object[]{u.getDatum(),u.getSati()});
        }

        double prosjek = manager.getProsjekUcenja(korisnik.getUsername());
        averageLabel.setText("Prosječno učenje: " + prosjek + " h");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
