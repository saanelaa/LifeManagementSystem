package lifemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MoodTrackerForm {
    private JPanel mainPanel;
    private JLabel naslov;
    private JTextField datumField;
    private JLabel datum;
    private JComboBox<String> moodCombo;
    private JLabel mood;
    private JButton spremiButton;
    private JScrollPane scrollPane;
    private JTable moodTable;
    private JButton nazadButton;
    private JProgressBar moodProgress;

    private Korisnik korisnik;
    private MoodManager manager;
    private DefaultTableModel tableModel;

    public MoodTrackerForm(Korisnik korisnik) {
        this.korisnik = korisnik;
        manager = new MoodManager();

        mainPanel.setBackground(UIStyle.CURRENT_BACKGROUND);

        UIStyle.styleTextField(datumField);
        UIStyle.styleButton(spremiButton);
        UIStyle.styleButton(nazadButton);
        UIStyle.styleComboBox(moodCombo);

        naslov.setText("Mood tracker");

        tableModel = new DefaultTableModel(
                new Object[]{"Datum", "Raspoloženje"}, 0
        );
        moodTable.setModel(tableModel);

        loadData();

        spremiButton.addActionListener(e -> saveMood());

        nazadButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new MyTrackersForm(korisnik).getMainPanel());
            frame.revalidate();
        });
    }

    private void saveMood() {
        String datum = datumField.getText();
        String selectedMood = (String) moodCombo.getSelectedItem();

        if (datum.isEmpty() || selectedMood == null) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Sva polja su obavezna!");
            return;
        }

        int mood = Integer.parseInt(selectedMood);

        manager.addUnos(new MoodUnos(
                korisnik.getUsername(), mood, datum
        ));

        datumField.setText("");
        loadData();
    }

    private void loadData() {
        List<MoodUnos> list =
                manager.getUnosiZaKorisnika(korisnik.getUsername());

        tableModel.setRowCount(0);

        for (MoodUnos m : list) {
            tableModel.addRow(new Object[]{
                    m.getDatum(),
                    m.getMood()
            });
        }
        double prosjek = manager.getProsjekMooda(korisnik.getUsername());

        moodProgress.setValue((int) Math.round(prosjek));
        moodProgress.setString("Prosjek raspoloženja: " + String.format("%.1f", prosjek));
        if (prosjek <= 2) {
            moodProgress.setForeground(new Color(220, 120, 120));
        } else if (prosjek <= 3) {
            moodProgress.setForeground(new Color(230, 200, 120));
        } else {
            moodProgress.setForeground(new Color(120, 200, 150));
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
