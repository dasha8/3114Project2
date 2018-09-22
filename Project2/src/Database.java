/**
 * The Database class is where input file commands are sent and acted upon. This
 * class creates the data structures that will store our movie reviews.
 * 
 * @author Dasha Savina (dasha8)
 * @version 09.18.18
 *
 */
public class Database {

    private Hash movieHT;
    private Hash reviewerHT;


    /**
     * Constructs a Database, which contains a hash table for storing reviews.
     * 
     * @param initialHashSize
     *            initial hash table size passed as argument
     */
    public Database(int initialHashSize) {
        movieHT = new Hash(initialHashSize);
        reviewerHT = new Hash(initialHashSize);
    }


    /**
     * Adds a new value to a hash table.
     * 
     * @param command
     *            the input command which contains the value to be added
     */
    public void add(String command) {

        String[] list = command.split("<SEP>");
        for (int i = 0; i < list.length; i++) {
            list[i] = list[i].trim();
        }
        
        if (Integer.parseInt(list[2]) > 10 || Integer.parseInt(list[2]) < 1) {
            System.out.println("Bad score |" + list[2] + "|. Scores must be between 1 and 10.");
            return;
        }
        
        if (!reviewerHT.inTable(list[0])) {
            Record newRec = new Record(list[0]);
            reviewerHT.hashInsert(newRec);
        }

        if (!movieHT.inTable(list[1])) {
            Record newRec = new Record(list[1]);
            movieHT.hashInsert(newRec);
        }

        System.out.println("Rating added: |" + list[0] + "|, |" + list[1]
            + "|, " + list[2]);

    }


    public void deleteReviewer(String command) {
        if (reviewerHT.inTable(command)) {
            reviewerHT.hashDelete(command);
            System.out.println("|" + command
                + "| has been deleted from the Reviewer database.");
        }
        else {
            System.out.println("|" + command + "| not deleted because it does not exist in the Reviewer database.");
        }
    }


    public void deleteMovie(String command) {
        if (movieHT.inTable(command)) {
            movieHT.hashDelete(command);
            System.out.println("|" + command
                + "| has been deleted from the Movie database.");
        }
        else {
            System.out.println("|" + command + "| not deleted because it does not exist in the Movie database.");
        }
    }


    public void printMovie() {
        System.out.println("Movies:");
        movieHT.printHash();
    }


    public void printReview() {
        System.out.println("Reviewers:");
        reviewerHT.printHash();
    }
}
