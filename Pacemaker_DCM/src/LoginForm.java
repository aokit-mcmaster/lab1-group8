/**
 * @author Muntakim Ali
 * @organization McMaster University 3K04
 *      - login screen for DCM for Pacemaker
 */

import javax.swing.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;

public class LoginForm extends javax.swing.JFrame {

    private volatile boolean LOGIN_SUCCESS = false;
    private final int MAX_USER_COUNT = 10;
    private int userCount = 0;
    private final String[] usernames = new String[MAX_USER_COUNT];
    private final String[] passwords = new String[MAX_USER_COUNT];

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

        getRootPane().setDefaultButton(buttonLogin);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        labelTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Login Screen");

        labelUsername.setText("Username:");

        labelPassword.setText("Password:");

        buttonLogin.setText("Login");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameField))
                    .addComponent(labelTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(buttonRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoveUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buttonLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        userCount = 0;  // making sure userCount is actually 0
        try {
            FileReader reader = new FileReader("userData.txt");
            Scanner scanner = new Scanner(reader);
            String line;
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                usernames[userCount] = line.split(" ")[0];
                passwords[userCount] = line.split(" ")[1];
                userCount++;
            }
            scanner.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* rewrites the text file using internal variables */
    private void updateUserDataFile() {
        try {
            FileWriter writer = new FileWriter("userData.txt");
            for(int i=0; i<userCount; i++) {
                writer.write(usernames[i] + " " + passwords[i] + "\n");
            }
            writer.close();
//            for(int i=0; i<MAX_USER_COUNT; i++)
//                System.out.println((i+1) + " " + usernames[i] + " " + passwords[i]);
            System.out.println();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* to check if username and password exists in the data */
    private boolean userAndPassExists(String username, String password) {
        for(int i=0; i<MAX_USER_COUNT; i++) {
            if(username.equals(usernames[i])
                    && password.equals(passwords[i]))
                return true;    // breaks the loop by returning true
        }
        return false;   // default return false
    }

    private boolean usernameExists(String username) {
        for(int i=0; i<MAX_USER_COUNT; i++) {
            if(username.equals(usernames[i]))
                return true;    // breaks the loop by returning true
        }
        return false;   // default return false
    }

    private int userIndex(String username, String password) {
        for(int i=0; i<MAX_USER_COUNT; i++) {
            if(username.equals(usernames[i])
                    && password.equals(passwords[i]))
                return i;
        }
        return -1;
    }

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        String inputUsername = usernameField.getText();
        String inputPassword = String.valueOf(passwordField.getPassword());

        if(inputUsername.equals("") || inputPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
        } else if(userAndPassExists(inputUsername, inputPassword)) {
            synchronized(this) { notify(); }
            LOGIN_SUCCESS = true;
            JOptionPane.showMessageDialog(null, "Login success!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect username or password.");
        }

        usernameField.setText(null);
        passwordField.setText(null);
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void buttonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegisterActionPerformed
        String inputUsername = usernameField.getText();
        String inputPassword = String.valueOf(passwordField.getPassword());

        if(inputUsername.contains(" ") || inputPassword.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Usernames & passwords can't contain spaces.");
        } else if(usernameExists(inputUsername)) {
            JOptionPane.showMessageDialog(this, "Username in use.");
        } else {
            if(userCount < MAX_USER_COUNT) {
                usernames[userCount] = inputUsername;
                passwords[userCount] = inputPassword;
                userCount++;
                updateUserDataFile();
                JOptionPane.showMessageDialog(this, "User successfully registered.");
            } else {
                JOptionPane.showMessageDialog(this, "Max amount of users registered.");
            }
        }

        usernameField.setText(null);
        passwordField.setText(null);
    }//GEN-LAST:event_buttonRegisterActionPerformed

    private void buttonRemoveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveUserActionPerformed
        String inputUsername = usernameField.getText();
        String inputPassword = String.valueOf(passwordField.getPassword());

        if(userCount == 0)
            JOptionPane.showMessageDialog(this, "No users registered.");
        else if(!userAndPassExists(inputUsername, inputPassword))
            JOptionPane.showMessageDialog(this, "User not registered.");
        else {
            int i=userIndex(inputUsername, inputPassword);
            for(; i<userCount-1; i++) {
                usernames[i] = usernames[i+1];
                passwords[i] = passwords[i+1];
            }
            usernames[i] = null;
            passwords[i] = null;
            userCount--;
            updateUserDataFile();
            JOptionPane.showMessageDialog(this, "User '" + inputUsername + "' successfully removed.");
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
