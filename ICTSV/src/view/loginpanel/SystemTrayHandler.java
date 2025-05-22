package view.loginpanel;

import javax.swing.JFrame; 
import javax.swing.JOptionPane; 
import javax.swing.ImageIcon; 
import java.awt.SystemTray; 
import java.awt.TrayIcon; 
import java.awt.PopupMenu; 
import java.awt.MenuItem;

public class SystemTrayHandler { 
	private SystemTray systemTray; 
	private TrayIcon trayIcon; 
	private JFrame frame;
	
	public SystemTrayHandler(JFrame frame) {
	    this.frame = frame;
	}

	public void setupSystemTray() {
	    if (!SystemTray.isSupported()) {
	        JOptionPane.showMessageDialog(frame, "System Tray not supported", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    systemTray = SystemTray.getSystemTray();
	    PopupMenu popup = new PopupMenu();
	    
	    MenuItem restoreItem = new MenuItem("Restore");
	    restoreItem.addActionListener(e -> {
	        frame.setVisible(true); 
	        frame.setState(JFrame.NORMAL);
	        systemTray.remove(trayIcon);
	    });
	    
	    MenuItem exitItem = new MenuItem("Exit");
	    exitItem.addActionListener(e -> System.exit(0));
	    
	    popup.add(restoreItem);
	    popup.add(exitItem);
	}
}