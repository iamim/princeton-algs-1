import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Fast")
class FastCollinearPointsTests {

    @Nested
    @DisplayName("Throws when")
    class throwTests {

        @Test
        @DisplayName("when null is passed")
        void nullPassed() {
            assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(null));
        }

        @Test
        @DisplayName("when at least one passed point is null")
        void oneIsNullTest() {
            assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(
                    new Point[] {null, new Point(1, 1), new Point(2, 2), new Point(3, 3)}));
        }

        @Test
        @DisplayName("when at least two points are the same")
        void twoSameTest() {
            assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(
                    new Point[] {new Point(1, 1), new Point(2, 2), new Point(2, 2),
                            new Point(3, 3)}));
        }
    }


    @Nested
    @DisplayName("When less than 4 points are passed")
    class lessThan3 {

        FastCollinearPoints fast;

        @BeforeEach
        void setUp() {
            fast = new FastCollinearPoints(
                    new Point[] {new Point(1, 1), new Point(2, 2), new Point(3, 3)});
        }

        @Test
        @DisplayName("finds 0 segment")
        void nOfSegments() {
            assertEquals(0, fast.numberOfSegments());
        }

        @Test
        @DisplayName("segments return an empty array")
        void segments() {
            assertArrayEquals(new LineSegment[0], fast.segments());
        }
    }


    @Nested
    @DisplayName("When a diagonal line of 4 is passed")
    class diagonal4Test {

        FastCollinearPoints fast;

        @BeforeEach
        void setUp() {
            fast = new FastCollinearPoints(
                    new Point[] {new Point(1, 1), new Point(2, 2), new Point(3, 3),
                            new Point(4, 4)});
        }

        @Test
        @DisplayName("finds 1 segment")
        void nOfSegments() {
            assertEquals(1, fast.numberOfSegments());
        }

        @Test
        @DisplayName("the segment is (1, 1) -> (4, 4)")
        void segments() {
            assertEquals("(1, 1) -> (4, 4)", fast.segments()[0].toString());
        }
    }


    @Nested
    @DisplayName("When a diagonal line of 3 is passed")
    class diagonal3Test {

        FastCollinearPoints fast;

        @BeforeEach
        void setUp() {
            fast = new FastCollinearPoints(
                    new Point[] {new Point(1, 1), new Point(2, 2), new Point(3, 3),
                            new Point(2, 1)});
        }

        @Test
        @DisplayName("finds 0 segment")
        void nOfSegments() {
            assertEquals(0, fast.numberOfSegments());
        }

        @Test
        @DisplayName("segments() return an empty array")
        void segments() {
            assertArrayEquals(new LineSegment[0], fast.segments());
        }
    }


    @Nested
    @DisplayName("When 5 points are passed, 4 are diagonal")
    class diagonal4WithAdditionalTest {

        FastCollinearPoints fast;

        @BeforeEach
        void setUp() {
            fast = new FastCollinearPoints(
                    new Point[] {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4),
                            new Point(2, 1)});
        }

        @Test
        @DisplayName("finds 1 segment")
        void nOfSegments() {
            assertEquals(1, fast.numberOfSegments());
        }

        @Test
        @DisplayName("the segment is (1, 1) -> (4, 4)")
        void segments() {
            assertEquals("(1, 1) -> (4, 4)", fast.segments()[0].toString());
        }
    }


    @Nested
    @DisplayName("When a 4x4 is passed")
    class fourByFourTest {

        FastCollinearPoints fast;

        @BeforeEach
        void setUp() {
            List<Point> inputList = new ArrayList<>();

            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    inputList.add(new Point(i, j));
                }
            }

            fast = new FastCollinearPoints(inputList.toArray(new Point[inputList.size()]));
        }

        @Test
        @DisplayName("finds 10 segments")
        void nOfSegments() {
            assertEquals(10, fast.numberOfSegments());
        }

        @Test
        @DisplayName("check the expected segments")
        void segments() {
            List<String> segmentStrings = Arrays.stream(fast.segments())
                                                .map(LineSegment::toString)
                                                .collect(Collectors.toList());

            assertThat(segmentStrings,
                    containsInAnyOrder("(1, 1) -> (4, 1)", "(1, 1) -> (1, 4)", "(1, 1) -> (4, 4)",
                            "(2, 1) -> (2, 4)", "(3, 1) -> (3, 4)", "(4, 1) -> (1, 4)",
                            "(4, 1) -> (4, 4)", "(1, 2) -> (4, 2)", "(1, 3) -> (4, 3)",
                            "(1, 4) -> (4, 4)"));
        }
    }


    @Nested
    @DisplayName("When a 5x5 is passed")
    class fiveByFiveTest {

        FastCollinearPoints fast;

        @BeforeEach
        void setUp() {
            List<Point> inputList = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    inputList.add(new Point(i, j));
                }
            }

            fast = new FastCollinearPoints(inputList.toArray(new Point[inputList.size()]));
        }

        @Test
        @DisplayName("finds 16 segments")
        void nOfSegments() {
            assertEquals(16, fast.numberOfSegments());
        }

        @Test
        @DisplayName("check the expected segments")
        void segments() {
            List<String> segmentStrings = Arrays.stream(fast.segments())
                                                .map(LineSegment::toString)
                                                .collect(Collectors.toList());

            assertThat(segmentStrings,
                    containsInAnyOrder("(1, 1) -> (5, 1)", "(1, 1) -> (1, 5)", "(1, 1) -> (5, 5)",
                            "(2, 1) -> (2, 5)", "(3, 1) -> (3, 5)", "(5, 1) -> (1, 5)",
                            "(4, 1) -> (4, 5)", "(1, 2) -> (5, 2)", "(1, 3) -> (5, 3)",
                            "(1, 5) -> (5, 5)", "(2, 1) -> (5, 4)", "(4, 1) -> (1, 4)",
                            "(1, 4) -> (5, 4)", "(5, 1) -> (5, 5)", "(5, 2) -> (2, 5)",
                            "(1, 2) -> (4, 5)"));
        }
    }

    @Nested
    @DisplayName("When a 6x6 is passed")
    class sixBySixTest {

        FastCollinearPoints fast;

        @BeforeEach
        void setUp() {
            List<Point> inputList = new ArrayList<>();

            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    inputList.add(new Point(i, j));
                }
            }

            fast = new FastCollinearPoints(inputList.toArray(new Point[inputList.size()]));
        }

        @Test
        @DisplayName("finds 22 segments")
        void nOfSegments() {
            assertEquals(22, fast.numberOfSegments());
        }
    }

    @Nested
    @DisplayName("BUG: test (13, 0) -> (10, 1) -> (7, 2) -> (4, 3)")
    class bug1 {

        FastCollinearPoints fast;

        @BeforeEach
        void setUp() {
            List<Point> inputList = new ArrayList<>();

            inputList.add(new Point(13, 0));
            inputList.add(new Point(10, 1));
            inputList.add(new Point(7, 2));
            inputList.add(new Point(4, 3));

            fast = new FastCollinearPoints(inputList.toArray(new Point[inputList.size()]));
        }

        @Test
        @DisplayName("finds 1 segments")
        void nOfSegments() {
            assertEquals(1, fast.numberOfSegments());
        }
    }

