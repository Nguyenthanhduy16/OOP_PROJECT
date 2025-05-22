package view.loginpanel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.LoginService;
import handle.login.LoginHandle;
import model.Admin;
import model.Student;
import model.User;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPanel {
    private JPanel panel;
    private JTextField txtEmail;
    private JPasswordField pwdPassword;
    private JComboBox roleComboBox;
    private Button btnLogin;
    private JLabel lblSignIn;
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JLabel lblRole;

    public LoginPanel() {
        panel = new JPanel();
        panel.setBounds(450, 0, 450, 600);
        panel.setBackground(new Color(253, 248, 251));
        panel.setLayout(null);

        // Sign In Title
        lblSignIn = new JLabel("Sign In");
        lblSignIn.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblSignIn.setForeground(Color.RED);
        lblSignIn.setBounds(175, 122, 150, 45);
        panel.add(lblSignIn);

        // Email Label
        lblEmail = new JLabel("UserName");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblEmail.setForeground(new Color(100, 100, 100));
        lblEmail.setBounds(105, 182, 100, 25);
        panel.add(lblEmail);

        // Email Field with Placeholder
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtEmail.setBounds(105, 212, 240, 35);
        txtEmail.setBackground(new Color(240, 240, 240));
        txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtEmail.setColumns(10);
        txtEmail.setForeground(new Color(150, 150, 150));
        txtEmail.setText("UserName");
        txtEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtEmail.getText().equals("UserName")) {
                    txtEmail.setText("");
                    txtEmail.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtEmail.getText().isEmpty()) {
                    txtEmail.setForeground(new Color(150, 150, 150));
                    txtEmail.setText("UserName");
                }
            }
        });
        panel.add(txtEmail);

        // Password Label
        lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPassword.setForeground(new Color(100, 100, 100));
        lblPassword.setBounds(105, 262, 100, 25);
        panel.add(lblPassword);

        // Password Field with Placeholder
        pwdPassword = new JPasswordField();
        pwdPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pwdPassword.setBounds(105, 292, 240, 35);
        pwdPassword.setBackground(new Color(240, 240, 240));
        pwdPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        pwdPassword.setEchoChar((char) 0);
        pwdPassword.setForeground(new Color(150, 150, 150));
        pwdPassword.setText("Password");
        pwdPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(pwdPassword.getPassword()).equals("Password")) {
                    pwdPassword.setText("");
                    pwdPassword.setEchoChar('*');
                    pwdPassword.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (new String(pwdPassword.getPassword()).isEmpty()) {
                    pwdPassword.setEchoChar((char) 0);
                    pwdPassword.setForeground(new Color(150, 150, 150));
                    pwdPassword.setText("Password");
                }
            }
        });
        panel.add(pwdPassword);

        // Role Label
        lblRole = new JLabel("ROLE");
        lblRole.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblRole.setForeground(new Color(50, 50, 50));
        lblRole.setBounds(105, 342, 100, 25);
        panel.add(lblRole);

        // Role Selection
        roleComboBox = new JComboBox<>(new String[]{"student", "admin"});
        roleComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        roleComboBox.setBounds(105, 372, 240, 35);
        roleComboBox.setBackground(new Color(253, 248, 251));
        roleComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(roleComboBox);

        // Login Button
        btnLogin = new Button("SIGN IN");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.RED);
        btnLogin.setBounds(105, 442, 240, 35);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        panel.add(btnLogin);
    }

    private void handleLogin() {
        String username = txtEmail.getText().trim();
        if (username.equals("UserName")) username = "";
        String password = new String(pwdPassword.getPassword());
        if (new String(pwdPassword.getPassword()).equals("Password")) password = "";
        String selectedRole = (String) roleComboBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Hãy điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LoginHandle loginHandle = new LoginHandle("data.json");
        LoginService loginService = new LoginService(loginHandle);
        User loggedUser = loginService.authenticate(username, password, selectedRole);

        if (loggedUser != null) {
            if (loggedUser instanceof Admin) {
                JOptionPane.showMessageDialog(panel, "Đăng nhập thành công với vai trò Admin!");
               // new AdminFrame((Admin) loggedUser).setVisible(true);
            } else if (loggedUser instanceof Student) {
                JOptionPane.showMessageDialog(panel, "Đăng nhập thành công với vai trò Student!");
               // new StudentFrame((Student) loggedUser).setVisible(true);
            }
         // Đóng cửa sổ hiện tại
            SwingUtilities.getWindowAncestor(panel).dispose();
        } else {
            JOptionPane.showMessageDialog(panel, "Tên đăng nhập, mật khẩu hoặc vai trò không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }


        
/*      if (loggedUser != null) {
            if (loggedUser instanceof Admin && selectedRole.equalsIgnoreCase("admin")) {
                JOptionPane.showMessageDialog(panel, "Đăng nhập thành công với vai trò Admin!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                // Lam gi do de goi phan giao dien admin ra
                
            } else if (loggedUser instanceof Student && selectedRole.equalsIgnoreCase("student")) {
                JOptionPane.showMessageDialog(panel, "Đăng nhập thành công với vai trò Student!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                
                // Lam gi do de goi phan giao dien student

            } else {
                JOptionPane.showMessageDialog(panel, "Vai trò không đúng với tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Tên đăng nhập hoặc mật khẩu không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }*/

    }
    
    // Chỉ là kết quả in ra 
        /*
        if (email.equals("admin") && password.equals("admin123") && role.equals("Admin")) {
            JOptionPane.showMessageDialog(panel, "Login successful as Admin!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (email.equals("user") && password.equals("user123") && role.equals("User")) {
            JOptionPane.showMessageDialog(panel, "Login successful as User!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(panel, "Invalid credentials or role", "Error", JOptionPane.ERROR_MESSAGE);
        }*/

    public JPanel getPanel() {
        return panel;
    }
}