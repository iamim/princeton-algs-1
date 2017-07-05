import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Point")
class PointTests {

    @Nested
    @DisplayName("compareTo()")
    class compareTo {

        @Test
        @DisplayName("(1, 1) < (2, 2)")
        void test1() {
            assertEquals(-1, (new Point(1, 1)).compareTo(new Point(2, 2)));
        }

        @Test
        @DisplayName("(1, 1) < (1, 2)")
        void test2() {
            assertEquals(-1, (new Point(1, 1)).compareTo(new Point(1, 2)));
        }

        @Test
        @DisplayName("(1, 1) < (2, 1)")
        void test3() {
            assertEquals(-1, (new Point(1, 1)).compareTo(new Point(1, 2)));
        }

        @Test
        @DisplayName("(1, 1) = (1, 1)")
        void test4() {
            assertEquals(0, (new Point(1, 1)).compareTo(new Point(1, 1)));
        }

        @Test
        @DisplayName("(1, 1) > (1, 0)")
        void test5() {
            assertEquals(1, (new Point(1, 1)).compareTo(new Point(1, 0)));
        }

        @Test
        @DisplayName("(1, 1) > (0, 1)")
        void test6() {
            assertEquals(1, (new Point(1, 1)).compareTo(new Point(0, 1)));
        }

        @Test
        @DisplayName("(1, 1) > (0, 0)")
        void test7() {
            assertEquals(1, (new Point(1, 1)).compareTo(new Point(0, 0)));
        }
    }

    @Nested
    @DisplayName("slopeTo()")
    class slopeTo {

        @Test
        @DisplayName("same point: [1,1].slopeTo([1,1]) = -Inf")
        void test1() {
            assertEquals(Double.NEGATIVE_INFINITY, (new Point(1, 1)).slopeTo(new Point(1, 1)));
        }

        @Test
        @DisplayName("vertical line up: [1,1].slopeTo([1,2]) = +Inf")
        void test2() {
            assertEquals(Double.POSITIVE_INFINITY, (new Point(1, 1)).slopeTo(new Point(1, 2)));
        }

        @Test
        @DisplayName("vertical line down: [1,1].slopeTo([1,-1]) = +Inf")
        void test3() {
            assertEquals(Double.POSITIVE_INFINITY, (new Point(1, 1)).slopeTo(new Point(1, -1)));
        }

        @Test
        @DisplayName("horizontal line right: [1,1].slopeTo([2,1]) = 0.0")
        void test4() {
            assertEquals(0.0, (new Point(1, 1)).slopeTo(new Point(2, 1)));
        }

        @Test
        @DisplayName("horizontal line left: [1,1].slopeTo([-1,1]) = 0.0")
        void test5() {
            assertEquals(0.0, (new Point(1, 1)).slopeTo(new Point(-1, 1)));
        }

        @Test
        @DisplayName("quadrant 1: [0,0].slopeTo([1,1]) = tan(45)")
        void test6() {
            assertEquals(Math.tan(Math.toRadians(45)), (new Point(0, 0)).slopeTo(new Point(1, 1)), 0.000001);
        }

        @Test
        @DisplayName("quadrant 2: [0,0].slopeTo([1,-1]) = tan(135)")
        void test7() {
            assertEquals(Math.tan(Math.toRadians(135)), (new Point(0, 0)).slopeTo(new Point(1, -1)), 0.000001);
        }

        @Test
        @DisplayName("quadrant 3: [0,0].slopeTo([-1,-1]) = tan(225)")
        void test8() {
            assertEquals(Math.tan(Math.toRadians(225)), (new Point(0, 0)).slopeTo(new Point(-1, -1)), 0.000001);
        }

        @Test
        @DisplayName("quadrant 4: [0,0].slopeTo([-1,1]) = tan(315)")
        void test9() {
            assertEquals(Math.tan(Math.toRadians(315)), (new Point(0, 0)).slopeTo(new Point(-1, 1)), 0.000001);
        }
    }

    @Nested
    @DisplayName("slopeOrder()")
    class slopeOrder {
        Point base = new Point(0, 0);
        Comparator<Point> comp = base.slopeOrder();

        @Test
        @DisplayName("(2,2) == (1,1)")
        void test1() {
            assertEquals(0, comp.compare(new Point(2,2), new Point(1, 1)));
        }

        @Test
        @DisplayName("(2,1) < (1,1)")
        void test2() {
            assertEquals(-1, comp.compare(new Point(2,1), new Point(1, 1)));
        }

        @Test
        @DisplayName("(1,2) > (1,1)")
        void test3() {
            assertEquals(1, comp.compare(new Point(1,2), new Point(1, 1)));
        }
    }
}
