
public class RunClass {
    public static void main(String args[]) throws InterruptedException, NoSuchFieldException {
        while(true) {
            // create login form
            LoginForm login = new LoginForm();
            login.setLocationRelativeTo(null);
            login.setVisible(true);

            // waits for user to login, then dispose form
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
