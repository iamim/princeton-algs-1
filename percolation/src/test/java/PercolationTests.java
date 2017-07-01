import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Percolation tests")
class PercolationTests {

    @Nested
    @DisplayName("Constructor tests")
    class ConstructorTests {

        @Test
        @DisplayName("initialized with dimension 3 normally")
        void createsNormallyWith3() {
            new Percolation(3);
        }

        @Test
        @DisplayName("throws when dimension is 0")
        void throwsWhenArgIs0() {
            assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
        }

        @Test
        @DisplayName("throws when dimension is negative")
        void throwsWhenArgIsNegative() {
            assertThrows(IllegalArgumentException.class, () -> new Percolation(-1));
        }
    }

    @Nested
    @DisplayName("Interface tests")
    class InterfaceTests {

        private Percolation perc;

        @BeforeEach
        void setUp() {
            perc = new Percolation(3);
        }

        @Test
        @DisplayName("open() throws with unexpected argument")
        void openThrowsTest() {
            assertThrows(IllegalArgumentException.class, () -> perc.open(0, 1));
            assertThrows(IllegalArgumentException.class, () -> perc.open(1, 0));
            assertThrows(IllegalArgumentException.class, () -> perc.open(-1, 1));
            assertThrows(IllegalArgumentException.class, () -> perc.open(0, -1));
            assertThrows(IllegalArgumentException.class, () -> perc.open(0, 4));
            assertThrows(IllegalArgumentException.class, () -> perc.open(4, 0));
            assertThrows(IllegalArgumentException.class, () -> perc.open(4, -1));
        }

        @Test
        @DisplayName("isOpen() throws with unexpected argument")
        void isFullThrowsTest() {
            assertThrows(IllegalArgumentException.class, () -> perc.isOpen(0, 1));
            assertThrows(IllegalArgumentException.class, () -> perc.isOpen(1, 0));
            assertThrows(IllegalArgumentException.class, () -> perc.isOpen(-1, 1));
            assertThrows(IllegalArgumentException.class, () -> perc.isOpen(0, -1));
            assertThrows(IllegalArgumentException.class, () -> perc.isOpen(0, 4));
            assertThrows(IllegalArgumentException.class, () -> perc.isOpen(4, 0));
            assertThrows(IllegalArgumentException.class, () -> perc.isOpen(4, -1));
        }

        @Test
        @DisplayName("isFull() throws with unexpected argument")
        void isOpenThrowsTest() {
            assertThrows(IllegalArgumentException.class, () -> perc.isFull(0, 1));
            assertThrows(IllegalArgumentException.class, () -> perc.isFull(1, 0));
            assertThrows(IllegalArgumentException.class, () -> perc.isFull(-1, 1));
            assertThrows(IllegalArgumentException.class, () -> perc.isFull(0, -1));
            assertThrows(IllegalArgumentException.class, () -> perc.isFull(0, 4));
            assertThrows(IllegalArgumentException.class, () -> perc.isFull(4, 0));
            assertThrows(IllegalArgumentException.class, () -> perc.isFull(4, -1));
        }
    }

    @Nested
    @DisplayName("When only (1, 1) is open")
    class OneOpen {
        private Percolation perc;

        @BeforeEach
        void setUp() {
            perc = new Percolation(3);
            perc.open(1, 1);
        }

        @AfterEach
        void tearDown() {
            perc = null;
        }

        @Test
        @DisplayName("numberOfOpenSites() is 1")
        void numberOfOpen_1() {
            assertEquals(1, perc.numberOfOpenSites());
        }

