/**
 * @author Muntakim Ali
 * @organization McMaster University 3K04
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class EditUserForm extends javax.swing.JFrame {

    private final int MAX_USER_COUNT = 10;
    private int USER_COUNT = 0;
    private final String[] USERNAMES = new String[MAX_USER_COUNT];
    private final String[] PASSWORDS = new String[MAX_USER_COUNT];
    
    private static EditUserForm soleInstance;
    
    public EditUserForm() {
        initUserData();
        initComponents();
        updateScrollPane();
    }
    
    /* singleton method ensures only one object of edit user form is insantiated */
    protected static EditUserForm getInstance() {
        if(soleInstance == null)
            soleInstance = new EditUserForm();
        return soleInstance;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        labelRemove = new javax.swing.JLabel();
        labelRegister = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        scrollPaneUserList = new javax.swing.JScrollPane();
        listUsers = new javax.swing.JList<>();
        buttonRemoveUser = new javax.swing.JButton();
        buttonRegisterUser = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Users");
        setResizable(false);

        labelTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Edit Users");

        labelRemove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRemove.setText("Select to Remove User");

        labelRegister.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRegister.setText("Register New User");

        labelUsername.setText("Username:");

        labelPassword.setText("Password:");

        listUsers.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "ADMIN", "USER1", "USER2", "USER3", "USER4", "USER5", "USER6", "USER7", "USER8", "USER9" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listUsers.setAutoscrolls(false);
        listUsers.setRequestFocusEnabled(false);
        scrollPaneUserList.setViewportView(listUsers);

        buttonRemoveUser.setText("Remove");
        buttonRemoveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveUserActionPerformed(evt);
            }
        });

        buttonRegisterUser.setText("Register");
        buttonRegisterUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegisterUserActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelRemove, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPaneUserList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(buttonRemoveUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelUsername))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernameField)
                                    .addComponent(passwordField))))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRegisterUser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelRemove)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(labelRegister)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelUsername))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPassword))
                                .addGap(18, 18, 18)
                                .addComponent(buttonRegisterUser))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPaneUserList, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoveUser)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch(IOException e) {
            try {
                FileWriter writer = new FileWriter("userData.txt");
                writer.write("admin" + " " + "password");
                writer.close();
            } catch(Exception f) {
                f.printStackTrace();
            }
        }
    }
    
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
    
    /* ouputs internal usernames to JList */
    private void updateScrollPane() {
        DefaultListModel model = new DefaultListModel();
        for(int i=0; i<USER_COUNT; i++)
            model.addElement(USERNAMES[i]);
        listUsers.setModel(model);
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
    private int userIndex(String username) {
        for(int i=0; i<MAX_USER_COUNT; i++) {
            if(username.equals(USERNAMES[i]))
                return i;
        }
        return -1;
    }
    
    private void buttonRemoveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveUserActionPerformed
        String inputUsername = listUsers.getSelectedValue();
        if(inputUsername.equals("admin"))
            JOptionPane.showMessageDialog(this, "Can't remove admin.");
        else {
            int i=userIndex(inputUsername);
            for(; i<USER_COUNT-1; i++) {
                USERNAMES[i] = USERNAMES[i+1];
                PASSWORDS[i] = PASSWORDS[i+1];
            }
            USERNAMES[i] = null;
            PASSWORDS[i] = null;
            USER_COUNT--;
            
            updateUserDataFile();
            updateScrollPane();
        }

        usernameField.setText(null);
        passwordField.setText(null);
    }//GEN-LAST:event_buttonRemoveUserActionPerformed

    private void buttonRegisterUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegisterUserActionPerformed
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
            updateScrollPane();
        }

        usernameField.setText(null);
        passwordField.setText(null);
    }//GEN-LAST:event_buttonRegisterUserActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonRegisterUser;
    private javax.swing.JButton buttonRemoveUser;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelRegister;
    private javax.swing.JLabel labelRemove;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JList<String> listUsers;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JScrollPane scrollPaneUserList;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
