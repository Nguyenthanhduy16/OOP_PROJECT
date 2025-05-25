package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.loginpanel.LeftPanel;
import view.loginpanel.LoginPanel;
import view.loginpanel.WindowControls;

public class ICTSVPanel extends JFrame {
	private JPanel contentPane;
	private WindowControls windowControls;
	private LoginPanel loginPanel;
	private LeftPanel leftPanel;

	public ICTSVPanel() {
	    setBackground(Color.WHITE);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(900, 600); // Increased default size to 900x600
	    setLocationRelativeTo(null);
	    setUndecorated(true);

	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(253, 248, 251));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 0));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    // Add window controls
	    windowControls = new WindowControls(this);
	    contentPane.add(windowControls.getPanel());

	    // Add left panel
	    leftPanel = new LeftPanel();
	    contentPane.add(leftPanel.getPanel());

	    // Add login panel
	    loginPanel = new LoginPanel();
	    contentPane.add(loginPanel.getPanel());

	    // Adjust window controls on resize
	    this.addComponentListener(new ComponentAdapter() {
	        public void componentResized(ComponentEvent evt) {
	            windowControls.getPanel().setBounds(getWidth() - 80, 0, 80, 32);
	            leftPanel.getPanel().setBounds(0, 0, 450, 600); // Chia đôi chiều rộng
	            loginPanel.getPanel().setBounds(450, 0, 450, 600); // Chia đôi chiều rộng
	        }
	    });
	}

	public static void main(String[] args) {
		
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                ICTSVPanel frame = new ICTSVPanel();
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	    
	    
	}
}
	  