        @Test
        @DisplayName("only (1, 1) is open, rest are closed")
        void openTest() {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (i == 1 && j == 1) {
                        assertTrue(perc.isOpen(i, j));
                    }
                    else {
                        assertFalse(perc.isOpen(i, j));
                    }
                }
            }
        }

        @Test
        @DisplayName("only (1, 1) isFull, rest are empty")
        void fullTest() {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (i == 1 && j == 1) {
                        assertTrue(perc.isFull(i, j));
                    }
                    else {
                        assertFalse(perc.isFull(i, j));
                    }
                }
            }
        }

        @Test
        @DisplayName("doesn't percolate")
        void percolateTest() {
            assertFalse(perc.percolates());
        }
    }

    @Nested
    @DisplayName("When only top row is open")
    class TopRowOpen {
        private Percolation perc;

        @BeforeEach
        void setUp() {
            perc = new Percolation(3);
            perc.open(1, 1);
            perc.open(1, 2);
            perc.open(1, 3);
        }

        @AfterEach
        void tearDown() {
            perc = null;
        }

        @Test
        @DisplayName("numberOfOpenSites() is 3")
        void numberOfOpen_1() {
            assertEquals(3, perc.numberOfOpenSites());
        }

        @Test
        @DisplayName("only top row is open, rest are closed")
        void openTest() {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (i == 1) {
                        assertTrue(perc.isOpen(i, j));
                    }
                    else {
                        assertFalse(perc.isOpen(i, j));
                    }
                }
            }
        }

        @Test
        @DisplayName("only top row isFull, rest are empty")
        void fullTest() {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (i == 1) {
                        assertTrue(perc.isFull(i, j));
                    }
                    else {
                        assertFalse(perc.isFull(i, j));
                    }
                }
            }
        }

        @Test
        @DisplayName("doesn't percolate")
        void percolateTest() {
            assertFalse(perc.percolates());
        }
    }

    @Nested
    @DisplayName("When only diagonal is open")
    class DiagonalOpen {
        private Percolation perc;

        @BeforeEach
        void setUp() {
            perc = new Percolation(3);
            perc.open(1, 1);
            perc.open(2, 2);
            perc.open(3, 3);
        }

        @AfterEach
        void tearDown() {
            perc = null;
        }

        @Test
        @DisplayName("numberOfOpenSites() is 3")
        void numberOfOpen_1() {
            assertEquals(3, perc.numberOfOpenSites());
        }

        @Test
        @DisplayName("only diagonal is open, rest are closed")
        void openTest() {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (i == j) {
                        assertTrue(perc.isOpen(i, j));
                    }
                    else {
                        assertFalse(perc.isOpen(i, j));
                    }
                }
            }
        }

        @Test
        @DisplayName("only (1, 1) isFull, rest are empty")
        void fullTest() {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (i == 1 && j == 1) {
                        assertTrue(perc.isFull(i, j));
                    }
                    else {
                        assertFalse(perc.isFull(i, j));
                    }
                }
            }
        }

        @Test
        @DisplayName("doesn't percolate")
        void percolateTest() {
            assertFalse(perc.percolates());
        }
    }

    @Nested
    @DisplayName("When only left column is open")
    class LeftColumnOpen {
        private Percolation perc;

        @BeforeEach
        void setUp() {
            perc = new Percolation(3);
            perc.open(1, 1);
            perc.open(2, 1);
            perc.open(3, 1);
        }

        @AfterEach
        void tearDown() {
            perc = null;
        }

        @Test
        @DisplayName("numberOfOpenSites() is 3")
        void numberOfOpen_1() {
            assertEquals(3, perc.numberOfOpenSites());
        }

        @Test
        @DisplayName("only left column is open, rest are closed")
        void openTest() {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (j == 1) {
                        assertTrue(perc.isOpen(i, j));
                    }
                    else {
                        assertFalse(perc.isOpen(i, j));
                    }
                }
            }
        }

        @Test
        @DisplayName("only left column isFull, rest are empty")
        void fullTest() {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (j == 1) {
                        assertTrue(perc.isFull(i, j));
                    }
                    else {
                        assertFalse(perc.isFull(i, j));
                    }
                }
            }
        }

        @Test
        @DisplayName("does percolate")
        void percolateTest() {
            assertTrue(perc.percolates());
        }
    }

    @Nested
    @DisplayName("When the board consists of one cell and it's closed")
    class OneCellClosed {
        private Percolation perc;

        @BeforeEach
        void setUp() {
            perc = new Percolation(1);
        }

        @AfterEach
        void tearDown() {
            perc = null;
        }

        @Test
        @DisplayName("numberOfOpenSites() is 1")
        void numberOfOpen_1() {
            assertEquals(0, perc.numberOfOpenSites());
        }

        @Test
        @DisplayName("the only cell is closed")
        void openTest() {
            assertFalse(perc.isOpen(1, 1));
        }

        @Test
        @DisplayName("the only cell is not full")
        void fullTest() {
            assertFalse(perc.isFull(1, 1));
        }

        @Test
        @DisplayName("doesn't percolate")
        void percolateTest() {
            assertFalse(perc.percolates());
        }
    }

    @Nested
    @DisplayName("When the board consists of one cell and it's open")
    class OneCellOpen {
        private Percolation perc;

        @BeforeEach
        void setUp() {
            perc = new Percolation(1);
            perc.open(1, 1);
        }

        @AfterEach
        void tearDown() {
            perc = null;
        }

        @Test
        @DisplayName("numberOfOpenSites() is 1")
        void numberOfOpen_1() {
            assertEquals(1, perc.numberOfOpenSites());
        }

        @Test
        @DisplayName("the only cell is open")
        void openTest() {
            assertTrue(perc.isOpen(1, 1));
        }

        @Test
        @DisplayName("the only cell is full")
        void fullTest() {
            assertTrue(perc.isFull(1, 1));
        }

        @Test
        @DisplayName("does percolate")
        void percolateTest() {
            assertTrue(perc.percolates());
        }
    }

    @Nested
    @DisplayName("When the same cell is opened twice in a single cell table")
    class OneCellOpen_Twice {
        private Percolation perc;

        @BeforeEach
        void setUp() {
            perc = new Percolation(1);
            perc.open(1, 1);
            perc.open(1, 1);
        }

        @AfterEach
        void tearDown() {
            perc = null;
        }

        @Test
        @DisplayName("numberOfOpenSites() is 1")
        void numberOfOpen_1() {
            assertEquals(1, perc.numberOfOpenSites());
        }

        @Test
        @DisplayName("the only cell is open")
        void openTest() {
            assertTrue(perc.isOpen(1, 1));
        }

        @Test
        @DisplayName("the only cell is full")
        void fullTest() {
            assertTrue(perc.isFull(1, 1));
        }

        @Test
        @DisplayName("does percolate")
        void percolateTest() {
            assertTrue(perc.percolates());
        }
    }
}
