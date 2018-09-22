import student.TestCase;

/**
 * This class tests Database.
 * 
 * @author Dasha Savina (dasha8)
 * @version 09.22.18
 */
public class DatabaseTest extends TestCase {

    private Database db; 
    private String sa;
    private String dn;
    private String tpr;
    
    
    public void setUp() {
        db = new Database(6);
        sa = "Name1<SEP>Spirited Away<SEP>5";
        dn = "Name2<SEP>Death Note<SEP>5";
        tpr = "Name3<SEP>Twin Peaks Returns<SEP>5";
    }
    
    public void testAddDelete() {
        db.add(sa);
        db.add(dn);
        db.add(tpr);
        db.printMovie();
    }
}
