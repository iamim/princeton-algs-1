import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<T> implements Iterable<T> {

    private T[] items;
    private int nextIdx;

    public RandomizedQueue() {
        items = (T[]) new Object[1];
        nextIdx = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nextIdx;
    }

    public void enqueue(T item) {
        if (item == null)
            throw new IllegalArgumentException("Can't put null");

        if (nextIdx == items.length)
            resize(nextIdx * 2);

        items[nextIdx++] = item;
    }

    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        T toReturn = items[--nextIdx];
        items[nextIdx] = null;

        StdRandom.shuffle(items);

        if (!isEmpty() && nextIdx == items.length / 4)
            resize(items.length / 2);

        return toReturn;
    }

    public T sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        return items[nextIdx - 1];
    }

    public Iterator<T> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];

        for (int i = 0; i < nextIdx; i++) {
            newArray[i] = items[i];
        }

        items = newArray;
    }

    private class RandomizedQueueIterator implements Iterator<T> {
        private T[] itemLinks;
        private int nextLinkIdx;

        RandomizedQueueIterator() {
            itemLinks = (T[]) new Object[nextIdx];
            nextLinkIdx = 0;

            for (int i = 0; i < nextIdx; i++) {
                itemLinks[i] = items[i];
            }

            StdRandom.shuffle(itemLinks);
        }

        public boolean hasNext() {
            return nextLinkIdx < itemLinks.length;
        }

        public T next() {
            if (isEmpty() || !hasNext())
                throw new NoSuchElementException();

            return itemLinks[nextLinkIdx++];
        }
    }
}
