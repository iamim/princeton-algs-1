import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Brute")
class BruteCollinearPointsTests {

    @Nested
    @DisplayName("Throws")
    class throwTests {

        @Test
        @DisplayName("when null is passed")
        void nullPassed() {
            assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(null));
        }

        @Test
        @DisplayName("when less than 4 points are passed")
        void lessThan4Passed() {
            assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(
                    new Point[] {new Point(1, 1), new Point(2, 2), new Point(3, 3)}));
        }

        @Test
        @DisplayName("when at least one passed point is null")
        void oneIsNullTest() {
            assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(
                    new Point[] {null, new Point(1, 1), new Point(2, 2), new Point(3, 3)}));
        }

        @Test
        @DisplayName("when at least two points are the same")
        void twoSameTest() {
            assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(
                    new Point[] {new Point(1, 1), new Point(2, 2), new Point(2, 2),
                            new Point(3, 3)}));
        }
    }

    @Nested
    @DisplayName("When a diagonal line of 3 is passed")
    class diagonal3Test {

        BruteCollinearPoints brute;

        @BeforeEach
        void setUp() {
            brute = new BruteCollinearPoints(
                    new Point[] {new Point(2, 2), new Point(3, 3),
                            new Point(4, 4), new Point(2, 1)});
        }

        @Test
        @DisplayName("finds 0 segment")
        void nOfSegments() {
            assertEquals(0, brute.numberOfSegments());
        }

        @Test
        @DisplayName("segments return an empty array")
        void segments() {
            assertArrayEquals(new LineSegment[0], brute.segments());
        }
    }


    @Nested
    @DisplayName("When a diagonal line of 4 is passed")
    class diagonal4Test {

        BruteCollinearPoints brute;

        @BeforeEach
        void setUp() {
            brute = new BruteCollinearPoints(
                    new Point[] {new Point(1, 1), new Point(2, 2), new Point(3, 3),
                            new Point(4, 4)});
        }

        @Test
        @DisplayName("finds 1 segment")
        void nOfSegments() {
            assertEquals(1, brute.numberOfSegments());
        }

        @Test
        @DisplayName("the segment is (1, 1) -> (4, 4)")
        void segments() {
            assertEquals("(1, 1) -> (4, 4)", brute.segments()[0].toString());
        }
    }


    @Nested
    @DisplayName("When a 4x4 is passed")
    class fourByFourTest {

        BruteCollinearPoints brute;

        @BeforeEach
        void setUp() {
            List<Point> inputList = new ArrayList<>();

            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    inputList.add(new Point(i, j));
                }
            }

            brute = new BruteCollinearPoints(inputList.toArray(new Point[inputList.size()]));
        }

        @Test
        @DisplayName("finds 10 segments")
        void nOfSegments() {
            assertEquals(10, brute.numberOfSegments());
        }

        @Test
        @DisplayName("check the expected segments")
        void segments() {
            List<String> segmentStrings = Arrays.stream(brute.segments())
                                                .map(LineSegment::toString)
                                                .collect(Collectors.toList());

            assertThat(segmentStrings,
                    containsInAnyOrder("(1, 1) -> (4, 1)", "(1, 1) -> (1, 4)", "(1, 1) -> (4, 4)",
                            "(2, 1) -> (2, 4)", "(3, 1) -> (3, 4)", "(4, 1) -> (1, 4)",
                            "(4, 1) -> (4, 4)", "(1, 2) -> (4, 2)", "(1, 3) -> (4, 3)",
                            "(1, 4) -> (4, 4)"));
        }
    }
}
