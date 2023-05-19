package cs1302.p3;

import cs1302.gen.UrgencyQueue;
import cs1302.gen.Node;

/**
 * Child of BaseStringList that makes an urgency queue through comparing objects only if they extend
 * comparable so it can use its build in compareTo() method.
 */
public class LinkedUrgencyQueue<Type extends Comparable<Type>>
    extends BaseLinkedUrgencyQueue<Type> {

    // CONSTRUCTOR
    /**
     * Construct a {@code LinkedUrgencyQueue}.
     */
    public LinkedUrgencyQueue() {
        super();
    } // LinkedUrgencyQueue

    // METHODS
    /** {@inheritDoc} */
    @Override
    public boolean enqueue(Type item) {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        } // if

        int pos = 0;
        // Places item in queue at the beginning if the queue is empty.
        if (size == 0) {
            head = new Node<Type>(item);
            size++;
            return true;
        } // if
        Node<Type> temp = head;
        Node<Type> newNode = new Node<Type>(item);
        for (int i = 0; i < size; i++) {
            int ret = item.compareTo(temp.getItem());
            // Places if the item is more urgent.
            if (item.compareTo(head.getItem()) > 0) {
                newNode.setNext(head);
                head = newNode;
                size++;
                return true;
            // Places item at end if there is no next node.
            } else if (ret < 0 && temp.getNext() == null) {
                temp.setNext(newNode);
                size++;
                return true;
            // Places item based on urgency and moves the tail end to the end of the new node.
            } else if (ret > 0) {
                Node<Type> temp2 = head;
                for (int j = 0; j < pos - 1; j++) {
                    temp2 = temp2.getNext();
                } // for
                newNode.setNext(temp);
                temp2.setNext(newNode);
                size++;
                return true;
            // Moves temp to the next location for tracing and position.
            } else {
                pos++;
                temp = temp.getNext();
            } // if
        } // for
        size++;
        return true;
    } // enqueue(item)

    /** {@inheritDoc} */
    @Override
    public UrgencyQueue<Type> dequeueMany(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("num cannot be less than 0.");
        } else if ( num > size()) {
            throw new IllegalStateException("num cannot be greater than size");
        } // if
        UrgencyQueue<Type> temp = new LinkedUrgencyQueue<>();
        for (int i = 0; i < num; i++) {
            temp.enqueue(this.dequeue());
        } // for
        return temp;
    } // dequeueMany(num)

    /** {@inheritDoc} */
    @Override
    public UrgencyQueue<Type> filter(java.util.function.Predicate<Type> cond) {
        if (cond == null) {
            throw new NullPointerException("cond cannot be null");
        } // if

        UrgencyQueue<Type> newQueue = new LinkedUrgencyQueue<>();
        Node<Type> temp = head;
        for (int i = 0; i < size; i++) {
            if (cond.test(temp.getItem())) {
                newQueue.enqueue(temp.getItem());
            } // if
            temp = temp.getNext();
        } // for
        return newQueue;
    } // filter(cond)

} // LinkedUrgencyQueue<Type extends Comparable<Type>>
