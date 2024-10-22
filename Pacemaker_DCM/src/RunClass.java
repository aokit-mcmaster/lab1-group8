/**
 * @author Muntakim Ali
 * @organization McMaster University 3K04
 */

import java.security.NoSuchAlgorithmException;

public class RunClass {
    
    public static void main(String args[]) 
            throws InterruptedException, NoSuchFieldException, NoSuchAlgorithmException {
 
        lookAndFeel("Nimbus");
        
        while(true) {
            // create login form
            LoginForm login = new LoginForm();
            login.setLocationRelativeTo(null);
            login.setVisible(true);

            // waits for user to login
            synchronized(login) { login.wait(); }
            login.dispose();

            // start DCM program
            DCM_Form program = new DCM_Form(login.getCurrentUser());
            program.setLocationRelativeTo(null);
            program.setVisible(true);
            
            // wait for user to logout in program, then dispose form
            synchronized(program) { program.wait(); }
            program.dispose();
        }

    }
    
    public static void lookAndFeel(String style) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (style.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DCM_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DCM_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DCM_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DCM_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
}
