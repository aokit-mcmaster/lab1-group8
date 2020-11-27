import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.nio.ByteBuffer;

/*
PACEMAKER PROTOCOL

INPUT (17 bytes)
---------------
BYTE	MEANING (units)
1	code (no unit, should be 1)
2	mode (enum)
3	LRL (ppm)
4	AV delay (10s of ms)
5	Atr Amp (0.1 of V)
6	Vent Amp (0.1 of V)
7	Atr Sens (0.1 of V)
8	Vent Sens (0.1 of V)
9	Atr PW (ms)
10	Vent PW (ms)
11	VRP (10s of ms)
12	ARP (10s of ms)
13	Max Sens Rate (ppm)
14	Act Thold (enum)
15	Reaction Time (sec)
16	Resp Factor (no unit)
17	Recover Time (min)

OUTPUT (16 bytes)
--------------------
CODE 0
bytes 1-16:	serial number

CODE 2
bytes 1-16:	parameters in same order

CODE 3
bytes 1-8:	atr signal sample (double)
bytes 9-16:	zeros

CODE 4
bytes 1-8:	vent signal sample (double)
bytes 9-16:	zeros

CODE 5
bytes 1-8:	atr signal sample (double)
bytes 9-16:	vent signal sample (double)
*/

public class DCM_SerialCOM {
    
    private static SerialPort SERIAL_PORT;
    private static volatile boolean IS_CONNECTED = false;
    private static volatile boolean RECEIVED = false;
    private static volatile boolean SENDING = false; // required so only one buffer is sent at a time
    private final byte[] OUTPUT_BUFFER = new byte[17];
    private final byte[] INPUT_BUFFER = new byte[16];
    private volatile int BUFFER_INDEX = 0; // index for inputBuffer
    
    // paramaeter codes for legibility
    private final byte READ_SERIAL_NUMBER = 0;
    private final byte WRITE_PARAMETERS = 1;
    private final byte READ_PARAMETERS = 2;
    private final byte READ_ATR_SIGNAL = 3;
    private final byte READ_VENT_SIGNAL = 4;
    private final byte READ_ATR_VENT_SIGNAL = 5;
    
    /* singleton method ensures only one object of edit user form is insantiated */
    /* only instantiates when needed called initially */
    private static DCM_SerialCOM soleInstance;
    protected static DCM_SerialCOM getInstance() {
        if(soleInstance == null)
            soleInstance = new DCM_SerialCOM();
        return soleInstance;
    }
    
