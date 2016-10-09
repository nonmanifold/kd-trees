import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;

import java.util.ArrayList;

public class DataLoader {
    public static ArrayList<Point2D> load(String filename) {
        In in = new In(filename);
        ArrayList<Point2D> points = new ArrayList<>();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            points.add(p);
        }
        return points;
    }

    public static KdTree loadIntoKDTree(String filename) {
        KdTree tree = new KdTree();
        ArrayList<Point2D> points = load(filename);
        for (Point2D p : points) {
            tree.insert(p);
        }
        return tree;
    }

    public static PointSET loadIntoPointSet(String filename) {
        PointSET tree = new PointSET();
        ArrayList<Point2D> points = load(filename);
        for (Point2D p : points) {
            tree.insert(p);
        }
        return tree;
    }
}
