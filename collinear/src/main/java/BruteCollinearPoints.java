import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments = new LineSegment[0];

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException(
                    "The input shouldn't be null");

        if (points.length < 4)
            return;

        if (hasNull(points))
            throw new IllegalArgumentException("The input contains null values");

        if (sortAndFindDuplicates(points))
            throw new IllegalArgumentException("The input should not contain duplicates or nulls");

        for (int i = 0; i < points.length - 3; i++) {
            Point p1 = points[i];

            for (int j = i + 1; j < points.length - 2; j++) {
                Point p2 = points[j];

                for (int k = j + 1; k < points.length - 1; k++) {
                    Point p3 = points[k];

                    for (int m = k + 1; m < points.length; m++) {
                        Point p4 = points[m];

                        double s1 = p1.slopeTo(p2);
                        double s2 = p1.slopeTo(p3);
                        double s3 = p1.slopeTo(p4);

                        // Rounding may be a problem?
                        if (s1 == s2 && s2 == s3) {
                            Point[] thesePoints = {p1, p2, p3, p4};
                            Arrays.sort(thesePoints, Point::compareTo);

                            addSegment(new LineSegment(thesePoints[0], thesePoints[3]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    private void addSegment(LineSegment segment) {
        LineSegment[] temp = new LineSegment[segments.length + 1];

        System.arraycopy(segments, 0, temp, 0, segments.length);
        temp[segments.length] = segment;

        segments = temp;
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
}