    protected boolean initPort(SerialPort port) {
        String portName = port.getSystemPortName();
        
        if(portName.equals(this.getPortName()))
            this.disconnect();
        
        // try connect 5 times, return false if can't
        int i = 0;
        while(!port.openPort()) {
            System.out.println("Opening " + portName + " attempt: " + i);
            if(i++ >= 5) {
                System.out.println("Failed to connect to " + portName + ".");
                return false;
            }
        }
        
        SERIAL_PORT = port;
        SERIAL_PORT.openPort();
        SERIAL_PORT.setBaudRate(115200);
        SERIAL_PORT.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }
            @Override
            public void serialEvent(SerialPortEvent event) {
                if(BUFFER_INDEX < INPUT_BUFFER.length) {
                    byte[] temp = event.getReceivedData();
                    
                    for(int i=0; 
                            i<temp.length && BUFFER_INDEX < INPUT_BUFFER.length;
                            i++, BUFFER_INDEX++)
                        INPUT_BUFFER[BUFFER_INDEX] = temp[i];
                    
                    // TODO: switch/case in here which points to functions
                    if(BUFFER_INDEX >= INPUT_BUFFER.length) {
//                        System.out.println("Received data of size: " + INPUT_BUFFER.length);
//                        for (int i=0; i<inputBuffer.length; i++)
//                            System.out.print((char)inputBuffer[i]);
                        BUFFER_INDEX = 0;
                        RECEIVED = true;
                    }
                }
            }
        });
                
        IS_CONNECTED = true;
        
        return true;
    }
    
    public void writeParamaters(
            int mode,
            int lower_rate_limit,
            int fixed_AV_delay,
            float atr_amp,
            float vent_amp,
            float atr_sens,
            float vent_sens,
            int atr_width,
            int vent_width,
            int vrp,
            int arp,
            int max_sensor_rate,
            int activity_threshold,
            int reaction_time,
            int response_factor,
            int recovery_time) {
        
        // busy wait until port finished sending
        while(SENDING) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        SENDING = true; // lock sending flag
        
        // set output buffer
        OUTPUT_BUFFER[1] = (byte) (mode & 0xFF);
        OUTPUT_BUFFER[2] = (byte) (lower_rate_limit & 0xFF);
        OUTPUT_BUFFER[3] = (byte) ((int)(fixed_AV_delay/10) & 0xFF);
        OUTPUT_BUFFER[4] = (byte) ((int)(atr_amp/0.1) & 0xFF);
        OUTPUT_BUFFER[5] = (byte) ((int)(vent_amp/0.1) & 0xFF);
        OUTPUT_BUFFER[6] = (byte) ((int)(atr_sens/0.1) & 0xFF);
        OUTPUT_BUFFER[7] = (byte) ((int)(vent_sens/0.1) & 0xFF);
        OUTPUT_BUFFER[8] = (byte) (atr_width & 0xFF);
        OUTPUT_BUFFER[9] = (byte) (vent_width & 0xFF);
        OUTPUT_BUFFER[10] = (byte) ((int)(vrp/10) & 0xFF);
        OUTPUT_BUFFER[11] = (byte) ((int)(arp/10) & 0xFF);
        OUTPUT_BUFFER[12] = (byte) (max_sensor_rate & 0xFF);
        OUTPUT_BUFFER[13] = (byte) (activity_threshold & 0xFF);
        OUTPUT_BUFFER[14] = (byte) (reaction_time & 0xFF);
        OUTPUT_BUFFER[15] = (byte) (response_factor & 0xFF);
        OUTPUT_BUFFER[16] = (byte) (recovery_time & 0xFF);
        
        sendPaceMakerCode(WRITE_PARAMETERS);

        SENDING = false; // unlock sending flag
    }
    
    public String returnSerialCode() {
        // send the code to receive serial number
        sendPaceMakerCode(READ_SERIAL_NUMBER);
        
        try{
            // wait until input buffer is fully received
            int i = 0;
            while(!RECEIVED) {
                System.out.println("Geting serial number from " 
                        + SERIAL_PORT.getSystemPortName() 
                        + " attempt: " + i);
                if(i++ >= 10) {
                    System.out.println("Couldn't get serial number from " 
                            + SERIAL_PORT.getSystemPortName() + ".");
                    return "";
                }
                Thread.sleep(10);
            }
            RECEIVED = false; // reset flag after received
        } catch(Exception e) {
            e.printStackTrace();
        };

        String output = "";
        for(int i=0; i<INPUT_BUFFER.length; i++)
            output += (char) INPUT_BUFFER[i];
        System.out.println("Pacemaker Model #: " + output);
        
        return output;
    }
    
    public double[] returnAtrVentSignals() {
        // send the code to receive atrial and ventricular signals
        sendPaceMakerCode(READ_ATR_VENT_SIGNAL);
        
        // wait until input buffer is fully received
        while(!RECEIVED) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        RECEIVED = false; // reset flag after received
        
        // inplace reverse because java is big endian and I hate it
        byte temp;
        for(int i=0, j=INPUT_BUFFER.length-1; i<j; i++, j--) {
            temp = INPUT_BUFFER[i];
            INPUT_BUFFER[i] = INPUT_BUFFER[j];
            INPUT_BUFFER[j] = temp;
        }
        
        double[] output = new double[2];
        output[0] = 5 * ByteBuffer.wrap(INPUT_BUFFER).getDouble(8); // atrial data
        output[1] = 5 * ByteBuffer.wrap(INPUT_BUFFER).getDouble(0); // ventricular data
        
        System.out.println("Atr Value: " + output[0]);
        System.out.println("Ven Value: " + output[1] + "\n");
        
        return output;
    }
    
    private void sendPaceMakerCode(int code) {
        if(code < 0 && code >= 256)
            System.out.println("DCM_serialCOM: CODE OUT OF RANGE");
        if(IS_CONNECTED) {
            OUTPUT_BUFFER[0] = (byte) (0xFF & code); // first byte is the code
            SERIAL_PORT.writeBytes(OUTPUT_BUFFER, OUTPUT_BUFFER.length);
        }
    }
    
    public static String getPortName() {
        return IS_CONNECTED ? SERIAL_PORT.getSystemPortName() : "NULL";
    }
    
    public static boolean isConnected() {
        if(IS_CONNECTED) {
            IS_CONNECTED = SERIAL_PORT.isOpen();
        }
        if(!IS_CONNECTED) {
            disconnect();
        }
        return IS_CONNECTED;
    }
    
    public static void disconnect() {
        if(SERIAL_PORT != null) {
            SERIAL_PORT.removeDataListener();
            SERIAL_PORT.closePort();
            SERIAL_PORT = null;
        }
        IS_CONNECTED = false;
    }
    
}
