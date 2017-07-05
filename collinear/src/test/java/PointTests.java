import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
}
