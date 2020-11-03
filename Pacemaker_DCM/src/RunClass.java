/**
 * @author Muntakim Ali
 * @organization McMaster University 3K04
 */

import com.fazecast.jSerialComm.SerialPort;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;

public class RunClass {
    
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
    
    public static void main(String args[]) 
            throws InterruptedException, NoSuchFieldException, NoSuchAlgorithmException {
 
        lookAndFeel("Windows");

//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                DCM_Form form = new DCM_Form();
//                form.setLocationRelativeTo(null);
//                form.setVisible(true);
//            }
//        });

        // testing hash algorithm
//        String[] inputs = new String[] {
//            "fat tuna",
//            "chinchilla",
//            "test",
//            "test1",
//            "test2",
//            "test ",
//            "penguin",
//            "PENGUIN",
//            "12345",
//            "~!@#$%^&*()",
//            "ANYTHING BUT THE ROACHES",
//        };
//        for(String input : inputs) {
//            System.out.println("'" + input + "'");
//            System.out.println(HashAlg.hash(input));
//            System.out.println();
//        }

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
}
