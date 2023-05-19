package cs1302.p3;

import cs1302.gen.UrgencyQueue;
import cs1302.gen.Node;
import java.util.function.Consumer;

/**
 * Abstract parent class for a basic urgency queue.
 */
public abstract class BaseLinkedUrgencyQueue<Type> implements UrgencyQueue<Type> {

    // INSTANCE VARIABLES
    int size;
    Node<Type> head;

    // CONSTRUCTOR
    /**
     * Construct a {@code BaseLinkedUrgencyQueue}. This constructor is never
     * intended to be called directly via {@code new}; instead, it should only
     * be called in child class constructors using {@code super()}.
     */
    public BaseLinkedUrgencyQueue() {
        size = 0;
        head = null;
    } // BaseLinkedUrgencyQueue

    // METHODS
    /** {@inheritDoc} */
    @Override
    public int size() {
        return size;
    } // peek()

    /** {@inheritDoc} */
    @Override
    public Type peek() {
        if (size == 0) {
            throw new IllegalStateException("No items in queue");
        } // if
        return head.getItem();
    } // size()

    /** {@inheritDoc}*/
    @Override
    public String toString() {
        String ret = "[";
        Node<Type> temp = head;
        for (int i = 0; i < size; i++) {
            ret += temp.getItem().toString();
            temp = temp.getNext();
            if (i < (size - 1)) {
                ret += ", ";
            } // if
        } // for
        ret += "]";
        return ret;
    } // toString()

    /** {@inheritDoc} */
    @Override
    public Type dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        } // if
        Type temp = head.getItem();
        head = head.getNext();
        size--;
        return temp;
    } // dequeue()

    /** {@inheritDoc} */
    @Override
    public void dequeue(Consumer<Type> action) {
        if (action == null) {
            throw new NullPointerException("action cannot be null");
        } else if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        } // if

        Type temp = head.getItem();
        head = head.getNext();
        size--;
        action.accept(temp);

    } // dequeue(action)

    /** {@inheritDoc} */
    @Override
    public void dequeueMany(int num, Consumer<Type> action) {
        if (action == null) {
            throw new NullPointerException("action cannot be null");
        } else if (num < 0) {
            throw new IllegalArgumentException("num has to ge greater than  or equal to 0");
        } else if (num > size) {
            throw new IllegalStateException("num cannot be greater than the size of queue");
        } // if

        for (int i = 0; i < num; i++) {
            this.dequeue(action);
        } // for
    } // dequeueMany(num, action)

    /** {@inheritDoc} */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            this.dequeue();
        } // for
        size = 0;
    } // clear()

    /** {@inheritDoc} */
    @Override
    public <SubType extends Type> boolean enqueueAll(Iterable<SubType> items) {
        if (items == null) {
            throw new NullPointerException("items cannot be null");
        }

        for (SubType item : items) {
            if (item == null) {
                throw new IllegalArgumentException("item cannot be null");
            }
            this.enqueue(item);
        } // for

        return true;
    } // enqueueAll(items)

    /** {@inheritDoc} */
    @Override
    public Type[] toArray(java.util.function.IntFunction<Type[]> generator) {
        if (generator == null) {
            throw new NullPointerException("generator cannot be null");
        } // if

        Type [] arr = generator.apply(size);
        Node<Type> temp = head;
        for (int i = 0; i < size; i++) {
            arr[i] = temp.getItem();
            temp = temp.getNext();
        } // for
        return arr;
    } // toArray(generator)

} // BaseLinkedUrgencyQueue<Type>
