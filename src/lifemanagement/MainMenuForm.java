package lifemanagement;

import financeapp.FinanceTrackerForm;
import javax.swing.*;

public class MainMenuForm {
    private JPanel mainPanel;
    private JButton profileButton;
    private JButton financeButton;
    private JButton trackersButton;
    private JButton logoutButton;
    private JLabel poruka;

    private Korisnik korisnik;

    public MainMenuForm(Korisnik korisnik) {
        this.korisnik = korisnik;

        UIStyle.applyTheme(korisnik.getTheme());

        mainPanel.setBackground(UIStyle.CURRENT_BACKGROUND);

        UIStyle.styleButton(profileButton);
        UIStyle.styleButton(financeButton);
        UIStyle.styleButton(trackersButton);
        UIStyle.styleButton(logoutButton);

        poruka.setText("Dobrodošli " + korisnik.getUsername() + ", uživajte u korištenju sistema!");

        financeButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new FinanceTrackerForm(korisnik).getMainPanel());
            frame.revalidate();
        });

        trackersButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new MyTrackersForm(korisnik).getMainPanel());
            frame.revalidate();
        });

        logoutButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new LoginForm().getMainPanel());
            frame.revalidate();
        });

        profileButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new ProfileForm(korisnik).getMainPanel());
            frame.revalidate();
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
