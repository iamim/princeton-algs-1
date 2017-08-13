import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class KdTree {

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
        if (p == null)
            throw new IllegalArgumentException();

        if (contains(p))
            return;

        nOfNodes += 1;

        root = insertHelper(VERT, root, p, 0, 0, 1, 1);
    }

    private Node insertHelper(boolean orient, Node parent, Point2D p, double xmin, double ymin,
            double xmax, double ymax) {
        if (parent == null)
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax));

        if (orient == VERT) {
            if (p.x() < parent.p.x())
                parent.lb = insertHelper(HORIZ, parent.lb, p, xmin, ymin, parent.p.x(), ymax);
            else
                parent.rt = insertHelper(HORIZ, parent.rt, p, parent.p.x(), ymin, xmax, ymax);
        }
        else {
            if (p.y() < parent.p.y())
                parent.lb = insertHelper(VERT, parent.lb, p, xmin, ymin, xmax, parent.p.y());
            else
                parent.rt = insertHelper(VERT, parent.rt, p, xmin, parent.p.y(), xmax, ymax);
        }

        return parent;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        return containsHelper(VERT, root, p);
    }

    private static boolean containsHelper(boolean orient, Node parent, Point2D p) {
        if (parent == null)
            return false;

        if (parent.p.equals(p))
            return true;

        if (orient == VERT) {
            if (p.x() < parent.p.x())
                return containsHelper(HORIZ, parent.lb, p);
            else
                return containsHelper(HORIZ, parent.rt, p);
        }
        else {
            if (p.y() < parent.p.y())
                return containsHelper(VERT, parent.lb, p);
            else
                return containsHelper(VERT, parent.rt, p);
        }
    }

    // draw all points to standard draw
    public void draw() {
        drawNode(VERT, root);
    }

    private static void drawNode(boolean orient, Node node) {
        if (node == null)
            return;

        // draw point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);

        StdDraw.point(node.p.x(), node.p.y());

        // draw line
        StdDraw.setPenRadius();
        if (orient == VERT) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }

        // draw children nodes
        drawNode(!orient, node.lb);
        drawNode(!orient, node.rt);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();

        return rangeHelper(VERT, root, new LinkedList<>(), rect);
    }

    private static List<Point2D> rangeHelper(boolean orient, Node parent, List<Point2D> list, RectHV rect) {
        if (parent == null || !parent.rect.intersects(rect)) return list;

        if (rect.contains(parent.p))
            list.add(parent.p);

        if (orient == VERT) {
            if (rect.xmin() < parent.p.x())
                rangeHelper(HORIZ, parent.lb, list, rect);

            if (rect.xmax() >= parent.p.x())
                rangeHelper(HORIZ, parent.rt, list, rect);
        }
        else {
            if (rect.ymin() < parent.p.y())
                rangeHelper(VERT, parent.lb, list, rect);

            if (rect.ymax() >= parent.p.y())
                rangeHelper(VERT, parent.rt, list, rect);
        }

        return list;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        return nearestHelper(VERT, root, p, root.p);
    }

    private static Point2D nearestHelper(boolean orient, Node parent, Point2D target, Point2D favorite) {
        if (parent == null)
            return favorite;

        if (parent.p.distanceSquaredTo(target) < favorite.distanceSquaredTo(target))
            favorite = parent.p;

        if (orient == VERT) {
            Node first = target.x() < parent.p.x() ? parent.lb : parent.rt;
            Node second = target.x() < parent.p.x() ? parent.rt : parent.lb;

            favorite = nearestHelper(HORIZ, first, target, favorite);

            if (second != null
                    && favorite.distanceSquaredTo(target) > second.rect.distanceSquaredTo(target))
                favorite = nearestHelper(HORIZ, second, target, favorite);
        }
        else {
            Node first = target.y() < parent.p.y() ? parent.lb : parent.rt;
            Node second = target.y() < parent.p.y() ? parent.rt : parent.lb;

            favorite = nearestHelper(VERT, first, target, favorite);

            if (second != null
                    && favorite.distanceSquaredTo(target) > second.rect.distanceSquaredTo(target))
                favorite = nearestHelper(VERT, second, target, favorite);
        }

        return favorite;
    }

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }
}
