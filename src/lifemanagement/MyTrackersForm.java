package lifemanagement;

import javax.swing.*;

public class MyTrackersForm {
    private JPanel mainPanel;
    private JButton sleepButton;
    private JButton calendarButton;
    private JButton moodButton;
    private JButton studyButton;
    private JButton nazadButton;
    private JLabel naslov;

    private Korisnik korisnik;

    public MyTrackersForm(Korisnik korisnik) {
        this.korisnik = korisnik;

        mainPanel.setBackground(UIStyle.CURRENT_BACKGROUND);

        UIStyle.styleButton(sleepButton);
        UIStyle.styleButton(calendarButton);
        UIStyle.styleButton(moodButton);
        UIStyle.styleButton(studyButton);
        UIStyle.styleButton(nazadButton);

        naslov.setText("My Trackers");

        sleepButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new SleepTrackerForm(korisnik).getMainPanel());
            frame.revalidate();
        });

        studyButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new StudyTrackerForm(korisnik).getMainPanel());
            frame.revalidate();
        });

        moodButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new MoodTrackerForm(korisnik).getMainPanel());
            frame.revalidate();
        });

        calendarButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new KalendarTrackerForm(korisnik).getMainPanel());
            frame.revalidate();
        });

        nazadButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.setContentPane(new MainMenuForm(korisnik).getMainPanel());
            frame.revalidate();
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
