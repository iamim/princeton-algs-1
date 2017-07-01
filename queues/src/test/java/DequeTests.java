import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Deque")
class DequeTests {

    @Nested
    @DisplayName("Throws when")
    class ThrowTests {

        Deque<Integer> deque;

        @BeforeEach
        void setUp() {
            deque = new Deque<>();
        }

        @Test
        @DisplayName("try to add null to the front")
        void nullToFront() {
            assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
        }

        @Test
        @DisplayName("try to add null to the end")
        void nullToEnd() {
            assertThrows(IllegalArgumentException.class, () -> deque.addLast(null));
        }

        @Test
        @DisplayName("try to pop from the front of empty deque")
        void popFromEmpty_front() {
            assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
        }

        @Test
        @DisplayName("try to pop from the end of empty deque")
        void popFromEmpty_end() {
            assertThrows(NoSuchElementException.class, () -> deque.removeLast());
        }

        @Test
        @DisplayName("try to invoke remove on the iterator")
        void iteratorRemove() {
            assertThrows(UnsupportedOperationException.class, () -> deque.iterator().remove());
        }
    }


    @Nested
    @DisplayName("When the constructor is invoked")
    class ConstructorTests {

        Deque<Integer> deque;

        @BeforeEach
        void setUp() {
            deque = new Deque<>();
        }

        @Test
        @DisplayName("size is 0")
        void size() {
            assertEquals(0, deque.size());
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(deque.isEmpty());
        }
    }


    @Nested
    @DisplayName("When one added to the front")
    class FrontAddOneTests {

        Deque<Integer> deque;

        @BeforeEach
        void setUp() {
            deque = new Deque<>();
            deque.addFirst(1);
        }

        @Test
        @DisplayName("size is 1")
        void size() {
            assertEquals(1, deque.size());
        }

        @Test
        @DisplayName("is NOT empty")
        void isEmpty() {
            assertFalse(deque.isEmpty());
        }

        @Test
        @DisplayName("is removable from the front")
        void removeFront() {
            assertEquals((Integer) 1, deque.removeFirst());
        }

        @Test
        @DisplayName("is removable from the end")
        void removeEnd() {
            assertEquals((Integer) 1, deque.removeLast());
        }

        @Test
        @DisplayName("iterator returns 1")
        void iterator() {
            assertIterableEquals(new ArrayList<>(Arrays.asList(1)), deque);
        }

        @Nested
        @DisplayName("then if removed from the front")
        class removedFromFront {

            @BeforeEach
            void setUp2() {
                deque.removeFirst();
            }

            @Test
            @DisplayName("size is zero")
            void size() {
                assertEquals(0, deque.size());
            }

            @Test
            @DisplayName("is empty")
            void isEmpty() {
                assertTrue(deque.isEmpty());
            }

            @Test
            @DisplayName("throws if try to pop from the front of empty deque")
            void popFromEmpty_front() {
                assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
            }

            @Test
            @DisplayName("throws if try to pop from the end of empty deque")
            void popFromEmpty_end() {
                assertThrows(NoSuchElementException.class, () -> deque.removeLast());
            }

            @Test
            @DisplayName("iterator returns nothing")
            void iterator() {
                assertIterableEquals(new ArrayList<>(Arrays.asList()), deque);
            }
        }


        @Nested
        @DisplayName("then if removed from the end")
        class removedFromEnd {

            @BeforeEach
            void setUp2() {
                deque.removeLast();
            }

            @Test
            @DisplayName("size is zero")
            void size() {
                assertEquals(0, deque.size());
            }

            @Test
            @DisplayName("is empty")
            void isEmpty() {
                assertTrue(deque.isEmpty());
            }

            @Test
            @DisplayName("throws if try to pop from the front of empty deque")
            void popFromEmpty_front() {
                assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
            }

            @Test
            @DisplayName("throws if try to pop from the end of empty deque")
            void popFromEmpty_end() {
                assertThrows(NoSuchElementException.class, () -> deque.removeLast());
            }

