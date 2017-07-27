import java.util.*;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private List<ProtoSegment> protoSegments = new ArrayList<>();

    public FastCollinearPoints(Point[] input) {
        if (input == null)
            throw new IllegalArgumentException("The input shouldn't be null");

        // Protective copying
        Point[] points = Arrays.copyOf(input, input.length);

        if (hasNull(points))
            throw new IllegalArgumentException("The input contains null values");

        if (sortAndFindDuplicates(points))
            throw new IllegalArgumentException("The input should not contain duplicates");

        if (points.length < 4) {
            segments = new LineSegment[0];
            return;
        }

        for (Point basePoint : points) {
            Arrays.sort(points, basePoint.slopeOrder()); // n^2 * lg(n)

            double previousSlope = Double.NEGATIVE_INFINITY;
            int nWithSameSlope = 1;
            for (int j = 1; j < points.length; j++) {
                Point jthPoint = points[j];

                double jthSlope = basePoint.slopeTo(jthPoint); // n^2
                if (jthSlope == previousSlope) {
                    nWithSameSlope++;
                }
                else {
                    if (nWithSameSlope >= 3) {
                        addProtoSegment(basePoint, Arrays.copyOfRange(points, j - nWithSameSlope, j));
                    }

                    nWithSameSlope = 1;
                }

                previousSlope = jthSlope;
            }

            if (nWithSameSlope >= 3) {
                addProtoSegment(basePoint,
                        Arrays.copyOfRange(points, points.length - nWithSameSlope, points.length));
            }
        }

        protoSegmentsToSegments();
    }

    private void protoSegmentsToSegments() {
        Collections.sort(protoSegments);
        removeDuplicateProtoSegments(protoSegments);

        segments = new LineSegment[protoSegments.size()];

        for (int i = 0; i < protoSegments.size(); i++) {
            ProtoSegment proto = protoSegments.get(i);
            segments[i] = proto.toSegment();
        }
    }

    private void removeDuplicateProtoSegments(List<ProtoSegment> list) {
        if (list.size() <= 1) return;

        for (int i = list.size() - 2; i >= 0; i--) {
            if (list.get(i).compareTo(list.get(i + 1)) == 0) list.remove(i + 1);
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }

    private boolean hasNull(Point[] points) {
        for (Point point : points) {
            if (point == null)
                return true;
        }

        return false;
    }

    private boolean sortAndFindDuplicates(Point[] points) {
        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++) {
            Point ith = points[i];
            Point next = points[i + 1];

            if (ith.compareTo(next) == 0)
                return true;
        }

        return false;
    }

    private void addProtoSegment(Point refPoint, Point[] otherPoints) {
        Point[] allPoints = Arrays.copyOf(otherPoints, otherPoints.length + 1);
        allPoints[otherPoints.length] = refPoint;

        Arrays.sort(allPoints);

        ProtoSegment newProtoSegment = new ProtoSegment(allPoints[0],
                allPoints[allPoints.length - 1]);

//        for (ProtoSegment protoSegment : protoSegments) {
//            if (newProtoSegment.equalsTo(protoSegment))
//                return;
//        }

        protoSegments.add(newProtoSegment);
    }

    private class ProtoSegment implements Comparable<ProtoSegment>{
        final Point lo;
        final Point hi;

        ProtoSegment(Point lo, Point hi) {
            this.lo = lo;
            this.hi = hi;
        }

        LineSegment toSegment() {
            return new LineSegment(lo, hi);
        }

        public int compareTo(ProtoSegment that) {
            if (this.lo.compareTo(that.lo) == 0) {
                return this.hi.compareTo(that.hi);
            }

            return this.lo.compareTo(that.lo);
        }
    }
}
