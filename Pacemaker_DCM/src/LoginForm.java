/**
 * @author Muntakim Ali
 * @organization McMaster University 3K04
 */

import javax.swing.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class LoginForm extends javax.swing.JFrame {

    private volatile static boolean LOGIN_SUCCESS = false;
    private final int MAX_USER_COUNT = 10;
    private int USER_COUNT = 0;
    private final String[] USERNAMES = new String[MAX_USER_COUNT];
    private final String[] PASSWORDS = new String[MAX_USER_COUNT];
    private String CURRENT_USER;
    
    public LoginForm() {
        initUserData();
        initComponents();
    }
    
    /* initialize all the components and widgets
     * sets positioning for each component */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        buttonLogin = new javax.swing.JButton();
        buttonRegister = new javax.swing.JButton();
        buttonRemoveUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome Screen & Login");
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        labelTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Login");

        labelUsername.setText("Username:");

        labelPassword.setText("Password:");

        buttonLogin.setText("Login");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });
        getRootPane().setDefaultButton(buttonLogin);

        buttonRegister.setText("Register");
        buttonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegisterActionPerformed(evt);
            }
        });

        buttonRemoveUser.setText("Remove User");
        buttonRemoveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(buttonRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoveUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buttonLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUsername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameField)
                            .addComponent(passwordField))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPassword))
                .addGap(18, 18, 18)
                .addComponent(buttonLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRegister)
                    .addComponent(buttonRemoveUser))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* load usernames & passwords into internal arrays
     * reads from text file with hashed+salted user/pass info
     * TODO: implement hash and salt */
    private void initUserData() {
        USER_COUNT = 0;  // making sure userCount is actually 0
        try {
            FileReader reader = new FileReader("userData.txt");
            Scanner scanner = new Scanner(reader);
            String line;
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                USERNAMES[USER_COUNT] = line.split(" ")[0];
                PASSWORDS[USER_COUNT] = line.split(" ")[1];
                USER_COUNT++;
            }
            scanner.close();
            reader.close();
        } catch (IOException e) {
            try {
                FileWriter writer = new FileWriter("userData.txt");
                writer.write("admin" + " " + "password");
                writer.close();
                initUserData(); // THIS MIGHT BE BAD
            } catch(Exception f) {
                f.printStackTrace();
            }
        }
    }

    public String getCurrentUser() throws NoSuchFieldException {
        if(!LOGIN_SUCCESS)
            throw new NoSuchFieldException("User not logged in.");
        return CURRENT_USER;
    }

    public boolean getLoginStatus() {
        return LOGIN_SUCCESS;
    }

    /* rewrites the text file using internal variables */
    private void updateUserDataFile() {
        try {
            FileWriter writer = new FileWriter("userData.txt");
            for(int i=0; i<USER_COUNT; i++) {
                writer.write(USERNAMES[i] + " " + PASSWORDS[i] + "\n");
            }
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* to check if username and password exists in the data */
    private boolean userAndPassExists(String username, String password) {
        for(int i=0; i<MAX_USER_COUNT; i++) {
            if(username.equals(USERNAMES[i])
                    && password.equals(PASSWORDS[i]))
                return true;    // breaks the loop by returning true
        }
        return false;   // default return false
    }

    /* check if only username exists in the data */
    private boolean usernameExists(String username) {
        for(int i=0; i<MAX_USER_COUNT; i++) {
            if(username.equals(USERNAMES[i]))
                return true;    // breaks the loop by returning true
        }
        return false;   // default return false
    }

    /* returns index of username and password */
    /* returns -1 if not found in database */
    private int userIndex(String username, String password) {
        for(int i=0; i<MAX_USER_COUNT; i++) {
            if(username.equals(USERNAMES[i])
                    && password.equals(PASSWORDS[i]))
                return i;
        }
        return -1;
    }

    /* action upon login button press */
    /* notifies other synchronized objects upon success */
    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        String inputUsername = usernameField.getText();
        String inputPassword = String.valueOf(passwordField.getPassword());

        if(inputUsername.equals("") || inputPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
        } else if(userAndPassExists(inputUsername, inputPassword)) {
            synchronized(this) { notify(); }
            LOGIN_SUCCESS = true;
            CURRENT_USER = inputUsername;
            JOptionPane.showMessageDialog(null, "You are logged in as '" + CURRENT_USER + "'.");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect username or password.");
        }

        usernameField.setText(null);
        passwordField.setText(null);
    }//GEN-LAST:event_buttonLoginActionPerformed

    /* action upon register button press */
    /* adds username and password to data; calls for rewrite text file */
    private void buttonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegisterActionPerformed
        String inputUsername = usernameField.getText();
        String inputPassword = String.valueOf(passwordField.getPassword());

        if(inputUsername.equals("") || inputPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
        } else if(inputUsername.contains(" ") || inputPassword.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Usernames & passwords can't contain spaces.");
        } else if(usernameExists(inputUsername)) {
            JOptionPane.showMessageDialog(this, "Username in use.");
        } else if(!(USER_COUNT < MAX_USER_COUNT)) {
            JOptionPane.showMessageDialog(this, "Max amount of users registered.");
        } else {
            USERNAMES[USER_COUNT] = inputUsername;
            PASSWORDS[USER_COUNT] = inputPassword;
            USER_COUNT++;

            updateUserDataFile();
        }

        usernameField.setText(null);
        passwordField.setText(null);
    }//GEN-LAST:event_buttonRegisterActionPerformed

    /* action upon remove button press */
    /* removes username and password from data only if matching username and password */
    private void buttonRemoveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveUserActionPerformed
        String inputUsername = usernameField.getText();
        String inputPassword = String.valueOf(passwordField.getPassword());

        if(inputUsername.equals("admin"))
            JOptionPane.showMessageDialog(this, "Can't remove admin.");
        else if(inputUsername.equals("") || inputPassword.equals(""))
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
        else if(USER_COUNT == 0)
            JOptionPane.showMessageDialog(this, "No users registered.");
        else if(!userAndPassExists(inputUsername, inputPassword))
            JOptionPane.showMessageDialog(this, "Incorrect username or password.");
        else {
            int i = userIndex(inputUsername, inputPassword);
            for(; i<USER_COUNT-1; i++) {
                USERNAMES[i] = USERNAMES[i+1];
                PASSWORDS[i] = PASSWORDS[i+1];
            }
            USERNAMES[i] = null;
            PASSWORDS[i] = null;
            USER_COUNT--;
            updateUserDataFile();
            JOptionPane.showMessageDialog(this,
                    "User '" + inputUsername + "' successfully removed.");
        }

        usernameField.setText(null);
        passwordField.setText(null);
    }//GEN-LAST:event_buttonRemoveUserActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogin;
    private javax.swing.JButton buttonRegister;
    private javax.swing.JButton buttonRemoveUser;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
