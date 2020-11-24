
/* this class might be unnecessary */

public class PacemakerParameters {
    
    // operating modes of pacemaker
    enum PACEMAKER_MODE { AOO, VOO, AAI, VVI, DDD, DDDR }
    
    // p parameters
    public PACEMAKER_MODE p_mode;
    public float p_lower_rate_limit;
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
    
}
