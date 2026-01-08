package lifemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
