import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {

//    private List<LineSegment> segments = new LinkedList<>();
    private List<ProtoSegment> protoSegments = new LinkedList<>();

    public FastCollinearPoints(Point[] input) {
        if (input == null)
            throw new IllegalArgumentException("The input shouldn't be null");

        // Protective copying
        Point[] points = Arrays.copyOf(input, input.length);

        if (hasNull(points))
            throw new IllegalArgumentException("The input contains null values");

        if (sortAndFindDuplicates(points))
            throw new IllegalArgumentException("The input should not contain duplicates or nulls");

        if (points.length < 4)
            return;

        for (Point basePoint : points) {
            Arrays.sort(points, basePoint.slopeOrder()); // n^2 * lg(n)

            double previousSlope = Double.NEGATIVE_INFINITY;
            int nWithSameSlope = 1;
            for (int j = 1; j < points.length; j++) {
                Point jthPoint = points[j];

                double jthSlope = basePoint.slopeTo(jthPoint);
                if (jthSlope == previousSlope) {
                    nWithSameSlope++;
                }
                else {
                    if (nWithSameSlope > 2) {
                        addSegment(basePoint, Arrays.copyOfRange(points, j - nWithSameSlope, j));
                    }

                    nWithSameSlope = 1;
                }

                previousSlope = jthSlope;
            }

            if (nWithSameSlope > 2) {
                addSegment(basePoint,
                        Arrays.copyOfRange(points, points.length - nWithSameSlope, points.length));
            }
        }
    }

    public int numberOfSegments() {
        return protoSegments.size();
    }

    public LineSegment[] segments() {
        return protoSegments.stream().map(ProtoSegment::toSegment).toArray(LineSegment[]::new);
    }

    private boolean hasNull(Point[] points) {
        for (Point point : points) {
            if (point == null)
                return true;
        }

        return false;
    }

    private boolean sortAndFindDuplicates(Point[] points) {
        Arrays.sort(points, Point::compareTo);

        for (int i = 0; i < points.length - 1; i++) {
            Point ith = points[i];
            Point next = points[i + 1];

            if (ith.compareTo(next) == 0)
                return true;
        }

        return false;
    }

    private void addSegment(Point refPoint, Point[] otherPoints) {
        Point[] allPoints = Arrays.copyOf(otherPoints, otherPoints.length + 1);
        allPoints[otherPoints.length] = refPoint;

        Arrays.sort(allPoints);

        ProtoSegment newProtoSegment = new ProtoSegment(allPoints[0],
                allPoints[allPoints.length - 1]);

        for (ProtoSegment protoSegment : protoSegments) {
            if (newProtoSegment.equalsTo(protoSegment))
                return;
        }

        protoSegments.add(newProtoSegment);
        //        LineSegment newSegment = new LineSegment(allPoints[0], allPoints[allPoints.length - 1]);
        //
        //        for (LineSegment segment : segments) {
        //            if (segment.toString().equals(newSegment.toString()))
        //                return;
        //        }
        //
        //        segments.add(newSegment);
    }

    private class ProtoSegment {
        final Point lo;
        final Point hi;

        ProtoSegment(Point lo, Point hi) {
            this.lo = lo;
            this.hi = hi;
        }

        LineSegment toSegment() {
            return new LineSegment(lo, hi);
        }

        boolean equalsTo(ProtoSegment other) {
            return this.lo.compareTo(other.lo) == 0 && this.hi.compareTo(other.hi) == 0;
        }
    }
}
