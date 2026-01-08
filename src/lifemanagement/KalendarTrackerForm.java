package lifemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class KalendarTrackerForm {
    private JPanel mainPanel;
    private JLabel naslov;
    private JScrollPane scrollPane;
    private JTable kalendarTable;
    private JTextField datumField;
    private JTextField nazivField;
    private JComboBox tipCombo;
    private JButton spremiButton;
    private JButton nazadButton;
    private JLabel datum;

    private Korisnik korisnik;
    private KalendarManager manager;
    private DefaultTableModel tableModel;

    public KalendarTrackerForm(Korisnik korisnik) {
        this.korisnik = korisnik;
        manager = new KalendarManager();

        mainPanel.setBackground(UIStyle.CURRENT_BACKGROUND);

        UIStyle.styleTextField(datumField);
        UIStyle.styleTextField(nazivField);
        UIStyle.styleButton(spremiButton);
        UIStyle.styleButton(nazadButton);
        UIStyle.styleComboBox(tipCombo);

        naslov.setText("Kalendar");

        tableModel = new DefaultTableModel(
                new Object[]{"Datum", "Naziv", "Tip"}, 0
        );
        kalendarTable.setModel(tableModel);

        loadData();

        spremiButton.addActionListener(e -> saveEvent());

        nazadButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new MyTrackersForm(korisnik).getMainPanel());
            frame.revalidate();
        });
    }

    private void saveEvent() {

        String datum = datumField.getText();
        String naziv = nazivField.getText();
        String tip = (String) tipCombo.getSelectedItem();

        if (datum.isEmpty() || naziv.isEmpty() || tip == null) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Sva polja su obavezna!");
            return;
        }

        manager.addUnos(new KalendarUnos(
                korisnik.getUsername(), datum, naziv, tip
        ));

        datumField.setText("");
        nazivField.setText("");
        loadData();
    }

    private void loadData() {

        List<KalendarUnos> list =
                manager.getUnosiZaKorisnika(korisnik.getUsername());

        tableModel.setRowCount(0);

        for (KalendarUnos c : list) {
            tableModel.addRow(new Object[]{
                    c.getDatum(),
                    c.getNaziv(),
                    c.getTip()
            });
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
