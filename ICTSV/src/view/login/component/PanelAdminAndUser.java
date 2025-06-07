package view.login.component;

import view.login.swing.Button;
import view.login.swing.MyPasswordField;
import view.login.swing.MyTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import view.login.main.Main;
import net.miginfocom.swing.MigLayout;
import screen.student.controller.StudentController;
import controller.LoginService;
import handle.login.LoginHandle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Admin;
import model.Student;
import model.User;

public class PanelAdminAndUser extends javax.swing.JLayeredPane {
	
	private void openStudentView(Student student) {
	    Platform.runLater(() -> {
	        try {
	            FXMLLoader loader = new FXMLLoader(
	                    getClass().getResource("/screen/student/view/StudentLayout.fxml"));
	            Parent root = loader.load();

	            // 1️⃣  lấy admin – ví dụ load từ file
	            Admin admin = handle.model.UserHandle.loadUsers()
	                         .stream()
	                         .filter(u -> u instanceof Admin)
	                         .map(u -> (Admin) u)
	                         .findFirst()
	                         .orElseThrow(() -> new IllegalStateException("Không tìm thấy Admin"));

	            // 2️⃣  inject vào controller
	            StudentController ctrl = loader.getController();
	            ctrl.setAdmin(admin);
	            ctrl.setStudent(student);        // hoặc ctrl.initData(student) sau setAdmin
	            ctrl.initData(student);

	            Stage stage = new Stage();
	            stage.setTitle("Student Management Page");
	            stage.setScene(new Scene(root));
	            stage.setOnShown(ev -> { if (main != null) main.dispose(); });
	            stage.show();

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this,
	                    "Không thể mở Student View: " + ex.getMessage(),
	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	}

	private void openAdminView(Admin admin) {
	    Platform.runLater(() -> {
	        try {
	            FXMLLoader loader = new FXMLLoader(
	                getClass().getResource("/screen/admin/view/Admin.fxml"));
	            Parent root = loader.load();
	            // Nếu muốn truyền dữ liệu cho controller:
	            // AdminController ctrl = loader.getController();
	            // ctrl.setAdmin(admin);
	            Stage stage = new Stage();
	            stage.setTitle("Admin Management Page");
	            stage.setScene(new Scene(root, 1600, 1000));
	            stage.setOnShown(ev -> { if (main != null) main.dispose(); });
	            stage.show();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this,
	                "Không thể mở Admin View: " + ex.getMessage(),
	                "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	}

    private Main main; // Reference to Main for authentication

    private LoginService loginService;

    public void setMain(Main main) {
        this.main = main;
    }
    
    public PanelAdminAndUser() {
    	this.loginService = new LoginService(new LoginHandle("/data/data.json"));
        initComponents();
        setupUserPanel();
        setupAdminPanel();
        adminPanel.setVisible(false);
        userPanel.setVisible(true);
    }

    private void setupUserPanel() {
        userPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Student");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 100, 100));
        userPanel.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/user.png")));
        txtUser.setHint("Username");
        userPanel.add(txtUser, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/pass.png")));
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
                String username = txtUser.getText().trim();
                if (username.equals("Username"))
                    username = "";
                String password = new String(txtPass.getPassword()).trim();
                if (password.equals("Password"))
                    password = "";
                String selectedRole = "student";

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(PanelAdminAndUser.this,
                            "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                User loggedUser = loginService.authenticate(username, password, selectedRole);

                if (loggedUser != null) 
                {
                    if (loggedUser instanceof Student student) 
                    {
                        JOptionPane.showMessageDialog(PanelAdminAndUser.this,
                                "Đăng nhập thành công với vai trò Student!");
                        openStudentView(student);
                        //Đóng cửa sổ hiện tại lại
                        java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(PanelAdminAndUser.this);
                        win.dispose();
                    }
                }
                else 
                {
                	JOptionPane.showMessageDialog(PanelAdminAndUser.this,
                            "Invalid Username or Password for role Student", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /*
         * if (.authenticate(role, email, password)) {
         * JOptionPane.showMessageDialog(PanelAdminAndUser.this,
         * "Login successful as Admin: " + email + "!", "Success",
         * JOptionPane.INFORMATION_MESSAGE);
         * } else {
         * JOptionPane.showMessageDialog(PanelAdminAndUser.this,
         * "Invalid Email or Password for role Admin", "Error",
         * JOptionPane.ERROR_MESSAGE);
         * }
         * }
         * });
         */
        userPanel.add(cmd, "w 40%, h 40");
    }

    private void setupAdminPanel() {
        adminPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Admin");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 100, 100));
        adminPanel.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/mail.png")));
        txtEmail.setHint("Username");
        adminPanel.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/pass.png")));
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
                String username = txtEmail.getText().trim();
                if (username.equals("Username"))
                    username = "";
                String password = new String(txtPass.getPassword()).trim();
                if (password.equals("Password"))
                    password = "";
                String selectedRole = "admin";

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(PanelAdminAndUser.this,
                            "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User loggedUser = loginService.authenticate(username, password, selectedRole);

                if (loggedUser != null) {
                    if (loggedUser instanceof Admin admin) {
                        JOptionPane.showMessageDialog(PanelAdminAndUser.this,
                                "Đăng nhập thành công với vai trò Admin!");
                        openAdminView(admin);
                        java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(PanelAdminAndUser.this);
                        win.dispose();
                    } 
                }else {
                	JOptionPane.showMessageDialog(PanelAdminAndUser.this,
                            "Invalid Username or Password for role Admin", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /*
         * if (.authenticate(role, email, password)) {
         * JOptionPane.showMessageDialog(PanelAdminAndUser.this,
         * "Login successful as Admin: " + email + "!", "Success",
         * JOptionPane.INFORMATION_MESSAGE);
         * } else {
         * JOptionPane.showMessageDialog(PanelAdminAndUser.this,
         * "Invalid Email or Password for role Admin", "Error",
         * JOptionPane.ERROR_MESSAGE);
         * }
         * }
         * });
         */
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
                        .addGap(0, 327, Short.MAX_VALUE));
        adminPanelLayout.setVerticalGroup(
                adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));

        add(adminPanel, "card3");

        userPanel.setBackground(new java.awt.Color(251, 240, 246));

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
                userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE));
        userPanelLayout.setVerticalGroup(
                userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));

        add(userPanel, "card2");
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel adminPanel;
    private javax.swing.JPanel userPanel;
    // End of variables declaration
}