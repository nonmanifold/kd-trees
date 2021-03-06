import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class KdTreeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void ShouldThrowNPEOnInsertNull() {
        KdTree instance = new KdTree();
        // throw a java.lang.NullPointerException if passed a null argument
        thrown.expect(NullPointerException.class);
        instance.insert(null);
    }

    @Test
    public void ShouldThrowNPEOnContainsNull() {
        KdTree instance = new KdTree();
        // throw a java.lang.NullPointerException if passed a null argument
        thrown.expect(NullPointerException.class);
        instance.contains(null);
    }

    @Test
    public void ShouldThrowNPEOnRangeNull() {
        KdTree instance = new KdTree();
        // throw a java.lang.NullPointerException if passed a null argument
        thrown.expect(NullPointerException.class);
        instance.range(null);
    }

    @Test
    public void ShouldThrowNPEOnNearestNull() {
        KdTree instance = new KdTree();
        // throw a java.lang.NullPointerException if passed a null argument
        thrown.expect(NullPointerException.class);
        instance.nearest(null);
    }

    @Test
    public void isEmptyTest() {
        KdTree empty = new KdTree();
        assertTrue(empty.isEmpty());
        KdTree circle10 = DataLoader.loadIntoKDTree("data/circle10.txt");
        assertFalse(circle10.isEmpty());
    }

    @Test
    public void SizeTestG() {
        Point2D[] ps = new Point2D[]{
                new Point2D(3, 6),
                new Point2D(17, 15),
                new Point2D(13, 15),
                new Point2D(6, 12),
                new Point2D(9, 1),
                new Point2D(2, 7),
                new Point2D(10, 19),
                new Point2D(10, 19)
        };

        KdTree tree = new KdTree();
        int count = 0;
        for (Point2D p : ps) {
            count++;
            tree.insert(p);
            assertTrue(tree.contains(p));
        }
        assertEquals(count - 1, tree.size());
    }

    @Test
    public void SizeTest() {
        KdTree tree = new KdTree();
        assertEquals(0, tree.size());
        tree.insert(new Point2D(0, 0));
        assertEquals(1, tree.size());
        tree.insert(new Point2D(0, 0));
        tree.insert(new Point2D(0, 0));
        assertEquals(1, tree.size());
        tree.insert(new Point2D(1, 0));
        assertEquals(2, tree.size());

        ArrayList<Point2D> points = DataLoader.load("data/circle10.txt");
        KdTree circle10 = new KdTree();
        int count = 0;
        for (Point2D p : points) {
            assertEquals(count, circle10.size());
            count++;
            circle10.insert(p);
            assertTrue(circle10.contains(p));
        }
        assertEquals(count, circle10.size());
    }

    @Test
    public void ContainsTest() {
        KdTree empty = new KdTree();
        assertFalse(empty.contains(new Point2D(0.5, 0.5)));
        KdTree circle10 = DataLoader.loadIntoKDTree("data/circle10.txt");
        assertFalse(circle10.contains(new Point2D(0.5, 0.5)));
        circle10.insert(new Point2D(0.5, 0.5));
        assertTrue(circle10.contains(new Point2D(0.5, 0.5)));
    }

    @Test
    public void RangeEmptyTest() {
        KdTree empty = new KdTree();

        ArrayList<Point2D> emptyRange = new ArrayList<>();
        for (Point2D p : empty.range(new RectHV(0.0, 0.0, 1.0, 1.0))) {
            emptyRange.add(p);
        }
        assertEquals(0, emptyRange.size());
    }

    @Test
    public void RangeSimpleTest() {

        KdTree simple = new KdTree();
        simple.insert(new Point2D(0.5, 0.5));
        simple.insert(new Point2D(0.2, 0.2));
        ArrayList<Point2D> simpleRange = new ArrayList<>();
        for (Point2D p : simple.range(new RectHV(0.0, 0.0, 1.0, 1.0))) {
            simpleRange.add(p);
        }
        assertEquals(2, simpleRange.size());
    }

    @Test
    public void RangeAllTest() {
        KdTree circle10 = DataLoader.loadIntoKDTree("data/circle10.txt");

        ArrayList<Point2D> range = new ArrayList<>();
        for (Point2D p : circle10.range(new RectHV(0.0, 0.0, 1.0, 1.0))) {
            range.add(p);
        }
        assertEquals(10, range.size());
    }

    @Test
    public void RangePartTest() {

        Point2D[] ps = new Point2D[]{
                new Point2D(0.7, 0.2),
                new Point2D(0.5, 0.4),
                new Point2D(0.2, 0.3),
                new Point2D(0.4, 0.7),
                new Point2D(0.9, 0.6)
        };

        KdTree tree = new KdTree();
        int count = 0;
        for (Point2D p : ps) {
            count++;
            tree.insert(p);
        }

        ArrayList<Point2D> rangeReduced = new ArrayList<>();
        for (Point2D p : tree.range(new RectHV(0.0, 0.0, 0.01, 1.0))) {
            rangeReduced.add(p);
        }
        assertEquals(0, rangeReduced.size());

        rangeReduced = new ArrayList<>();
        for (Point2D p : tree.range(new RectHV(0.0, 0.0, 1.0, 0.01))) {
            rangeReduced.add(p);
        }
        assertEquals(0, rangeReduced.size());


        rangeReduced = new ArrayList<>();
        for (Point2D p : tree.range(new RectHV(0.99, 0.0, 1.0, 0.0))) {
            rangeReduced.add(p);
        }
        assertEquals(0, rangeReduced.size());

        rangeReduced = new ArrayList<>();
        for (Point2D p : tree.range(new RectHV(0, 0.99, 0.0, 0.99))) {
            rangeReduced.add(p);
        }
        assertEquals(0, rangeReduced.size());
    }

    @Test
    public void NearestTest() {
        KdTree empty = new KdTree();
        assertEquals(null, empty.nearest(new Point2D(0.5, 0.5)));
        KdTree circle10 = DataLoader.loadIntoKDTree("data/circle10.txt");
        assertEquals(new Point2D(0.975528, 0.345492), circle10.nearest(new Point2D(0.81, 0.30)));

        KdTree circle10k = DataLoader.loadIntoKDTree("data/circle10k.txt");
        assertEquals(new Point2D(0.76125, 0.317125), circle10k.nearest(new Point2D(0.81, 0.30)));
    }
}