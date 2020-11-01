/**
 * @author Muntakim Ali
 * @organization McMaster University 3K04
 */

import com.fazecast.jSerialComm.SerialPort;
import java.security.NoSuchAlgorithmException;

public class RunClass {
    public static void main(String args[]) 
            throws InterruptedException, NoSuchFieldException, NoSuchAlgorithmException {
 
        for (SerialPort serial : SerialPort.getCommPorts())
            System.out.println(serial.getSystemPortName());
        System.out.println();

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
            DCM program = new DCM(login.getCurrentUser());
            program.setLocationRelativeTo(null);
            program.setVisible(true);
            
            // wait for user to logout in program, then dispose form
            synchronized(program) { program.wait(); }
            program.dispose();
        }
        
    }
}
