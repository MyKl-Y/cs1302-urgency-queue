package cs1302.p3;

import cs1302.gen.UrgencyQueue;
import cs1302.gen.Node;

/**
 * Child of BaseLinkedUrgencyQueue that creates a queue based on urgency through a custom comparator
 * that is passed in by the user.
 */
public class CustomLinkedUrgencyQueue<Type> extends BaseLinkedUrgencyQueue<Type> {

    // INSTANCE VARIABLES
    private java.util.Comparator<Type> comparator;

    // CONSTRUCTOR
    /**
     * Construct a {@code CustomLinkedUrgencyQueue}.
     *
     * @param cprt a function that lets you determine the urgency order
     *     between two items
     * @throws NullPointerException if {@code cprt} is {@code null}
     */
    public CustomLinkedUrgencyQueue(java.util.Comparator<Type> cprt) {
        super();
        comparator = cprt;
    } // CustomLinkedUrgencyQueue

    // METHODS
    /**
     * Custom enqueue method meaning it uses the comparator passed in by user.
     *
     * <p>
     * {@inhertDoc}
     */
    @Override
    public boolean enqueue(Type item) {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        } // if
        int pos = 0;
        // Places node in beginning since queue is empty.
        if (size == 0) {
            head = new Node<Type>(item);
            size++;
            return true;
        } // if
        Node<Type> temp = head;
        Node<Type> newNode = new Node<Type>(item);
        for (int i = 0; i < size; i++) {
            int ret = comparator.compare(item, temp.getItem());
            // Places node in front.
            if (comparator.compare(item, head.getItem()) > 0) {
                newNode.setNext(head);
                head = newNode;
                size++;
                return true;
            // Places node at end.
            } else if (ret < 0 && temp.getNext() == null) {
                temp.setNext(newNode);
                size++;
                return true;
            // Places node in between others.
            } else if (ret > 0) {
                Node<Type> temp2 = head;
                for (int j = 0; j < pos - 1; j++) {
                    temp2 = temp2.getNext();
                } // for
                newNode.setNext(temp);
                temp2.setNext(newNode);
                size++;
                return true;
            // Places temp at next location and increases position to continue tracing.
            } else {
                pos++;
                temp = temp.getNext();
            } // if
        } // for
        size++;
        return true;

    } // enqueue(item)

    /**
     * dequeueMany for CustomLinkedUrgencyQueue.
     *
     * <p>
     * {@inhertDoc}
     */
    @Override
    public UrgencyQueue<Type> dequeueMany(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("num cannot be less than 0.");
        } else if ( num > size()) {
            throw new IllegalStateException("num cannot be greater than size");
        } // if
        UrgencyQueue<Type> temp = new CustomLinkedUrgencyQueue<>(comparator);
        for (int i = 0; i < num; i++) {
            temp.enqueue(this.dequeue());
        } // for
        return temp;

    } // dequeueMany(num)

    /**
     * Filter for CustomLinkedUrgencyQueue.
     *
     * <p>
     * {@inhertDoc}
     */
    @Override
    public UrgencyQueue<Type> filter(java.util.function.Predicate<Type> cond) {
        if (cond == null) {
            throw new NullPointerException("cond cannot be null");
        } // if

        UrgencyQueue<Type> newQueue = new CustomLinkedUrgencyQueue<>(comparator);
        Node<Type> temp = head;
        for (int i = 0; i < size; i++) {
            if (cond.test(temp.getItem())) {
                newQueue.enqueue(temp.getItem());
            } // if
            temp = temp.getNext();
        } // for
        return newQueue;
    } // filter(cond)

} // CustomLinkedUrgencyQueue<Type>
