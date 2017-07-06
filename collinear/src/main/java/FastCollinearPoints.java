import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments = new LineSegment[0];

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
            Arrays.sort(points, basePoint.slopeOrder());

            Double previousSlope = null;
            int nWithSameSlope = 1;
            for (int j = 0; j < points.length; j++) {
                Point jthPoint = points[j];

                double jthSlope = basePoint.slopeTo(jthPoint);
                if (previousSlope != null && jthSlope == previousSlope) {
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
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
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

            if (ith == null || next == null)
                return true;
            if (ith.compareTo(next) == 0)
                return true;
        }

        return false;
    }

    private void addSegment(Point refPoint, Point[] otherPoints) {
        Point[] allPoints = Arrays.copyOf(otherPoints, otherPoints.length + 1);
        allPoints[otherPoints.length] = refPoint;

        Arrays.sort(allPoints, Point::compareTo);

        LineSegment newSegment = new LineSegment(allPoints[0], allPoints[allPoints.length - 1]);

        for (LineSegment segment : segments) {
            if (segment.toString().equals(newSegment.toString()))
                return;
        }

        LineSegment[] newSegments = Arrays.copyOf(segments, segments.length + 1);
        newSegments[newSegments.length - 1] = newSegment;

        segments = newSegments;
    }
}
