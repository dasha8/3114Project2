import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class exists to read in commands from the project's input file and parse
 * them to determine the action to be taken.
 * 
 * @author Dasha Savina (dasha8)
 * @version 09.18.18
 *
 */
public class CommandParser {

    private Scanner scanner;
    private Database database;


    /**
     * Initializes a Scanner that will move through our input file.
     * 
     * @param inputFile
     *            the file we are getting input from
     * @param initialHashSize
     *            initial hash table size passed in through arguments
     */
    public CommandParser(String inputFile, int initialHashSize) {

        try {
            scanner = new Scanner(new File(inputFile));
            database = new Database(initialHashSize);
        }
        catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }
    }


    /**
     * Reads in the whole file line by line, cleaning each line and then sending
     * it to checkCommand to be acted on.
     * 
     * @return if a line is read is successfully read or not
     */
    public boolean readFile() {
        boolean read = false;

        while (scanner.hasNextLine()) {
            read = true;
            String line = this.cleanCommand(scanner.nextLine());
            if (line != null) {
                this.checkCommand(line);
            }
        }

        scanner.close();
        return read;
    }


    /**
     * Takes in a line from the input file and removes extra spaces, extra tabs,
     * etc.
     * 
     * @param currentLine
     *            the line that will be cleaned up
     * @return A clean, edited command
     */
    public String cleanCommand(String currentLine) {

        // Replaces all groups of tabs and spaces with a single space.
        currentLine = currentLine.trim();
        currentLine = currentLine.replaceAll("\\s+", " ");
        currentLine = currentLine.replace("\n", "");

        if (currentLine.equals("") || currentLine.equals(" ")) {
            return null;
        }

// // If the line begins with a space, remove it.
// if (' ' == currentLine.charAt(0)) {
// currentLine = currentLine.substring(1, currentLine.length());
// }
// // If the line ends with a space, remove it.
// if (' ' == currentLine.charAt(currentLine.length() - 1)) {
// currentLine = currentLine.substring(0, currentLine.length() - 1);
// }

        return currentLine;
    }


    /**
     * Given a clean command line instruction, this method parses the necessary
     * information and directs our our next update to the review database.
     * 
     * @param cmd
     *            command line being input
     * @return if a command was realized
     */
    public boolean checkCommand(String cmd) {

        // print ___
        if (cmd.contains("print hashtable")) {
            // print movie hashtable
            if (cmd.contains("movie")) {
                database.printMovie();
                return true;
            }
            // print reviewer hashtable
            else if (cmd.contains("reviewer")) {
                database.printReview();
                return true;
            }
        }
        else if (cmd.contains("list")) {
            //list movie
            if (cmd.contains("movie")) {
                
                return true;
            }
            //list reviewer
            else if (cmd.contains("reviewer")) {
                
                return true;
            }
        }
        else if (cmd.contains("similar")) {
            return true;
        }
        // add/delete
        else {
            // add
            if (cmd.contains("add")) {
                cmd = cmd.replace("add ", "");
                database.add(cmd);
                return true;

            }
            // delete
            else if (cmd.contains("delete reviewer")) {
                cmd = cmd.replace("delete reviewer ", "");
                database.deleteReviewer(cmd);
                return true;
            }
            else if (cmd.contains("delete movie")) {
                cmd = cmd.replace("delete movie ", "");
                database.deleteMovie(cmd);
                return true;
            }
        }

        return false;
    }
}