//    @Nested
//    @DisplayName("When a 15x4 is passed")
//    class sevenBySevenTest {
//
//        FastCollinearPoints fast;
//
//        @BeforeEach
//        void setUp() {
//            List<Point> inputList = new ArrayList<>();
//
//            for (int i = 1; i <= 15; i++) {
//                for (int j = 1; j <= 4; j++) {
//                    inputList.add(new Point(i, j));
//                }
//            }
//
//            fast = new FastCollinearPoints(inputList.toArray(new Point[inputList.size()]));
//        }
//
//        @Test
//        @DisplayName("finds 79 segments")
//        void nOfSegments() {
//            assertEquals(79, fast.numberOfSegments());
//        }
//    }

//    @Nested
//    @DisplayName("When a 15x15 is passed")
//    class fifteenByFifteenTest {
//
//        FastCollinearPoints fast;
//
//        @BeforeEach
//        void setUp() {
//            List<Point> inputList = new ArrayList<>();
//
//            for (int i = 1; i <= 15; i++) {
//                for (int j = 1; j <= 15; j++) {
//                    inputList.add(new Point(i, j));
//                }
//            }
//
//            fast = new FastCollinearPoints(inputList.toArray(new Point[inputList.size()]));
//        }
//
//        @Test
//        @DisplayName("finds 76 segments")
//        void nOfSegments() {
//            assertEquals(76, fast.numberOfSegments());
//        }
//    }
}
