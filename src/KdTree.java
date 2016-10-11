import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private int size = 0;

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
                draw(x.lb, new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax()), false);
            }
            if (x.rt != null) {
                draw(x.rt, new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax()), false);
            }
        } else {
            // y
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(rect.xmin(), x.p.y(), rect.xmax(), x.p.y());

            if (x.lb != null) {
                draw(x.lb, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y()), true);
            }
            if (x.rt != null) {
                draw(x.rt, new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax()), true);
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
        ArrayList<Point2D> range = new ArrayList<>();
        search(range, root, new RectHV(0, 0, 1, 1), true, rect);
        return range;
    }

    private void search(ArrayList<Point2D> range, Node x, RectHV nodeRect, boolean isX, RectHV rect) {
        if (x == null) {
            return;
        }
        if (!nodeRect.intersects(rect)) {
            return;
        }
        if (rect.contains(x.p)) {
            range.add(x.p);
        }

        if (isX) {
            // x
            if (x.lb != null) {
                search(range, x.lb, new RectHV(nodeRect.xmin(), nodeRect.ymin(), x.p.x(), nodeRect.ymax()), false, rect);
            }
            if (x.rt != null) {
                search(range, x.rt, new RectHV(x.p.x(), nodeRect.ymin(), nodeRect.xmax(), nodeRect.ymax()), false, rect);
            }
        } else {
            // y
            if (x.lb != null) {
                search(range, x.lb, new RectHV(nodeRect.xmin(), nodeRect.ymin(), nodeRect.xmax(), x.p.y()), true, rect);
            }
            if (x.rt != null) {
                search(range, x.rt, new RectHV(nodeRect.xmin(), x.p.y(), nodeRect.xmax(), nodeRect.ymax()), true, rect);
            }
        }

    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (size == 0) {
            return null;
        }
        return p;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
