/******************************************************************************
 *  Compilation:  javac KdTreeVisualizer.java
 *  Execution:    java KdTreeVisualizer
 *  Dependencies: KdTree.java
 *
 *  Add the points that the user clicks in the standard draw window
 *  to a kd-tree and draw the resulting kd-tree.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

public class KdTreeVisualizer {
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
    public static void main(String[] args) {
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        KdTree kdtree = new KdTree();
        kdtree =loadIntoKDTree("data/circle10.txt");
        StdDraw.clear();
        kdtree.draw();
        StdDraw.show();
        while (true) {
            if (StdDraw.mousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                StdOut.printf("%8.6f %8.6f\n", x, y);
                Point2D p = new Point2D(x, y);
                if (rect.contains(p)) {
                    StdOut.printf("%8.6f %8.6f\n", x, y);
                    kdtree.insert(p);
                    StdDraw.clear();
                    kdtree.draw();
                    StdDraw.show();
                }
            }
            StdDraw.pause(50);
        }

    }

    private static class DataLoader {
    }
}
