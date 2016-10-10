import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private int size=0;

    private static class Node {
        private Point2D p;      // the point
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        private Node(Point2D p) {
            this.p = p;
        }
    }

    private Node root = null;

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x, int acc) {
        if (x == null)
            return acc;
        else {
            acc = size(x.lb, acc);
            acc = size(x.rt, acc);
            return 1 + acc;
        }
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        root = put(root, p, true);
    }

    private double compare(Node x, Point2D p, boolean isX) {
        double cmp;

        if (isX) {
            // x
            cmp = p.x() - x.p.x();
        } else {
            // y
            cmp = p.y() - x.p.y();
        }
        return cmp;
    }

    private Node put(Node x, Point2D p, boolean isX) {
        if (x == null) {
            size++;
            return new Node(p);
        }
        double cmp = compare(x, p, isX);

        if (cmp < 0)
            x.lb = put(x.lb, p, !isX);
        else if (cmp > 0)
            x.rt = put(x.rt, p, !isX);
        else if (!x.p.equals(p)) {
            x.rt = put(x.rt, p, !isX);
        }
        return x;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        return p.equals(get(root, p, true));
    }

    private Point2D get(Node x, Point2D p, boolean isX) {
        if (x == null)
            return null;
        double cmp = compare(x, p, isX);
        if (x.p.equals(p)) {
            return p;
        }
        if (cmp < 0)
            return get(x.lb, p, !isX);
        else
            return get(x.rt, p, !isX);
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        RectHV rect = new RectHV(0, 0, 1, 1);

        draw(root, rect, true);
    }

    private void draw(Node x, RectHV rect, boolean isX) {
        if (x == null) {
            return;
        }
        StdDraw.setPenRadius();
        if (isX) {
            // x
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), rect.ymin(), x.p.x(), rect.ymax());

            if (x.lb != null) {
                draw(x.lb, new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax()), false);
            }
            if (x.rt != null) {
                draw(x.rt, new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax()), false);
            }
        } else {
            // y
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(rect.xmin(), x.p.y(), rect.xmax(), x.p.y());

            if (x.lb != null) {
                draw(x.lb, new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax()), true);

            }
            if (x.rt != null) {
                draw(x.rt, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y()), true);
            }
        }

        // draw point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        x.p.draw();
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        return p;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