            @Test
            @DisplayName("iterator returns nothing")
            void iterator() {
                assertIterableEquals(new ArrayList<>(Arrays.asList()), deque);
            }
        }
    }


    @Nested
    @DisplayName("When one added to the end")
    class EndAddOneTests {

        Deque<Integer> deque;

        @BeforeEach
        void setUp() {
            deque = new Deque<>();
            deque.addLast(1);
        }

        @Test
        @DisplayName("size is 1")
        void size() {
            assertEquals(1, deque.size());
        }

        @Test
        @DisplayName("is NOT empty")
        void isEmpty() {
            assertFalse(deque.isEmpty());
        }

        @Test
        @DisplayName("is removable from the front")
        void removeFront() {
            assertEquals((Integer) 1, deque.removeFirst());
        }

        @Test
        @DisplayName("is removable from the end")
        void removeEnd() {
            assertEquals((Integer) 1, deque.removeLast());
        }

        @Test
        @DisplayName("iterator returns 1")
        void iterator() {
            assertIterableEquals(new ArrayList<>(Arrays.asList(1)), deque);
        }

        @Nested
        @DisplayName("then if removed from the front")
        class removedFromFront {

            @BeforeEach
            void setUp2() {
                deque.removeFirst();
            }

            @Test
            @DisplayName("size is zero")
            void size() {
                assertEquals(0, deque.size());
            }

            @Test
            @DisplayName("is empty")
            void isEmpty() {
                assertTrue(deque.isEmpty());
            }

            @Test
            @DisplayName("throws if try to pop from the front of empty deque")
            void popFromEmpty_front() {
                assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
            }

            @Test
            @DisplayName("throws if try to pop from the end of empty deque")
            void popFromEmpty_end() {
                assertThrows(NoSuchElementException.class, () -> deque.removeLast());
            }

            @Test
            @DisplayName("iterator returns nothing")
            void iterator() {
                assertIterableEquals(new ArrayList<>(Arrays.asList()), deque);
            }
        }


        @Nested
        @DisplayName("then if removed from the end")
        class removedFromEnd {

            @BeforeEach
            void setUp2() {
                deque.removeLast();
            }

            @Test
            @DisplayName("size is zero")
            void size() {
                assertEquals(0, deque.size());
            }

            @Test
            @DisplayName("is empty")
            void isEmpty() {
                assertTrue(deque.isEmpty());
            }

            @Test
            @DisplayName("throws if try to pop from the front of empty deque")
            void popFromEmpty_front() {
                assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
            }

            @Test
            @DisplayName("throws if try to pop from the end of empty deque")
            void popFromEmpty_end() {
                assertThrows(NoSuchElementException.class, () -> deque.removeLast());
            }

            @Test
            @DisplayName("iterator returns nothing")
            void iterator() {
                assertIterableEquals(new ArrayList<>(Arrays.asList()), deque);
            }
        }
    }


    @Nested
    @DisplayName("When multiple added from both sides")
    class MultipleAddTests {

        Deque<Integer> deque;

        @BeforeEach
        void setUp() {
            deque = new Deque<>();

            // [1, 2, 3, 4]
            deque.addFirst(2);
            deque.addLast(3);
            deque.addFirst(1);
            deque.addLast(4);
        }

        @Test
        @DisplayName("size is 1")
        void size() {
            assertEquals(4, deque.size());
        }

        @Test
        @DisplayName("is NOT empty")
        void isEmpty() {
            assertFalse(deque.isEmpty());
        }

        @Test
        @DisplayName("is removable from the front")
        void removeFront() {
            assertEquals((Integer) 1, deque.removeFirst());
        }

        @Test
        @DisplayName("is removable from the end")
        void removeEnd() {
            assertEquals((Integer) 4, deque.removeLast());
        }

