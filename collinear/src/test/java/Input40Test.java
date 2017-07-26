import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Input40Test {

    static Point[] points;

    @BeforeAll
    static void readPoints() {
        In in = new In("/Users/ilya/Git/princeton-algs-1/collinear/src/test/resources/input40.txt");
        int n = in.readInt();
        points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
    }

    @Test
    void shouldReturn4Segments() {
        FastCollinearPoints fast = new FastCollinearPoints(points);
        assertEquals(4, fast.numberOfSegments());
    }
}
