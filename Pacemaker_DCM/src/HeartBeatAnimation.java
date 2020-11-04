
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

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

public class HeartBeatAnimation {
    
    public Frame head;
    
    /**
     * Constructor method searches for all .txt files in passed folder directory.
     * Each .txt should be an ascii image, and method constructs a circular linked list
     * with each text file string as a node.
     * @param folderDir - directory for ascii .txt files
     */
    public HeartBeatAnimation(String folderDir) {
        try {
            // gets folder directory and ensures it exists
            File folder = new File(folderDir);
            if(folder.exists() && folder.isDirectory()) {

                // gets all text files in directory
                File[] listOfFiles =  folder.listFiles();
                Arrays.sort(listOfFiles);   // sorts by number

                // intialize scanner
                Scanner scanner;

                // initialize head
                scanner = new Scanner(listOfFiles[0]);
                head = new Frame(scanner.useDelimiter("\\A").next());
                Frame current = head;

                // create links for next sequences
                for(int i=1; i<listOfFiles.length; i++) {
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

}

