package lifemanagement;

import javax.swing.*;

public class ProfileForm {
    private JPanel mainPanel;
    private JLabel naslov;
    private JTextField usernameField;
    private JLabel ime;
    private JPasswordField passwordField;
    private JLabel password;
    private JComboBox themeCombo;
    private JLabel tema;
    private JButton saveButton;
    private JButton nazadButton;

    private Korisnik korisnik;
    private KorisnikManager korisnikManager;

    public ProfileForm(Korisnik korisnik) {
        this.korisnik = korisnik;
        korisnikManager = new KorisnikManager();

        mainPanel.setBackground(UIStyle.CURRENT_BACKGROUND);

        UIStyle.styleTextField(usernameField);
        UIStyle.stylePasswordField(passwordField);
        UIStyle.styleButton(saveButton);
        UIStyle.styleButton(nazadButton);
        UIStyle.styleComboBox(themeCombo);

        usernameField.setText(korisnik.getUsername());
        themeCombo.setSelectedItem(korisnik.getTheme());

        saveButton.addActionListener(e -> saveChanges());

        nazadButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new MainMenuForm(korisnik).getMainPanel());
            frame.revalidate();
        });
    }

    private void saveChanges() {

        String oldUsername = korisnik.getUsername();
        String oldTheme = korisnik.getTheme();

        String newUsername = usernameField.getText();
        String newPassword = new String(passwordField.getPassword());
        String newTheme = (String) themeCombo.getSelectedItem();

        if (newUsername.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Korisničko ime je obavezno!");
            return;
        }

        boolean changingSensitiveData =
                !newUsername.equals(oldUsername) || !newTheme.equals(oldTheme);

        if (changingSensitiveData && newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Za promjenu korisničkog imena ili teme morate unijeti lozinku!");
            return;
        }

        korisnik.setUsername(newUsername);

        if (!newPassword.isEmpty()) {
            korisnik.setPassword(newPassword);
        }

        korisnik.setTheme(newTheme);
        korisnikManager.updateKorisnik(korisnik);

        UIStyle.applyTheme(newTheme);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        frame.setContentPane(new MainMenuForm(korisnik).getMainPanel());
        frame.revalidate();
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
