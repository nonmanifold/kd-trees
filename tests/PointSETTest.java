import edu.princeton.cs.algs4.Point2D;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    public void NearestTest() {
        PointSET instance = DataLoader.loadIntoPointSet("data/circle10.txt");
        assertEquals(new Point2D(0.975528, 0.345492), instance.nearest(new Point2D(0.81, 0.30)));

        PointSET instancek = DataLoader.loadIntoPointSet("data/circle10k.txt");
        assertEquals(new Point2D(0.500000, 1.000000), instancek.nearest(new Point2D(0.81, 0.30)));
    }
}