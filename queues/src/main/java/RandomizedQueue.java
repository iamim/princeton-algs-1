import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int nextIdx;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        nextIdx = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nextIdx;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Can't put null");

        if (nextIdx == items.length)
            resize(nextIdx * 2);

        items[nextIdx++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item toReturn = items[--nextIdx];
        items[nextIdx] = null;

        StdRandom.shuffle(items);

        if (!isEmpty() && nextIdx == items.length / 4)
            resize(items.length / 2);

        return toReturn;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        return items[nextIdx - 1];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];

        for (int i = 0; i < nextIdx; i++) {
            newArray[i] = items[i];
        }

        items = newArray;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] itemLinks;
        private int nextLinkIdx;

        RandomizedQueueIterator() {
            itemLinks = (Item[]) new Object[nextIdx];
            nextLinkIdx = 0;

            for (int i = 0; i < nextIdx; i++) {
                itemLinks[i] = items[i];
            }

            StdRandom.shuffle(itemLinks);
        }

        public boolean hasNext() {
            return nextLinkIdx < itemLinks.length;
        }

        public Item next() {
            if (isEmpty() || !hasNext())
                throw new NoSuchElementException();

            return itemLinks[nextLinkIdx++];
        }
    }
}
