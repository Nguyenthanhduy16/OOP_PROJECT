package component;

import swing.Button;
import swing.MyPasswordField;
import swing.MyTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import main.Main;
import net.miginfocom.swing.MigLayout;

public class PanelAdminAndUser extends javax.swing.JLayeredPane {

    private Main main; // Reference to Main for authentication

    public void setMain(Main main) {
        this.main = main;
    }

    public PanelAdminAndUser() {
        initComponents();
        setupUserPanel();
        setupAdminPanel();
        adminPanel.setVisible(false);
        userPanel.setVisible(true);
    }

    private void setupUserPanel() {
        userPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("User");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 100, 100));
        userPanel.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUser.setHint("StudentID");
        userPanel.add(txtUser, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        userPanel.add(txtPass, "w 60%");
        userPanel.add(new JLabel(), "h 10"); // Empty component for spacing
        Button cmd = new Button();
        cmd.setBackground(new Color(255, 110, 110));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = txtUser.getText().trim();
                if (studentID.equals("StudentID")) studentID = "";
                String password = new String(txtPass.getPassword()).trim();
                if (password.equals("Password")) password = "";
                String role = "User";

                if (studentID.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(PanelAdminAndUser.this, 
                        "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (main.authenticate(role, studentID, password)) {
                    JOptionPane.showMessageDialog(PanelAdminAndUser.this, 
                        "Login successful as User: " + studentID + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(PanelAdminAndUser.this, 
                        "Invalid StudentID or Password for role User", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        userPanel.add(cmd, "w 40%, h 40");
    }

    private void setupAdminPanel() {
        adminPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Admin");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 100, 100));
        adminPanel.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        adminPanel.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        adminPanel.add(txtPass, "w 60%");
        adminPanel.add(new JLabel(), "h 10"); // Empty component for spacing
        Button cmd = new Button();
        cmd.setBackground(new Color(255, 110, 110));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                if (email.equals("Email")) email = "";
                String password = new String(txtPass.getPassword()).trim();
                if (password.equals("Password")) password = "";
                String role = "Admin";

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(PanelAdminAndUser.this, 
                        "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (main.authenticate(role, email, password)) {
                    JOptionPane.showMessageDialog(PanelAdminAndUser.this, 
                        "Login successful as Admin: " + email + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(PanelAdminAndUser.this, 
                        "Invalid Email or Password for role Admin", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        adminPanel.add(cmd, "w 40%, h 40");
    }

    public void showUserPanel(boolean show) {
        if (show) {
            userPanel.setVisible(true);
            adminPanel.setVisible(false);
        } else {
            userPanel.setVisible(false);
            adminPanel.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        adminPanel = new javax.swing.JPanel();
        userPanel = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        adminPanel.setBackground(new java.awt.Color(251, 240, 246));

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(adminPanel, "card3");

        userPanel.setBackground(new java.awt.Color(251, 240, 246));

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(userPanel, "card2");
    }
    // Variables declaration - do not modify
    private javax.swing.JPanel adminPanel;
    private javax.swing.JPanel userPanel;
    // End of variables declaration
}