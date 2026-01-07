package lifemanagement;

import javax.swing.*;

public class LoginForm {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel naslov;

    private KorisnikManager korisnikManager;

    public LoginForm() {
        korisnikManager = new KorisnikManager();

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Sva polja su obavezna!");
                return;
            }

            Korisnik korisnik = korisnikManager.login(username, password);

            if (korisnik == null) {
                JOptionPane.showMessageDialog(null,
                        "Pogrešno korisničko ime ili lozinka!");
            } else {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                frame.setContentPane(new MainMenuForm(korisnik).getMainPanel());
                frame.revalidate();
            }
        });

        registerButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new RegisterForm().getMainPanel());
            frame.revalidate();
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