        @Test
        @DisplayName("iterator returns [1, 2, 3, 4]")
        void iterator() {
            assertIterableEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), deque);
        }

        @Nested
        @DisplayName("then if one removed from the front")
        class removedFromFront {

            @BeforeEach
            void setUp2() {
                deque.removeFirst();
            }

            @Test
            @DisplayName("size is 3")
            void size() {
                assertEquals(3, deque.size());
            }

            @Test
            @DisplayName("is NOT empty")
            void isEmpty() {
                assertFalse(deque.isEmpty());
            }

            @Test
            @DisplayName("is removable from the front")
            void removeFront() {
                assertEquals((Integer) 2, deque.removeFirst());
            }

            @Test
            @DisplayName("is removable from the end")
            void removeEnd() {
                assertEquals((Integer) 4, deque.removeLast());
            }

            @Test
            @DisplayName("iterator returns [2, 3, 4]")
            void iterator() {
                assertIterableEquals(new ArrayList<>(Arrays.asList(2, 3, 4)), deque);
            }
        }


        @Nested
        @DisplayName("then if removed from the end")
        class removedFromEnd {

            @BeforeEach
            void setUp2() {
                deque.removeLast();
            }

            @Test
            @DisplayName("size is 3")
            void size() {
                assertEquals(3, deque.size());
            }

            @Test
            @DisplayName("is NOT empty")
            void isEmpty() {
                assertFalse(deque.isEmpty());
            }

            @Test
            @DisplayName("is removable from the front")
            void removeFront() {
                assertEquals((Integer) 1, deque.removeFirst());
            }

            @Test
            @DisplayName("is removable from the end")
            void removeEnd() {
                assertEquals((Integer) 3, deque.removeLast());
            }

            @Test
            @DisplayName("iterator returns [1, 2, 3]")
            void iterator() {
                assertIterableEquals(new ArrayList<>(Arrays.asList(1, 2, 3)), deque);
            }
        }

        @Nested
        @DisplayName("then if all removed from both sides")
        class removedAll {

            @BeforeEach
            void setUp2() {
                deque.removeLast();
                deque.removeFirst();
                deque.removeLast();
                deque.removeFirst();
            }

            @Test
            @DisplayName("size is 0")
            void size() {
                assertEquals(0, deque.size());
            }

            @Test
            @DisplayName("is empty")
            void isEmpty() {
                assertTrue(deque.isEmpty());
            }

            @Test
            @DisplayName("throws if try to pop from the front of empty deque")
            void popFromEmpty_front() {
                assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
            }

            @Test
            @DisplayName("throws if try to pop from the end of empty deque")
            void popFromEmpty_end() {
                assertThrows(NoSuchElementException.class, () -> deque.removeLast());
            }

            @Test
            @DisplayName("iterator returns nothing")
            void iterator() {
                assertIterableEquals(new ArrayList<>(Arrays.asList()), deque);
            }
        }

        @Nested
        @DisplayName("then if all removed from both sides and added again")
        class removedAllAndAdded {

            @BeforeEach
            void setUp2() {
                deque.removeLast();
                deque.removeFirst();
                deque.removeLast();
                deque.removeFirst();

                // [1, 2, 3, 4]
                deque.addFirst(2);
                deque.addLast(3);
                deque.addFirst(1);
                deque.addLast(4);
            }

            @Test
            @DisplayName("size is 4")
            void size() {
                assertEquals(4, deque.size());
            }

            @Test
            @DisplayName("is NOT empty")
            void isEmpty() {
                assertFalse(deque.isEmpty());
            }

            @Test
            @DisplayName("is removable from the front")
            void removeFront() {
                assertEquals((Integer) 1, deque.removeFirst());
            }

            @Test
            @DisplayName("is removable from the end")
            void removeEnd() {
                assertEquals((Integer) 4, deque.removeLast());
            }

            @Test
            @DisplayName("iterator returns [1, 2, 3, 4]")
            void iterator() {
                assertIterableEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), deque);
            }
        }
    }

}
