/**
 * @author Muntakim Ali
 * @organization McMaster University 3K04
 *      - login screen for DCM for Pacemaker
 */

import javax.swing.*;
import java.awt.event.*;

public class Login extends javax.swing.JFrame {

    private String[] usernames = new String[10];
    private String[] passwords = new String[10];
    private int userCount = 0;
    private final int MAX_USER_COUNT = 10;
    private volatile boolean LOGIN_SUCCESS = false;

    public Login() {
        usernames[0] = "admin";     // for testing purposes
        passwords[0] = "password";  // TODO: remove later
        initUserData();
        initComponents();
    }

    /* initialize all the components and widgets
     * sets positioning for each component */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        labelTitle = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        buttonLogin = new javax.swing.JButton();
        buttonRegister = new javax.swing.JButton();
        buttonRemoveUser = new javax.swing.JButton();

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
        this.getRootPane().setDefaultButton(buttonLogin);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameField))
                    .addComponent(labelTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(buttonRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRemoveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
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
        // TODO: read 'userData.txt' and add to internal user/pass fields
    }

    /* getter method to check if successful login attempt was made */
    protected boolean getLoginSuccess() {
        return LOGIN_SUCCESS;
    }

    /* to check if user exists in the data */
    private boolean userExists(String username, String password) {
        for(int i=0; i<MAX_USER_COUNT; i++) {
            if(username.equals(usernames[i])
                    && password.equals(passwords[i]))
                return true;    // breaks the loop by returning true
        }
        return false;   // default return false
    }

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        String inputUsername = usernameField.getText();
        String inputPassword = String.valueOf(passwordField.getPassword());

        if(inputUsername.equals("") || inputPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
        } else if(userExists(inputUsername, inputPassword)) {
            LOGIN_SUCCESS = true;
            JOptionPane.showMessageDialog(null, "Login success!");
        } else {
            usernameField.setText(null);
            passwordField.setText(null);
            JOptionPane.showMessageDialog(this, "Incorrect username or password.");
        }
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void buttonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegisterActionPerformed
        String inputUsername = usernameField.getText();
        String inputPassword = String.valueOf(passwordField.getPassword());

        if(inputUsername.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Usernames can't contain spaces.");
        } else if(userExists(inputUsername, inputPassword)) {
            JOptionPane.showMessageDialog(this, "User already registered.");
        } else {
            if(userCount < MAX_USER_COUNT) {
                // TODO: actually add user to 'userData.txt'
                userCount++;
            } else {
                JOptionPane.showMessageDialog(this, "Max amount of users registered.");
            }
        }
    }//GEN-LAST:event_buttonRegisterActionPerformed

    private void buttonRemoveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveUserActionPerformed
        String inputUsername = usernameField.getText();
        String inputPassword = String.valueOf(passwordField.getPassword());

        if(userCount == 0)
            JOptionPane.showMessageDialog(this, "No users registered.");
        else if(!userExists(inputUsername, inputPassword))
            JOptionPane.showMessageDialog(this, "User not registered.");
        else {
            // TODO: actually remove user from 'userData.txt'
            userCount--;
        }
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
