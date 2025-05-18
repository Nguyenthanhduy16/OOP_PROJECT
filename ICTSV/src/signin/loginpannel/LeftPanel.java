package loginpannel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class LeftPanel {
    private JPanel panel;

    public LeftPanel() {
        panel = new JPanel();
        panel.setBounds(0, 0, 450, 600);
        panel.setBackground(new Color(253, 248, 251));
        panel.setLayout(null);

        // 1. Logo (First Icon)
        JLabel topLeftImage = new JLabel();
        topLeftImage.setBounds(10, 20, 80, 100);
        topLeftImage.setIcon(new ImageIcon(
            new ImageIcon(LeftPanel.class.getResource("/images/Hust.png")).getImage().getScaledInstance(80, 100, java.awt.Image.SCALE_SMOOTH)
        ));
        panel.add(topLeftImage);

        // 2. Icon lớn ở giữa panel (Second Icon)
        int iconWidth = 450;
        int iconHeight = 300;
        JLabel centerIcon = new JLabel();
        centerIcon.setBounds(0, 100, iconWidth, iconHeight);
        centerIcon.setIcon(new ImageIcon(
            new ImageIcon(LeftPanel.class.getResource("/images/app_icon.png")).getImage().getScaledInstance(iconWidth, iconHeight, java.awt.Image.SCALE_SMOOTH)
        ));
        panel.add(centerIcon);

        // 3. Tên phần mềm (căn giữa dưới icon)
        JLabel appNameLabel = new JLabel("Quản lý điểm rèn luyện", SwingConstants.CENTER);
        appNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        appNameLabel.setForeground(new Color(40, 40, 40));
        appNameLabel.setBounds(0, 420, 450, 40);
        panel.add(appNameLabel);
    }

    public JPanel getPanel() {
        return panel;
    }
}