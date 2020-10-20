
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class DCM_OLD extends JFrame {
    
    public DCM_OLD() {
        initParameters();
        initComponents();
    }
    
    private final int P_NUM = 16;    // number of p_ parameters
    
    // operating modes of pacemaker
    enum PACEMAKER_MODE {
        VOO,
        AOO,
        VVI,
        AAI,
        NULL;
    }
    
    // p parameters
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
    
    // pointers to p parameters
    Object[] P_OBJECT_ARRAY = new Object[] {
        p_mode,
        p_lower_rate_limit,
        p_upper_rate_limit,
        p_atr_pulse_amplitude,
        p_vent_pulse_amplitude,
        p_atr_pulse_width,
        p_vent_pulse_width,
        p_atr_sensitivity,
        p_vent_sensitivity,
        p_vrp,
        p_arp,
        p_pvarp,
        p_hysteresis_enable,
        p_hysteresis_rate_limit,
        p_rate_smoothing_enable,
        p_rate_smoothing_percent
    };
    
    // h parameters
    float h_atr_pulse_detect;
    float h_vent_pulse_detect;
    
    // internal signals
    float start_pace;
    
    private void initParameters() {
        p_mode = PACEMAKER_MODE.NULL;
        p_lower_rate_limit = 0;
        p_upper_rate_limit = 0;
        p_atr_pulse_amplitude = 0;
        p_vent_pulse_amplitude = 0;
        p_atr_pulse_width = 0;
        p_vent_pulse_width = 0;
        p_atr_sensitivity = 0;
        p_vent_sensitivity = 0;
        p_vrp = 0;
        p_arp = 0;
        p_pvarp = 0; 
        p_hysteresis_enable = false;
        p_hysteresis_rate_limit = 0;
        p_rate_smoothing_enable = false;
        p_rate_smoothing_percent = 0;
    }

    // JFrame components
    private JLabel labelTitle;
    private JLabel[] labelArray = new JLabel[P_NUM];
    private JTextField[] inputFieldArray = new JTextField[P_NUM];
    private JCheckBox checkRateSmoothing = new JCheckBox();
    private JCheckBox checkHysteresis = new JCheckBox();
    private JButton buttonEditParams = new JButton();
    private JButton buttonLogout = new JButton();

    private void initComponents() {
        GridBagConstraints gridBagConstraints;
        
        getContentPane().setLayout(new GridBagLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // title label
        labelTitle = new javax.swing.JLabel();
        labelTitle.setText("DCM");
        labelTitle.setFont(new java.awt.Font("Dialog", 1, 24));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(labelTitle, gridBagConstraints);

        String[] textsToLabel = new String[] {
            "p_mode",
            "p_lower_rate_limit",
            "p_upper_rate_limit",
            "p_atr_pulse_amplitude",
            "p_vent_pulse_amplitude",
            "p_atr_pulse_width",
            "p_vent_pulse_width",
            "p_atr_sensitivity",
            "p_vent_sensitivity",
            "p_vrp",
            "p_arp",
            "p_pvarp",
            "p_hysteresis_enable",
            "p_hysteresis_rate_limit",
            "p_rate_smoothing_enable",
            "p_rate_smoothing_percent"
        };
        
        // adding all JLabels to pane
        for(int i=0; i<P_NUM; i++) {
            labelArray[i] = new JLabel();
            labelArray[i].setText(textsToLabel[i]);
            labelArray[i].setHorizontalAlignment(SwingConstants.LEFT);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1 + i;
            getContentPane().add(labelArray[i], gridBagConstraints);
        }
        
        // adding all JTextFields to pane
        for(int i=0; i<P_NUM; i++) {
            if(textsToLabel[i].equals("p_hysteresis_enable")) {
                checkHysteresis = new JCheckBox();
                checkHysteresis.setSelected(p_hysteresis_enable);
                gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1 + i;
                getContentPane().add(checkHysteresis, gridBagConstraints);
                continue;
            }
            if(textsToLabel[i].equals("p_rate_smoothing_enable")) {
                checkRateSmoothing = new JCheckBox();
                checkRateSmoothing.setSelected(p_rate_smoothing_enable);
                gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1 + i;
                getContentPane().add(checkRateSmoothing, gridBagConstraints);
                continue;
            }
            if(textsToLabel[i].equals("p_mode")) {
                inputFieldArray[i] = new JTextField();
                inputFieldArray[i].setColumns(4);
                inputFieldArray[i].setText(p_mode.toString());
                inputFieldArray[i].setHorizontalAlignment(SwingConstants.CENTER);
                gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1 + i;
                gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
                getContentPane().add(inputFieldArray[i], gridBagConstraints);
                continue;
            }
            inputFieldArray[i] = new JTextField();
            inputFieldArray[i].setColumns(4);
            inputFieldArray[i].setText((P_OBJECT_ARRAY[i]).toString());
            inputFieldArray[i].setHorizontalAlignment(SwingConstants.CENTER);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1 + i;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            getContentPane().add(inputFieldArray[i], gridBagConstraints);
        }
        
        pack();
    }

}
