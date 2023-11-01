package org.cis120;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Use this file to test your implementation of Pixel.
 *
 * We will manually grade this file to give you feedback
 * about the completeness of your test cases.
 */

public class MyPixelTest {

    /*
     * Remember, UNIT tests should ideally have one point of failure. Below we
     * give you two examples of unit tests for the Pixel constructor, one that
     * takes in three ints as arguments and one that takes in an array. We use
     * the getRed(), getGreen(), and getBlue() methods to check that the values
     * were set correctly. These two tests do not comprehensively test all of
     * Pixel so you must add your own.
     *
     * You might want to look into assertEquals, assertTrue, assertFalse, and
     * assertArrayEquals at the following:
     * http://junit.sourceforge.net/javadoc/org/junit/Assert.html
     *
     * Note, if you want to add global variables so that you can reuse Pixels
     * in multiple tests, feel free to do so.
     */

    @Test
    public void testConstructInBounds() {
        Pixel p = new Pixel(40, 50, 60);
        assertEquals(40, p.getRed());
        assertEquals(50, p.getGreen());
        assertEquals(60, p.getBlue());
    }

    @Test
    public void testConstructArrayLongerThan3() {
        int[] arr = { 10, 20, 30, 40 };
        Pixel p = new Pixel(arr);
        assertEquals(10, p.getRed());
        assertEquals(20, p.getGreen());
        assertEquals(30, p.getBlue());
    }

    /* ADD YOUR OWN TESTS BELOW */

    @Test
    public void testConstructArrayShorterThan3() {
        int[] arr = {10, 20};
        Pixel p = new Pixel(arr);
        assertEquals(10, p.getRed());
        assertEquals(20, p.getGreen());
        assertEquals(0, p.getBlue());
    }

    @Test
    public void testConstructArrayOutsideBounds() {
        Pixel p = new Pixel(-40, 50, 60);
        assertEquals(0, p.getRed());
        assertEquals(50, p.getGreen());
        assertEquals(60, p.getBlue());
    }

    @Test
    public void testNull() {
        Pixel p = new Pixel(null);
        assertEquals(0, p.getRed());
        assertEquals(0, p.getGreen());
        assertEquals(0, p.getBlue());
    }

    //tests for getComponents
    @Test
    public void testGetComponents() {
        Pixel p = new Pixel(10,20,30);
        int[] c = p.getComponents();
        assertEquals(c[0], 10);
        assertEquals(c[1], 20);
        assertEquals(c[2], 30);
    }

    //test distances
    @Test
    public void testNoDistance() {
        Pixel a = new Pixel(10,20,30);
        Pixel b = new Pixel(10,20,30);

        int dis = b.distance(a);
        assertEquals(0, dis);
    }

    @Test
    public void testNullDistance() {
        Pixel p = new Pixel(10,20,30);
        int dis = p.distance(null);
        assertEquals(-1, dis);
    }

    @Test
    public void testActualDistance() {
        Pixel a = new Pixel(10,20,30);
        Pixel b = new Pixel(5, 10, 60);
        assertEquals(45, a.distance(b));
    }

    //testString
    @Test
    public void testString() {
        Pixel p = new Pixel(10,20,30);
        String a = "(10,20,30)";
        a.equals(p.toString());
    }

    @Test
    public void testEquals() {
        Pixel a = new Pixel(10,20,30);
        Pixel b = new Pixel(10,20,30);
        assertTrue(a.equals(b));
    }

    @Test
    public void testNotEquals() {
        Pixel a = new Pixel(10,20,30);
        Pixel b = new Pixel(1,2,3);
        assertFalse(a.equals(b));
    }

}
