/**
 * @author Muntakim Ali
 * @organization McMaster University 3K04
 */

import com.fazecast.jSerialComm.SerialPort;

public class RunClass {
    public static void main(String args[]) throws InterruptedException, NoSuchFieldException {

//        SerialPort serials[] = SerialPort.getCommPorts(); 
//        for (SerialPort serial : serials)
//            System.out.println(serial.getSystemPortName()); 
        
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
            program.setLocationRelativeTo(login);
            program.setVisible(true);
            
            // wait for user to logout in program, then dispose form
            synchronized(program) { program.wait(); }
            program.dispose();
        }
    }
}
