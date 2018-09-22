import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test the hash function
 *
 * @author CS3114 staff
 * @version August, 2018
 */
public class HashTest extends TestCase {

    private Hash ht;
    private Record r;
    private Record r2;
    private Record r3;
    private Record r4;
    private Record r5;
    private Record r6;
    private Record r7;
    private Record rCopy;


    /**
     * useful notes for testing:
     * the hash function returns the following values for 10 random inputs
     * (given a size 5 and 10 and 20 hash table)
     * test - 3 - 8 - 8
     * basket - 4 - 9 - 19
     * apple - 0 - 0 - 10
     * banana - 4 - 4 - 4
     * death note - 3 - 8 - 8
     * turkey 1 - 1 - 1
     * reddi - 5 - 15
     */

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        ht = new Hash(10);
        r = new Record("test");
        rCopy = new Record("test");
        r2 = new Record("basket");
        r3 = new Record("death note");
        r4 = new Record("apple");
        r5 = new Record("banana");
        r6 = new Record("turkey");
        r7 = new Record("reddi");
// System.out.println(ht.h("Death Note", 6));
// System.out.println(ht.h("Twin Peaks Returns", 6));
// System.out.println(ht.h("basket", 5));
// System.out.println(ht.h("death note", 5));
// System.out.println(ht.h("monkeym", 10));
// System.out.println(ht.h("apple", 5));
// System.out.println(ht.h("banana", 5));
// System.out.println(ht.h("death note", 5));
// System.out.println(ht.h("turkey", 5));
    }


    /**
     * tests doubling the hash table size twice
     */
    public void testDoubleTwice() {
        

        System.out.println(ht.h("Death Note", 6));
        System.out.println(ht.h("Twin Peaks Returns", 6));
        
        Hash test = new Hash(5);
        //assertFalse(test.hashDelete("v"));
        test.hashInsert(r);
        test.hashInsert(r2);
        test.hashInsert(r3);
        test.inTable("death note");
        assertEquals(10, test.getSize());
        assertEquals(r, test.getRecordAt(8));
        assertEquals(r2, test.getRecordAt(9));
        assertEquals(r3, test.getRecordAt(2));
        Record another = new Record("monkeym");
        test.hashInsert(another);
        assertEquals(another, test.getRecordAt(7));
    }


    /**
     * Tests if the key has a record in the table
     */
    public void testInTable() {
        Hash test = new Hash(5);
        test.hashInsert(r);
        assertTrue(test.inTable("test"));
        assertFalse(test.inTable("test2"));

    }


    /**
     * Tests that hashsearch2 is correctly working
     */
    public void testHashSearch2() {
        Hash test = new Hash(5);
        test.hashInsert(r);
        test.hashSearch2("test");

        test.hashSearch2("test2");
        assertTrue(test.inTable("test"));

    }


    /**
     * Tests that the insert works correctly
     */
    public void testHashInsertandDelete() {

        ht.hashInsert(r);
        ht.hashInsert(r2);
        ht.hashInsert(r3);
        ht.hashInsert(r4);
        assertEquals(r, ht.getRecordAt(8));
        assertEquals(r2, ht.getRecordAt(9));
        assertEquals(r3, ht.getRecordAt(2));
        assertEquals(r4, ht.getRecordAt(0));
        assertFalse(ht.hashInsert(rCopy));

        //assertFalse(ht.hashDelete("not here"));

        ht.hashInsert(r5);
        // test doubling in size
        ht.hashInsert(r6);
        assertEquals(20, ht.getSize());

        assertEquals(r, ht.getRecordAt(9));
        assertEquals(r2, ht.getRecordAt(19));
        assertEquals(r3, ht.getRecordAt(8));
        assertEquals(r4, ht.getRecordAt(10));
        assertEquals(r5, ht.getRecordAt(4));
        assertEquals(r6, ht.getRecordAt(1));

        assertEquals(r6, ht.hashSearch("turkey"));
        assertEquals(r, ht.hashSearch("test"));

        //assertTrue(ht.hashDelete("death note"));
        //assertTrue(ht.getRecordAt(8).isEmpty());

        ht.hashInsert(r7);
        ht.hashDelete("reddi");
        ht.hashDelete("reddi");

        ht.hashDelete("death note");
        ht.hashDelete("apple");
        ht.hashDelete("banana");
        ht.hashDelete("turkey");
        ht.hashDelete("reddi");
        ht.hashInsert(r2);
        assertEquals(r2, ht.hashSearch2("basket"));
        assertNull(ht.hashSearch2("death note"));
        assertNull(ht.hashSearch2("apple"));
        assertNull(ht.hashSearch2("banana"));

    }


    /**
     * tests deleting and reinserting a record
     */
    public void testDeleteAndReinsert() {
        ht.hashInsert(r);
        ht.hashInsert(r);
        ht.hashDelete("test");

        ht.hashDelete("test");
        //assertFalse(ht.hashDelete("asdaS"));
        assertTrue(ht.getRecordAt(8).isEmpty());
        r = new Record("test");
        ht.hashInsert(r);
    }


    /**
     * tests collisions between record insertions
     */
    public void testCollisions() {
        Record c1 = new Record("test");
        Record c2 = new Record("death note");
        Record c3 = new Record("monkeym");

        ht.hashInsert(c1);
        ht.hashInsert(c2);
        ht.hashInsert(c3);

        assertEquals(c1, ht.getRecordAt(8));
        assertEquals(c2, ht.getRecordAt(9));
        assertEquals(c3, ht.getRecordAt(2));

        Record c3Copy = new Record("monkeym");
        assertFalse(ht.hashInsert(c3Copy));

    }

}
