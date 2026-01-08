package lifemanagement;

import javax.swing.*;

public class RegisterForm {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> themeCombo;
    private JButton registerButton;
    private JButton nazadButton;
    private JLabel naslov;
    private JLabel ime;
    private JLabel password;

    private KorisnikManager korisnikManager;

    public RegisterForm() {
        korisnikManager = new KorisnikManager();

        mainPanel.setBackground(UIStyle.CURRENT_BACKGROUND);
        UIStyle.styleTextField(usernameField);
        UIStyle.styleTextField(passwordField);
        UIStyle.styleButton(registerButton);
        UIStyle.styleButton(nazadButton);
        UIStyle.styleComboBox(themeCombo);

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
