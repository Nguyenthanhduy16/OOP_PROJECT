package view.login.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import net.miginfocom.swing.MigLayout;
import view.login.component.PanelAdminAndUser;
import view.login.component.PanelCover;

public class Main extends javax.swing.JFrame {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private PanelAdminAndUser adminAndUser; 
    private boolean isUser = true; 
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private Point initialClick;
    private final Map<String, String> userAccounts = new HashMap<>();
    private final Map<String, String> adminAccounts = new HashMap<>();
    private JButton closeButton; // Close button

    public Main() {
        initComponents();
       // initAccounts();
        init();
        initDragAndDrop();
    }
    

    /*
    private void initAccounts() {
        // User accounts
        userAccounts.put("tyn", "5804");
        // Admin accounts
        adminAccounts.put("admin", "admin123");
    }*/

    /*public boolean authenticate(String role, String identifier, String password) {
        if (role.equals("User")) {
            return userAccounts.containsKey(identifier) && userAccounts.get(identifier).equals(password);
        } else if (role.equals("Admin")) {
            return adminAccounts.containsKey(identifier) && adminAccounts.get(identifier).equals(password);
        }
        return false;
    }*/
    
   

    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        adminAndUser = new PanelAdminAndUser();
        adminAndUser.setMain(this);

        // Initialize close button
        closeButton = new JButton("X");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        closeButton.setForeground(Color.BLACK); // Set text color to black (or adjust as needed)
        closeButton.setBorder(null);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false); // Remove default button background
        closeButton.setOpaque(false); // Make background transparent
        closeButton.setBounds(0, 0, 25, 25); // Small button size
        closeButton.addActionListener(e -> System.exit(0)); // Close the application

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize; 
                if (fraction <= 0.5f) {
                    size += fraction * addSize; 
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isUser) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    adminAndUser.showUserPanel(isUser);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(adminAndUser, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isUser = !isUser; 
            }
        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        bg.setLayout(layout);
        bg.add(cover, "width " + coverSize + "%, pos " + (isUser ? "1al" : "0al") + " 0 n 100%");
        bg.add(adminAndUser, "width " + loginSize + "%, pos " + (isUser ? "0al" : "1al") + " 0 n 100%");
        bg.add(closeButton, "pos 1.0al 0, w 25, h 25"); // Position at top-right
        bg.setLayer(closeButton, javax.swing.JLayeredPane.POPUP_LAYER); // Ensure button is on top
        adminAndUser.showUserPanel(!isUser); 
        cover.login(isUser);
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
    }

    private void initDragAndDrop() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Only allow dragging if not clicking the close button
                if (!closeButton.getBounds().contains(e.getPoint())) {
                    initialClick = e.getPoint();
                    getComponentAt(initialClick);
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (initialClick != null) {
                    int thisX = getLocation().x;
                    int thisY = getLocation().y;
                    int xMoved = e.getX() - initialClick.x;
                    int yMoved = e.getY() - initialClick.y;
                    int newX = thisX + xMoved;
                    int newY = thisY + yMoved;
                    setLocation(newX, newY);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(251, 240, 246));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
    	javafx.application.Platform.startup(() -> {});
    	java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
    }

    // Variables declaration
    private javax.swing.JLayeredPane bg;
}