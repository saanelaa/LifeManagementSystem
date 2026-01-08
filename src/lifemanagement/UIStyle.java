package lifemanagement;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UIStyle {
    public static final Color ROZA = new Color(243, 161, 212);
    public static final Color PLAVA = new Color(119, 154, 197);
    public static final Color ZELENA = new Color(119, 165, 136);
    public static final Color DARK_MODE = new Color(81, 80, 80);
    public static final Color LIGHT_MODE= new Color(255, 237, 209);
    public static final Color SAGE = new Color(203, 184, 141);
    public static final Color SPRUCE = new Color(0x35, 0x5E, 0x58);
    public static final Color WHITE = Color.WHITE;
    public static final Color TEXT_DARK = new Color(50, 50, 50);

    public static Border roundedBorder(Color color) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
    }

    public static void styleButton(JButton button) {
        button.setBackground(SPRUCE);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(roundedBorder(SPRUCE));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void styleTextField(JTextField field) {
        field.setBackground(WHITE);
        field.setForeground(TEXT_DARK);
        field.setBorder(roundedBorder(Color.LIGHT_GRAY));
    }

    public static void stylePasswordField(JPasswordField field) {
        field.setBackground(WHITE);
        field.setForeground(TEXT_DARK);
        field.setBorder(roundedBorder(Color.LIGHT_GRAY));
    }

    public static void styleComboBox(JComboBox comboBox){
        comboBox.setBackground(WHITE);
        comboBox.setForeground(TEXT_DARK);
    }

    public static Color CURRENT_BACKGROUND = SAGE;

    public static void applyTheme(String theme) {

        if (theme == null) return;

        switch (theme) {
            case "Roza":
                CURRENT_BACKGROUND = ROZA;
                break;
            case "Plava":
                CURRENT_BACKGROUND = PLAVA;
                break;
            case "Zelena":
                CURRENT_BACKGROUND = ZELENA;
                break;
            case "Light mode":
                CURRENT_BACKGROUND = LIGHT_MODE;
                break;
            case "Dark mode":
                CURRENT_BACKGROUND = DARK_MODE;
                break;
            default:
                CURRENT_BACKGROUND = SAGE;
                break;
        }
    }
}
