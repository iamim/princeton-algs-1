import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Can't add null to the deque");

        Node newNode = new Node();
        newNode.val = item;
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            newNode.nextToLast = first;
            first.nextToFirst = newNode;
            first = newNode;
        }

        size++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Can't add null to the deque");

        Node newNode = new Node();
        newNode.val = item;
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            newNode.nextToFirst = last;
            last.nextToLast = newNode;
            last = newNode;
        }

        size++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("The deque is empty");

        Item toReturn = first.val;

        if (size() == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.nextToLast;
            first.nextToFirst = null;
        }

        size--;

        return toReturn;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("The deque is empty");

        Item toReturn = last.val;

        if (size() == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.nextToFirst;
            last.nextToLast = null;
        }

        size--;

        return toReturn;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item item = current.val;
            current = current.nextToLast;
            return item;
        }
    }

    private class Node {
        Item val;

        Node nextToFirst;
        Node nextToLast;
    }
}
