package lifemanagement;

import javax.swing.*;

public class RegisterForm {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox themeCombo;
    private JButton registerButton;
    private JButton nazadButton;
    private JLabel naslov;

    private KorisnikManager korisnikManager;

    public RegisterForm() {
        korisnikManager = new KorisnikManager();

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String theme = (String) themeCombo.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Sva polja su obavezna!");
                return;
            }

            Korisnik korisnik = new Korisnik(username, password, theme);
            boolean success = korisnikManager.register(korisnik);

            if (!success) {
                JOptionPane.showMessageDialog(null,
                        "Korisničko ime već postoji!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Registracija uspješna!");

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                frame.setContentPane(new LoginForm().getMainPanel());
                frame.revalidate();
            }
        });

        nazadButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new LoginForm().getMainPanel());
            frame.revalidate();
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
