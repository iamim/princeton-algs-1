import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private enum ORIENT {
        VERT,
        HORIZ
    }
    private static boolean VERT = true;
    private static boolean HORIZ = false;

    private Node root;
    private int nOfNodes;

    // construct an empty set of points
    public KdTree() {
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return nOfNodes;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        if (contains(p)) return;

        root = insert(VERT, root, p);
    }

    // If too complicated check for contains first
    private Node insert(boolean orient, Node parent, Point2D p) {
        if (parent == null) return new Node(p);

        if (orient == VERT) {
            if (p.x() < parent.p.x())
                parent.lb = insert(HORIZ, parent.lb, p);
            else
                parent.rt = insert(HORIZ, parent.rt, p);
        }
        else {
            if (p.y() < parent.p.y())
                parent.lb = insert(VERT, parent.lb, p);
            else
                parent.rt = insert(VERT, parent.rt, p);
        }

        return parent;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return contains(VERT, root, p);
    }

    private boolean contains(boolean orient, Node parent, Point2D p) {
        if (parent == null) return false;

        if (parent.p.equals(p)) return true;

        if (orient == VERT) {
            if (p.x() < parent.p.x())
                return contains(HORIZ, parent.lb, p);
            else
                return contains(HORIZ, parent.rt, p);
        }
        else {
            if (p.y() < parent.p.y())
                return contains(VERT, parent.lb, p);
            else
                return contains(VERT, parent.rt, p);
        }
    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        return null;
    }

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;

        public Node(Point2D p) {
            this.p = p;
        }
    }
}
