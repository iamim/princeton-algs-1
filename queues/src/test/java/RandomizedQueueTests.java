import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RandomizedQueue")
class RandomizedQueueTests {

    @Nested
    @DisplayName("Throws when")
    class ThrowTests {

        RandomizedQueue<Integer> rq;

        @BeforeEach
        void setUp() {
            rq = new RandomizedQueue<>();
        }

        @Test
        @DisplayName("try to add null")
        void addNull() {
            assertThrows(IllegalArgumentException.class, () -> rq.enqueue(null));
        }

        @Test
        @DisplayName("try to sample empty queue")
        void sampleEmpty() {
            assertThrows(NoSuchElementException.class, () -> rq.sample());
        }

        @Test
        @DisplayName("try to pop empty queue")
        void popEmpty() {
            assertThrows(NoSuchElementException.class, () -> rq.dequeue());
        }

        @Test
        @DisplayName("try to call remove() on iterator")
        void removeOnIterator() {
            assertThrows(UnsupportedOperationException.class, () -> rq.iterator().remove());
        }

        @Test
        @DisplayName("try to call next() on iterator when the queue is empty")
        void nextOnIterator() {
            assertThrows(NoSuchElementException.class, () -> rq.iterator().next());
        }
    }

    @Nested
    @DisplayName("When a new instance")
    class ConstructorTests {

        RandomizedQueue<Integer> rq;

        @BeforeEach
        void setUp() {
            rq = new RandomizedQueue<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(rq.isEmpty());
        }

        @Test
        @DisplayName("size is 0")
        void size() {
            assertEquals(0, rq.size());
        }

        @Test
        @DisplayName("throws when try to sample")
        void sampleEmpty() {
            assertThrows(NoSuchElementException.class, () -> rq.sample());
        }

        @Test
        @DisplayName("throws when try to pop")
        void popEmpty() {
            assertThrows(NoSuchElementException.class, () -> rq.dequeue());
        }

        @Test
        @DisplayName("can add a value")
        void enqueue() {
            rq.enqueue(1);
        }

        @Test
        @DisplayName("iterator returns []")
        void iterator() {
            assertIterableEquals(new ArrayList<>(Arrays.asList()), rq);
        }
    }

    @Nested
    @DisplayName("When new instance is instantiated and one add is made")
    class AddOneTests {

        RandomizedQueue<Integer> rq;

        @BeforeEach
        void setUp() {
            rq = new RandomizedQueue<>();
            rq.enqueue(1);
        }

        @Test
        @DisplayName("is NOT empty")
        void isEmpty() {
            assertFalse(rq.isEmpty());
        }

        @Test
        @DisplayName("size is 1")
        void size() {
            assertEquals(1, rq.size());
        }

        @Test
        @DisplayName("can sample")
        void sampleEmpty() {
            assertEquals((Integer) 1, rq.sample());
        }

        @Test
        @DisplayName("can dequeue")
        void popEmpty() {
            assertEquals((Integer) 1, rq.dequeue());
        }

        @Test
        @DisplayName("sample is equal to dequeue")
        void popVsPeek() {
            int sample = rq.sample();
            int dequeue = rq.dequeue();
            assertEquals(sample, dequeue, "Dequeue is not equal to sample");
        }

        @Test
        @DisplayName("can add a value")
        void enqueue() {
            rq.enqueue(1);
        }

        @Test
        @DisplayName("iterator returns [1]")
        void iterator() {
            assertIterableEquals(new ArrayList<>(Arrays.asList(1)), rq);
        }
    }

    @Nested
    @DisplayName("New instance, add one, delete one")
    class AddOneDeleteOneTests {

        RandomizedQueue<Integer> rq;

        @BeforeEach
        void setUp() {
            rq = new RandomizedQueue<>();
            rq.enqueue(1);
            rq.dequeue();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(rq.isEmpty());
        }

        @Test
        @DisplayName("size is 0")
        void size() {
            assertEquals(0, rq.size());
        }

        @Test
        @DisplayName("throws when try to sample")
        void sampleEmpty() {
            assertThrows(NoSuchElementException.class, () -> rq.sample());
        }

        @Test
        @DisplayName("throws when try to pop")
        void popEmpty() {
            assertThrows(NoSuchElementException.class, () -> rq.dequeue());
        }

        @Test
        @DisplayName("can add a value")
        void enqueue() {
            rq.enqueue(1);
        }

        @Test
        @DisplayName("iterator returns []")
        void iterator() {
            assertIterableEquals(new ArrayList<>(Arrays.asList()), rq);
        }
    }

    @Nested
    @DisplayName("New instance, add a bunch")
    class AddManyTests {

        RandomizedQueue<Integer> rq;

        @BeforeEach
        void setUp() {
            rq = new RandomizedQueue<>();
            rq.enqueue(1);
            rq.enqueue(2);
            rq.enqueue(3);
            rq.enqueue(4);
            rq.enqueue(5);
        }

        @Test
        @DisplayName("is Not empty")
        void isEmpty() {
            assertFalse(rq.isEmpty());
        }

        @Test
        @DisplayName("size is 5")
        void size() {
            assertEquals(5, rq.size());
        }

        @Test
        @DisplayName("can sample")
        void sampleEmpty() {
            assertThat(rq.sample(), anyOf(is(1), is(2), is(3), is(4), is(5)));
        }

        @Test
        @DisplayName("can dequeue")
        void popEmpty() {
            assertThat(rq.sample(), anyOf(is(1), is(2), is(3), is(4), is(5)));
        }

        @Test
        @DisplayName("sample is equal to dequeue")
        void popVsPeek() {
            int sample = rq.sample();
            int dequeue = rq.dequeue();
            assertEquals(sample, dequeue, "Dequeue is not equal to sample");
        }

        @Test
        @DisplayName("can add a value")
        void enqueue() {
            rq.enqueue(1);
        }

        @Test
        @DisplayName("iterator returns the input in random order")
        void iterator() {
            assertThat(rq, containsInAnyOrder(1, 2, 3, 4, 5));
        }

        @Test
        @DisplayName("several iterators can operate independently")
        void iterators() {
            Iterator it1 = rq.iterator();
            Iterator it2 = rq.iterator();
            Iterator it3 = rq.iterator();

            List<Integer> it1Result = new ArrayList<>();
            List<Integer> it2Result = new ArrayList<>();
            List<Integer> it3Result = new ArrayList<>();

            while (it1.hasNext()) {
                assertTrue(it2.hasNext());
                assertTrue(it3.hasNext());

                it1Result.add((Integer) it1.next());
                it2Result.add((Integer) it2.next());
                it3Result.add((Integer) it3.next());
            }

            assertThat(it1Result, containsInAnyOrder(1, 2, 3, 4, 5));
            assertThat(it2Result, containsInAnyOrder(1, 2, 3, 4, 5));
            assertThat(it3Result, containsInAnyOrder(1, 2, 3, 4, 5));
        }
    }
}
