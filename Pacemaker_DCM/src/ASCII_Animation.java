
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 * A "frame" in a an "animation" is like
 * a "node" in a "linked list".
 */
class Frame {
    String frame;
    Frame next;
    
    Frame(String ascii_string) {
        this.frame = ascii_string;
        this.next = null;
    }
}

public class ASCII_Animation {
    
    private Frame head;
    private int frameCount = 0;
    private volatile int msDelay = 33;
    private volatile boolean running = false;
    
    /**
     * Constructor method searches for all .txt files in passed folder directory.
     * Each .txt should be an ascii image, and method constructs a circular linked list
     * with each text file string as a node.
     * @param folderDir - directory for ascii .txt files
     */
    public ASCII_Animation(String folderDir) {
        try {
            // gets folder directory and ensures it exists
            File folder = new File(folderDir);
            if(folder.exists() && folder.isDirectory()) {

                // gets all text files in directory
                File[] listOfFiles =  folder.listFiles();
                Arrays.sort(listOfFiles);   // sorts by number
                frameCount = listOfFiles.length;

                // intialize scanner and head 
                Scanner scanner = new Scanner(listOfFiles[0]);
                head = new Frame(scanner.useDelimiter("\\A").next());
                Frame current = head;

                // create links for next sequences
                for(int i=1; i<frameCount; i++) {
                    scanner = new Scanner(listOfFiles[i]);
                    current.next = new Frame(scanner.useDelimiter("\\A").next());
                    current = current.next;
                }
                
                // the tail's next pointer is head to make it circular
                current.next = head;
            } else {
                // case for when folder not found
                head = new Frame("can't find animation!");
                head.next = new Frame("MISSING FOLDER?!?!");
                head.next.next = head;  // circular; repeat messages
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method starts the animation on a separate thread, as to not stall the program.
     * This animation is an infinite loop that cyclically iterates through 
     * the frames in the animation sequence. Method .play() must be called to start
     * the animation sequence.
     * @param screen - the JTextArea which the ascii art is printed onto
     */
    public void animate(JTextArea screen) {
        new Thread(() -> {
            // sets first frame
            Frame current = this.head;
            screen.setText("\n\n" + current.frame);
            
            // infinitly loop through animation sequence
            while(current.next != null) {
                // only print & iterate when *running*
                if(running) {
                    screen.setText("\n\n" + current.frame);
                    current = current.next;
                }
                try {
                    Thread.sleep(msDelay);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    
    public synchronized void pause() {
        running = false;
    }
    
    public synchronized void play() {
        running = true;
    }
    
    public int getframeCount() {
        return frameCount;
    }
    
    public double getFPS() {
        return 1/(msDelay/1000);
    }
    
    public synchronized void setDelay(int delay) {
        msDelay = delay;
    }
    
    public synchronized void setFPS(double fps) {
        msDelay = (int) ((int) 1000/fps);
    }
    
    public synchronized void increaseSpeed() {
        setFPS(1.1 * getFPS());
    }
    
    public synchronized void decreaseSpeed() {
        setFPS(0.9 * getFPS());
    }

}

