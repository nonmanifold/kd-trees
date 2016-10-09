import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PointSETTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void ShouldThrowNPEOnInsertNull() {
        PointSET instance = new PointSET();
        // throw a java.lang.NullPointerException if passed a null argument
        thrown.expect(NullPointerException.class);
        instance.insert(null);
    }

    @Test
    public void ShouldThrowNPEOnContainsNull() {
        PointSET instance = new PointSET();
        // throw a java.lang.NullPointerException if passed a null argument
        thrown.expect(NullPointerException.class);
        instance.contains(null);
    }

    @Test
    public void ShouldThrowNPEOnRangeNull() {
        PointSET instance = new PointSET();
        // throw a java.lang.NullPointerException if passed a null argument
        thrown.expect(NullPointerException.class);
        instance.range(null);
    }

    @Test
    public void ShouldThrowNPEOnNearestNull() {
        PointSET instance = new PointSET();
        // throw a java.lang.NullPointerException if passed a null argument
        thrown.expect(NullPointerException.class);
        instance.nearest(null);
    }

    @Test
    public void isEmptyTest() {
        PointSET empty = new PointSET();
        assertTrue(empty.isEmpty());
        PointSET circle10 = DataLoader.loadIntoPointSet("data/circle10.txt");
        assertFalse(circle10.isEmpty());
    }

    @Test
    public void SizeTest() {
        PointSET empty = new PointSET();
        assertEquals(0, empty.size());
        PointSET circle10 = DataLoader.loadIntoPointSet("data/circle10.txt");
        assertEquals(10, circle10.size());
    }

    @Test
    public void ContainsTest() {
        PointSET empty = new PointSET();
        assertFalse(empty.contains(new Point2D(0.5, 0.5)));
        PointSET circle10 = DataLoader.loadIntoPointSet("data/circle10.txt");
        assertFalse(circle10.contains(new Point2D(0.5, 0.5)));
        circle10.insert(new Point2D(0.5, 0.5));
        assertTrue(circle10.contains(new Point2D(0.5, 0.5)));
    }

    @Test
    public void RangeTest() {
        PointSET empty = new PointSET();

        ArrayList<Point2D> emptyRange = new ArrayList<>();
        for (Point2D p : empty.range(new RectHV(0.0, 0.0, 1.0, 1.0))) {
            emptyRange.add(p);
        }
        assertEquals(0, emptyRange.size());

        PointSET circle10 = DataLoader.loadIntoPointSet("data/circle10.txt");

        ArrayList<Point2D> range = new ArrayList<>();
        for (Point2D p : circle10.range(new RectHV(0.0, 0.0, 1.0, 1.0))) {
            range.add(p);
        }
        assertEquals(10, range.size());

        ArrayList<Point2D> rangeReduced = new ArrayList<>();
        for (Point2D p : circle10.range(new RectHV(0.0, 0.0, 0.024472 , 1.0))) {
            rangeReduced.add(p);
        }
        assertEquals(2, rangeReduced.size());
    }

    @Test
    public void NearestTest() {
        PointSET empty = new PointSET();
        assertEquals(null, empty.nearest(new Point2D(0.5, 0.5)));
        PointSET circle10 = DataLoader.loadIntoPointSet("data/circle10.txt");
        assertEquals(new Point2D(0.975528, 0.345492), circle10.nearest(new Point2D(0.81, 0.30)));

        PointSET circle10k = DataLoader.loadIntoPointSet("data/circle10k.txt");
        assertEquals(new Point2D(0.76125, 0.317125), circle10k.nearest(new Point2D(0.81, 0.30)));
    }
}