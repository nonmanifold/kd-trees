import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
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
        if (isEmpty()) {
            return 0;
        } else {
            return 1 + size(root);
        }
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return 1 + size(x.lb) + size(x.rt);
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        root = put(root, p, 0);
    }

    private double compare(Node x, Point2D p, int depth) {
        double cmp;

        if (depth % 2 == 0) {
            // x
            cmp = x.p.x() - p.x();
        } else {
            // y
            cmp = x.p.y() - p.y();
        }
        return cmp;
    }

    private Node put(Node x, Point2D p, int depth) {
        if (x == null)
            return new Node(p);
        double cmp = compare(x, p, depth);

        if (cmp < 0)
            x.lb = put(x.lb, p, depth + 1);
        else if (cmp > 0)
            x.rt = put(x.rt, p, depth + 1);
        else
            x.p = p;
        return x;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        return get(p) != null;
    }

    private Point2D get(Point2D key) {
        return get(root, key, 0);
    }

    private Point2D get(Node x, Point2D p, int depth) {
        if (x == null)
            return null;
        double cmp = compare(x, p, depth);
        if (cmp < 0)
            return get(x.lb, p, depth + 1);
        else if (cmp > 0)
            return get(x.rt, p, depth + 1);
        else
            return x.p;
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        RectHV rect = new RectHV(0, 0, 1, 1);

        draw(root, rect, 0);
    }

    private void draw(Node x, RectHV rect, int depth) {
        if (x == null) {
            return;
        }
        StdDraw.setPenRadius();
        if (depth % 2 == 0) {
            // x
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), rect.ymin(), x.p.x(), rect.ymax());

            if (x.lb != null) {
                draw(x.lb, new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax()), depth + 1);
            }
            if (x.rt != null) {
                draw(x.rt, new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax()), depth + 1);
            }
        } else {
            // y
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(rect.xmin(), x.p.y(), rect.xmax(), x.p.y());

            if (x.lb != null) {
                draw(x.lb, new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax()), depth + 1);

            }
            if (x.rt != null) {
                draw(x.rt, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y()), depth + 1);
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
