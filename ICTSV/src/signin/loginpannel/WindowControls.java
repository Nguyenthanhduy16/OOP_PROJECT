package signin.loginpannel;

import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JLabel; 
import javax.swing.SwingConstants; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseMotionAdapter; 
import java.awt.GraphicsEnvironment; 
import java.awt.Rectangle;

public class WindowControls { 
	private JPanel windowButtons; 
	private JFrame frame; 
	private boolean isMaximized = false; 
	private int normalWidth = 900; // Updated to match new default window width 
	private int normalHeight = 600; // Updated to match new default window height 
	private int normalX; 
	private int normalY; 
	private int xx, xy;

	public WindowControls(JFrame frame) {
	    this.frame = frame;

	    windowButtons = new JPanel();
	    windowButtons.setLayout(null);
	    windowButtons.setBackground(new Color(253, 248, 251));
	    windowButtons.setBounds(frame.getWidth() - 80, 0, 80, 32); // Start at right edge minus panel width
	    windowButtons.setOpaque(true);

	    // Minimize
	    JLabel lblMinimize = new JLabel("\u2013", SwingConstants.CENTER);
	    lblMinimize.setForeground(new Color(200, 200, 200));
	    lblMinimize.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    lblMinimize.setBounds(0, 0, 40, 32);
	    lblMinimize.setOpaque(false);
	    lblMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	    windowButtons.add(lblMinimize);
	    lblMinimize.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	            frame.setState(JFrame.ICONIFIED);
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            lblMinimize.setBackground(new Color(80, 80, 80));
	            lblMinimize.setOpaque(true);
	            lblMinimize.setForeground(new Color(253, 248, 251));
	            windowButtons.repaint();
	        }
	        @Override
	        public void mouseExited(MouseEvent e) {
	            lblMinimize.setOpaque(false);
	            lblMinimize.setForeground(new Color(200, 200, 200));
	            windowButtons.repaint();
	        }
	    });

	    // Close
	    JLabel lblClose = new JLabel("\u00D7", SwingConstants.CENTER);
	    lblClose.setForeground(new Color(200, 200, 200));
	    lblClose.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    lblClose.setBounds(40, 0, 40, 32);
	    lblClose.setOpaque(false);
	    lblClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	    windowButtons.add(lblClose);
	    lblClose.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	            System.exit(0);
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            lblClose.setBackground(new Color(232, 17, 35));
	            lblClose.setOpaque(true);
	            lblClose.setForeground(new Color(253, 248, 251));
	            windowButtons.repaint();
	        }
	        @Override
	        public void mouseExited(MouseEvent e) {
	            lblClose.setOpaque(false);
	            lblClose.setForeground(new Color(200, 200, 200));
	            windowButtons.repaint();
	        }
	    });

	    // Dragging functionality
	    windowButtons.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            xx = e.getX();
	            xy = e.getY();
	        }
	    });
	    windowButtons.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {
	            int x = e.getXOnScreen();
	            int y = e.getYOnScreen();
	            frame.setLocation(x - xx - windowButtons.getX(), y - xy - windowButtons.getY());
	        }
	    });
	}

	public JPanel getPanel() {
	    return windowButtons;
	}
}

