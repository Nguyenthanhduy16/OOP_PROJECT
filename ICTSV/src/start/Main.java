package start;

import screen.ViewLoginScreen;

public class Main {
	public static void main(String args[]) {
    	javafx.application.Platform.startup(() -> {});
    	java.awt.EventQueue.invokeLater(() -> new ViewLoginScreen().setVisible(true));
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewLoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }
}
