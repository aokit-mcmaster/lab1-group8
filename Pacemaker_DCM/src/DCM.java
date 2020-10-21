/**
 * @author Muntakim Ali
 * @organization McMaster University 3K04
 */

import javax.swing.*;
import com.fazecast.jSerialComm.*;

public class DCM extends javax.swing.JFrame {

    // operating modes of pacemaker
    enum PACEMAKER_MODE { VOO, AOO, VVI, AAI };

    // paramaters to send to pacemaker
    PACEMAKER_MODE p_mode;
    float p_lower_rate_limit;
    float p_upper_rate_limit;
    float p_atr_pulse_amplitude;
    float p_vent_pulse_amplitude;
    float p_atr_pulse_width;
    float p_vent_pulse_width;
    float p_atr_sensitivity;
    float p_vent_sensitivity;
    float p_vrp;
    float p_arp;
    float p_pvarp;
    boolean p_hysteresis_enable;
    float p_hysteresis_rate_limit;
    boolean p_rate_smoothing_enable;
    float p_rate_smoothing_percent;

    // internal boolean field to track when parameters are being sent
    private boolean ADMIN_MODE = false;
    private boolean SENDING_PARAMETERS = false;
    private boolean IS_CONNECTED = false;

    public DCM() {
        initComponents();
        resetAllFields();
        initParameters();
        initSerialPorts();
    }
    
    public DCM(String username) {
        initComponents();
        resetAllFields();
        initParameters();
        initSerialPorts();
        ADMIN_MODE = username.equals("admin");
    }

    private void initSerialPorts() {
        
    }
    
    /* initialize the JFrame form */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupPMode = new javax.swing.ButtonGroup();
        labelTitle = new javax.swing.JLabel();
        radioButtonVOO = new javax.swing.JRadioButton();
        radioButtonAOO = new javax.swing.JRadioButton();
        radioButtonVVI = new javax.swing.JRadioButton();
        radioButtonAAI = new javax.swing.JRadioButton();
        fieldLowerRateLimit = new javax.swing.JTextField();
        fieldUpperRateLimit = new javax.swing.JTextField();
        fieldAtrPulseAmp = new javax.swing.JTextField();
        fieldVentPulseAmp = new javax.swing.JTextField();
        fieldAtrPulseWidth = new javax.swing.JTextField();
        fieldVentPulseWidth = new javax.swing.JTextField();
        fieldAtrSens = new javax.swing.JTextField();
        fieldVentSens = new javax.swing.JTextField();
        fieldVRP = new javax.swing.JTextField();
        fieldARP = new javax.swing.JTextField();
        fieldPVARP = new javax.swing.JTextField();
        fieldHysteresisRateLimit = new javax.swing.JTextField();
        fieldRateSmoothingPercent = new javax.swing.JTextField();
        labelMode = new javax.swing.JLabel();
        labelLowerRateLimit = new javax.swing.JLabel();
        labelUpperRateLimit = new javax.swing.JLabel();
        labelAtrPulseAmp = new javax.swing.JLabel();
        labelVentPulseAmp = new javax.swing.JLabel();
        labelAtrPulseWidth = new javax.swing.JLabel();
        labelVentPulseWidth = new javax.swing.JLabel();
        labelAtrSens = new javax.swing.JLabel();
        labelVentSens = new javax.swing.JLabel();
        labelVRP = new javax.swing.JLabel();
        labelARP = new javax.swing.JLabel();
        labelPVARP = new javax.swing.JLabel();
        labelHysteresisRateLimit = new javax.swing.JLabel();
        labelRateSmoothingPercent = new javax.swing.JLabel();
        enableSmoothing = new javax.swing.JCheckBox();
        enableHysteresis = new javax.swing.JCheckBox();
        buttonLogout = new javax.swing.JButton();
        buttonEditUser = new javax.swing.JButton();
        buttonResetParam = new javax.swing.JButton();
        buttonSendParam = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        labelTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("DCM");

        buttonGroupPMode.add(radioButtonVOO);
        radioButtonVOO.setText("VOO");

        buttonGroupPMode.add(radioButtonAOO);
        radioButtonAOO.setText("AOO");

        buttonGroupPMode.add(radioButtonVVI);
        radioButtonVVI.setText("VVI");

