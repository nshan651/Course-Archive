package Lab_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FixedArrayQueue<E> implements SimpleQueue<E> {

    private final int capacity;

    private int size;

    private int front;

    private int rear;

    private final E[] data;

    /* Explicit Constructor to allocate size of the fixed array queue.*/
    @SuppressWarnings("unchecked")
    public FixedArrayQueue(final int capacity) {
        this.capacity = capacity;
        this.data = (E[]) new Object[capacity];
        this.size = 0;
        this.front = 0; // read
        this.rear = 0; // write
    }

    @Override
    public boolean offer(final E obj) {
        /* Adds an item to the end of the queue and returns a boolean to indicate whether the attempt succeeded */
        if (front == rear && size != 0) {
            return false;
        }
        size++;
        data[rear] = obj;
        rear = (rear+1)%(data.length);
        return true;
    }

    @Override
    public E peek() {
        /* Returns the object at the front of the queue without removing it. */
        if (data[front] != null) {
            return (data[front]);
        }
        return null;
    }

    @Override
    public E poll() {
        /* Returns the object at the front of the queue and removes it. */
        if (isEmpty()) {
            return null;
        }
        size--;
        E r = data[front]; // save front to val to be read
        data[front] = null;
        front = (front + 1)%(data.length); // advance the front pointer
        return r; // return the object that is being removed
    }

    @Override
    public boolean isEmpty() {
        /* Returns true if the queue is empty; otherwise, returns null. */
        return (front == rear && size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    /* Returns a Java list containing the items currently in the queue.
     * The item at the front of the queue is the first item of the list (at index 0).
     */
    @Override
    public List<E> asList() {
        List<E> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(data[(front+i)%data.length]);
        }
        return list;
    }
}
