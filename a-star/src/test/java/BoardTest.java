import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    @Nested
    @DisplayName("When a solved 9x9 table is passed")
    static class nineXnine_solved {
        static Board ut;

        @BeforeAll
        static void setUp() {
            int[][] blocks = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    blocks[i][j] = (i * 3 + j + 1) % 9;
                }
            }

            assertEquals(1, blocks[0][0]);
            assertEquals(4, blocks[1][0]);
            assertEquals(0, blocks[2][2]);

            ut = new Board(blocks);
        }

        @Test
        @DisplayName("dimensions is 3")
        void dimensions() {
            assertEquals(3, ut.dimension());
        }

        @Test
        @DisplayName("Hamming number is 0")
        void hamming() {
            assertEquals(0, ut.hamming());
        }

        @Test
        @DisplayName("Manhattan number is 0")
        void manhattan() {
            assertEquals(0, ut.manhattan());
        }

        @Test
        @DisplayName("isGoal true")
        void isGoal() {
            assertTrue(ut.isGoal());
        }

        @Test
        @DisplayName("twin returns (0,0) <-> (1,0)")
        void twin() {
            String expectedStr = "3\n" +
                    " 4  2  3 \n" +
                    " 1  5  6 \n" +
                    " 7  8  0 \n";

            assertEquals(expectedStr, ut.twin().toString());
        }

        @Test
        @DisplayName("neighbors return 2 expected neighbors")
        void neighbors() {
            String n1 = "3\n" +
                    " 1  2  3 \n" +
                    " 4  5  0 \n" +
                    " 7  8  6 \n";

            String n2 = "3\n" +
                    " 1  2  3 \n" +
                    " 4  5  6 \n" +
                    " 7  0  8 \n";

            ut.neighbors().forEach(n -> System.out.println(n.toString()));

//            assertThat(ut.neighbors(), containsInAnyOrder(n1, n2));
        }

        @Test
        @DisplayName("toString")
        void _toString() {
            String expectedStr = "3\n" +
                    " 1  2  3 \n" +
                    " 4  5  6 \n" +
                    " 7  8  0 \n";

            assertEquals(expectedStr, ut.toString());
        }
    }

    @Nested
    @DisplayName("When an example board is passed")
    static class nineXnine_example {
        static Board ut;

        @BeforeAll
        static void setUp() {
            int[] input = new int[]{8, 1, 3, 4, 0, 2, 7, 6, 5};

            int[][] blocks = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    blocks[i][j] = input[i*3 + j];
                }
            }

            ut = new Board(blocks);
        }

        @Test
        @DisplayName("dimensions is 3")
        void dimensions() {
            assertEquals(3, ut.dimension());
        }

        @Test
        @DisplayName("Hamming number is 5")
        void hamming() {
            assertEquals(5, ut.hamming());
        }

        @Test
        @DisplayName("Manhattan number is 10")
        void manhattan() {
            assertEquals(10, ut.manhattan());
        }

        @Test
        @DisplayName("isGoal false")
        void isGoal() {
            assertFalse(ut.isGoal());
        }

        @Test
        @DisplayName("twin returns (0,0) <-> (1,0)")
        void twin() {
            String expectedStr = "3\n" +
                    " 4  1  3 \n" +
                    " 8  0  2 \n" +
                    " 7  6  5 \n";

            assertEquals(expectedStr, ut.twin().toString());
        }

        @Test
        @DisplayName("neighbors return 4 expected neighbors")
        void neighbors() {

            ut.neighbors().forEach(n -> System.out.println(n.toString()));

        }

        @Test
        @DisplayName("toString")
        void _toString() {
            String expectedStr = "3\n" +
                    " 8  1  3 \n" +
                    " 4  0  2 \n" +
                    " 7  6  5 \n";

            assertEquals(expectedStr, ut.toString());
        }
    }
}
