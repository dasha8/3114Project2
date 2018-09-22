import student.TestCase;

/**
 * @author Dasha Savina (dasha8)
 * @version 09.22.18
 */
public class MovieReviewTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }

    /**
     * Get code coverage of the class declaration.
     */
    public void testRInit() {
        MovieReview recstore = new MovieReview();
        assertNotNull(recstore);
        //MovieReview.main(null);
    }
}
