/**
 * The aim of this project is to create a movie review storage system that
 * allows the user to look up reviews based on either movie name or reviewer
 * name.
 */

/**
 * The class containing the main method.
 *
 * @author Dasha Savina (dasha8)
 * @version 09.18.18
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class MovieReview {

    private static CommandParser parser;


    /**
     * @param args
     *            Command line parameters
     */
    public static void main(String[] args) {

        if (args.length != 2) {

            System.out.println(
                "Incorrect Input, the input paramaters need to be as follows:");
            System.out.println(
                "java MovieReview {initial-hash-size} {command-file}");
        }

        int hashSize = Integer.parseInt(args[0]);
        String fileName = args[1];

        parser = new CommandParser(fileName, hashSize);
        parser.readFile();
    }
}
