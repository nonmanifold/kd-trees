import edu.princeton.cs.algs4.Point2D;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DataLoaderTest {
    @Test
    public void loadTest() {
        ArrayList<Point2D> points = DataLoader.load("data/circle4.txt");
        assertArrayEquals(new Point2D[]{
                new Point2D(0.0, 0.5),
                new Point2D(0.5, 1.0),
                new Point2D(0.5, 0.0),
                new Point2D(1.0, 0.5),
        }, points.toArray());
    }
}