        buttonGroupPMode.add(radioButtonAAI);
        radioButtonAAI.setText("AAI");

        labelMode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMode.setText("p_mode");

        labelLowerRateLimit.setText("p_lower_rate_limit");

        labelUpperRateLimit.setText("p_upper_rate_limit");

        labelAtrPulseAmp.setText("p_atr_pulse_amplitude");

        labelVentPulseAmp.setText("p_vent_pulse_amplitude");

        labelAtrPulseWidth.setText("p_atr_pulse_width");

        labelVentPulseWidth.setText("p_vent_pulse_width");

        labelAtrSens.setText("p_atr_sensitivity");
        labelAtrSens.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        labelVentSens.setText("p_vent_sensitivity");

        labelVRP.setText("p_vrp");

        labelARP.setText("p_arp");

        labelPVARP.setText("p_pvarp");

        labelHysteresisRateLimit.setText("p_hysteresis_rate_limit");

        labelRateSmoothingPercent.setText("p_rate_smoothing_percent");

        enableSmoothing.setText("p_rate_smoothing_enable ");
        enableSmoothing.setToolTipText("");
        enableSmoothing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableSmoothingActionPerformed(evt);
            }
        });

        enableHysteresis.setText("p_hysteresis_enable ");
        enableHysteresis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableHysteresisActionPerformed(evt);
            }
        });

        buttonLogout.setText("Logout");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });

        buttonEditUser.setText("Edit Users");
        buttonEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditUserActionPerformed(evt);
            }
        });

        buttonResetParam.setText("Reset Parameters");
        buttonResetParam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetParamActionPerformed(evt);
            }
        });

        buttonSendParam.setText("Send Parameters to Pacemaker");
        buttonSendParam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendParamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelMode, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioButtonAOO)
                                    .addComponent(radioButtonVOO))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioButtonVVI)
                                    .addComponent(radioButtonAAI)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(fieldUpperRateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(labelUpperRateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(fieldAtrPulseAmp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(labelAtrPulseAmp, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(fieldVentPulseAmp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(labelVentPulseAmp, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fieldAtrPulseWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(labelAtrPulseWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fieldVentPulseWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(labelVentPulseWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(fieldLowerRateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(labelLowerRateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fieldHysteresisRateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(labelHysteresisRateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fieldRateSmoothingPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(labelRateSmoothingPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(enableSmoothing, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enableHysteresis, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fieldAtrSens, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(labelAtrSens, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fieldVentSens, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(labelVentSens, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fieldVRP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fieldARP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fieldPVARP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(6, 6, 6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelVRP, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelARP, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelPVARP, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(buttonSendParam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(buttonResetParam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radioButtonVVI)
                                        .addGap(0, 0, 0)
                                        .addComponent(radioButtonAAI))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radioButtonVOO)
                                        .addGap(0, 0, 0)
                                        .addComponent(radioButtonAOO)))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelMode)
                                .addGap(38, 38, 38)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldLowerRateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLowerRateLimit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldUpperRateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUpperRateLimit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldAtrPulseAmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAtrPulseAmp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldVentPulseAmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelVentPulseAmp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldAtrPulseWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAtrPulseWidth))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldVentPulseWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelVentPulseWidth)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldAtrSens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(labelAtrSens)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldVentSens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(labelVentSens)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fieldVRP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fieldARP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelARP))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fieldPVARP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPVARP)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(labelVRP)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldHysteresisRateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(labelHysteresisRateLimit)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldRateSmoothingPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(labelRateSmoothingPercent)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(enableHysteresis)
                        .addGap(0, 0, 0)
                        .addComponent(enableSmoothing)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonResetParam)
                            .addComponent(buttonEditUser))
                        .addGap(0, 0, 0)
                        .addComponent(buttonSendParam))
                    .addComponent(buttonLogout))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* sets all textfields to default values */
    private void resetAllFields() {
        // automatically on AOO mode
        radioButtonAOO.setSelected(true);

        // all fields are 0
        fieldLowerRateLimit.setText("0");
        fieldUpperRateLimit.setText("0");
        fieldAtrPulseAmp.setText("0");
        fieldVentPulseAmp.setText("0");
        fieldAtrPulseWidth.setText("0");
        fieldVentPulseWidth.setText("0");
        fieldAtrSens.setText("0");
        fieldVentSens.setText("0");
        fieldVRP.setText("0");
        fieldARP.setText("0");
        fieldPVARP.setText("0");
        fieldHysteresisRateLimit.setText("0");
        fieldRateSmoothingPercent.setText("0");

        // uncheck boxes and disable associated fields
        enableHysteresis.setSelected(false);
        fieldHysteresisRateLimit.setEnabled(false);
        enableSmoothing.setSelected(false);
        fieldRateSmoothingPercent.setEnabled(false);
    }

    /* gets parameter values from text fields */
    private void initParameters() {
        // set p_mode epending on radio button selection
        if(radioButtonAOO.isSelected())
            p_mode = PACEMAKER_MODE.AOO;
        if(radioButtonVOO.isSelected())
            p_mode = PACEMAKER_MODE.VOO;
        if(radioButtonVVI.isSelected())
            p_mode = PACEMAKER_MODE.VVI;
        if(radioButtonAAI.isSelected())
            p_mode = PACEMAKER_MODE.AAI;

        // set paramaters from text field input
        p_lower_rate_limit = Float.valueOf(fieldLowerRateLimit.getText());
        p_upper_rate_limit = Float.valueOf(fieldUpperRateLimit.getText());;
        p_atr_pulse_amplitude = Float.valueOf(fieldAtrPulseAmp.getText());
        p_vent_pulse_amplitude = Float.valueOf(fieldVentPulseAmp.getText());
        p_atr_pulse_width = Float.valueOf(fieldAtrPulseWidth.getText());
        p_vent_pulse_width = Float.valueOf(fieldVentPulseWidth.getText());
        p_atr_sensitivity = Float.valueOf(fieldAtrSens.getText());
        p_vent_sensitivity = Float.valueOf(fieldVentSens.getText());
        p_vrp = Float.valueOf(fieldVRP.getText());
        p_arp = Float.valueOf(fieldARP.getText());
        p_pvarp = Float.valueOf(fieldPVARP.getText());

        // set enables from checkbox input and asociated text field input
        p_hysteresis_enable = enableHysteresis.isSelected();
        p_hysteresis_rate_limit = Float.valueOf(fieldHysteresisRateLimit.getText());
        p_rate_smoothing_enable = enableSmoothing.isSelected();
        p_rate_smoothing_percent = Float.valueOf(fieldRateSmoothingPercent.getText());
    }

    private void disableAllComponents() {
        radioButtonAAI.setEnabled(false);
        radioButtonAOO.setEnabled(false);
        radioButtonVOO.setEnabled(false);
        radioButtonVVI.setEnabled(false);

        fieldLowerRateLimit.setEnabled(false);
        fieldUpperRateLimit.setEnabled(false);
        fieldAtrPulseAmp.setEnabled(false);
        fieldVentPulseAmp.setEnabled(false);
        fieldAtrPulseWidth.setEnabled(false);
        fieldVentPulseWidth.setEnabled(false);
        fieldAtrSens.setEnabled(false);
        fieldVentSens.setEnabled(false);
        fieldVRP.setEnabled(false);
        fieldARP.setEnabled(false);
        fieldPVARP.setEnabled(false);
        fieldHysteresisRateLimit.setEnabled(false);
        fieldRateSmoothingPercent.setEnabled(false);

        enableHysteresis.setEnabled(false);
        enableSmoothing.setEnabled(false);

        fieldHysteresisRateLimit.setEnabled(false);
        fieldRateSmoothingPercent.setEnabled(false);
    }

    private void enableAllComponents() {
        radioButtonAAI.setEnabled(true);
        radioButtonAOO.setEnabled(true);
        radioButtonVOO.setEnabled(true);
        radioButtonVVI.setEnabled(true);

        fieldLowerRateLimit.setEnabled(true);
        fieldUpperRateLimit.setEnabled(true);
        fieldAtrPulseAmp.setEnabled(true);
        fieldVentPulseAmp.setEnabled(true);
        fieldAtrPulseWidth.setEnabled(true);
        fieldVentPulseWidth.setEnabled(true);
        fieldAtrSens.setEnabled(true);
        fieldVentSens.setEnabled(true);
        fieldVRP.setEnabled(true);
        fieldARP.setEnabled(true);
        fieldPVARP.setEnabled(true);
        fieldHysteresisRateLimit.setEnabled(true);
        fieldRateSmoothingPercent.setEnabled(true);

        enableHysteresis.setEnabled(true);
        enableSmoothing.setEnabled(true);

        fieldHysteresisRateLimit.setEnabled(enableHysteresis.isSelected());
        fieldRateSmoothingPercent.setEnabled(enableSmoothing.isSelected());
    }

    /* if left checked -> enable associated field */
    /* if left unchecked -> disable associated field */
    private void enableSmoothingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableSmoothingActionPerformed
        fieldRateSmoothingPercent.setEnabled(enableSmoothing.isSelected());
    }//GEN-LAST:event_enableSmoothingActionPerformed

    /* if left checked -> enable associated field */
    /* if left unchecked -> disable associated field */
    private void enableHysteresisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableHysteresisActionPerformed
        fieldHysteresisRateLimit.setEnabled(enableHysteresis.isSelected());
    }//GEN-LAST:event_enableHysteresisActionPerformed

    /* set all text fields to default values */
    private void buttonResetParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetParamActionPerformed
        resetAllFields();
    }//GEN-LAST:event_buttonResetParamActionPerformed

    /* DCM communicates with pacemaker to send parameter values  */
    private void buttonSendParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendParamActionPerformed
        initParameters();

        SENDING_PARAMETERS = true;
        disableAllComponents();



        SENDING_PARAMETERS = false;
        enableAllComponents();
    }//GEN-LAST:event_buttonSendParamActionPerformed

    /* opens EditUserForm */
    private void buttonEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditUserActionPerformed
        if(ADMIN_MODE) {
            EditUserForm editUserForm = new EditUserForm();
            editUserForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            editUserForm.setLocationRelativeTo(this);
            editUserForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Login as admin.");
        }
    }//GEN-LAST:event_buttonEditUserActionPerformed

    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        synchronized(this) {
            notify();
            dispose();
        }
    }//GEN-LAST:event_buttonLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEditUser;
    private javax.swing.ButtonGroup buttonGroupPMode;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonResetParam;
    private javax.swing.JButton buttonSendParam;
    private javax.swing.JCheckBox enableHysteresis;
    private javax.swing.JCheckBox enableSmoothing;
    private javax.swing.JTextField fieldARP;
    private javax.swing.JTextField fieldAtrPulseAmp;
    private javax.swing.JTextField fieldAtrPulseWidth;
    private javax.swing.JTextField fieldAtrSens;
    private javax.swing.JTextField fieldHysteresisRateLimit;
    private javax.swing.JTextField fieldLowerRateLimit;
    private javax.swing.JTextField fieldPVARP;
    private javax.swing.JTextField fieldRateSmoothingPercent;
    private javax.swing.JTextField fieldUpperRateLimit;
    private javax.swing.JTextField fieldVRP;
    private javax.swing.JTextField fieldVentPulseAmp;
    private javax.swing.JTextField fieldVentPulseWidth;
    private javax.swing.JTextField fieldVentSens;
    private javax.swing.JLabel labelARP;
    private javax.swing.JLabel labelAtrPulseAmp;
    private javax.swing.JLabel labelAtrPulseWidth;
    private javax.swing.JLabel labelAtrSens;
    private javax.swing.JLabel labelHysteresisRateLimit;
    private javax.swing.JLabel labelLowerRateLimit;
    private javax.swing.JLabel labelMode;
    private javax.swing.JLabel labelPVARP;
    private javax.swing.JLabel labelRateSmoothingPercent;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelUpperRateLimit;
    private javax.swing.JLabel labelVRP;
    private javax.swing.JLabel labelVentPulseAmp;
    private javax.swing.JLabel labelVentPulseWidth;
    private javax.swing.JLabel labelVentSens;
    private javax.swing.JRadioButton radioButtonAAI;
    private javax.swing.JRadioButton radioButtonAOO;
    private javax.swing.JRadioButton radioButtonVOO;
    private javax.swing.JRadioButton radioButtonVVI;
    // End of variables declaration//GEN-END:variables
}
