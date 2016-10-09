import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class KdTreeTest {
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
}