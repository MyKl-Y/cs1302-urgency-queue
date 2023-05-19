package cs1302.test;

import cs1302.p3.*;
import cs1302.gen.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.Comparator;
import java.util.LinkedList;

/** Tester class for p3. */
public class QueueTester {

    public static Comparator<Integer> compare = (Integer i1, Integer i2) -> {
        return i1 - i2;
    };
    public static UrgencyQueue<Integer> queue = new CustomLinkedUrgencyQueue<Integer>(compare);

    public static void main(String[] args) {
        testSize();
        testEnqueue();
        testToString();
        testDequeue();
        testClear();
        testEnqueueAll();
        testToArray();
        testFilter();
    } // main

    /** Test size Method. */
    public static void testSize() {
        if (queue.size() == 0) {
            System.out.println("size: Test Passed");
        } else {
            System.out.println("size: Test Failed");
            System.exit(0);
        } // if
    } // testSize

    /** Test enqueue Method. */
    public static void testEnqueue() {
        queue.enqueue(3);
        System.out.println(queue.toString());
        queue.enqueue(0);
        System.out.println(queue.toString());
        queue.enqueue(2);
        System.out.println(queue.toString());
        queue.enqueue(4);
        System.out.println(queue.toString());
        queue.enqueue(1);
        System.out.println(queue.toString());
        if (queue.toString().equals("[4, 3, 2, 1, 0]")) {
            System.out.println("enqueue: Test Passed");
        } else {
            System.out.println("enqueue: Test Failed");
            System.exit(0);
        } // if
    } // testEnqueue

    /** Test toString Method. */
    public static void testToString() {
        System.out.println(queue);
        if (queue.toString().equals("[4, 3, 2, 1, 0]")) {
            System.out.println("toString: Test Passed");
        } else {
            System.out.println("toString: Test Failed");
        } //if
    } // testToString

    /** Test dequeue Methods. */
    public static void testDequeue() {
        System.out.println(queue.dequeue());
        System.out.println(queue);
        Consumer<Integer> consumer = (Integer i) -> System.out.println(i);
        queue.dequeue(consumer);
        System.out.println(queue);
        queue.dequeueMany(2, consumer);
        System.out.println(queue);
        queue.enqueue(1);
        queue.enqueue(-1);
        System.out.println(queue);
        System.out.println(queue.dequeueMany(2));
        if (queue.toString().equals("[-1]")) {
            System.out.println(queue.toString());
            System.out.println("dequeue: Test Passed");
        } else {
            System.out.println("dequeue: Test Failed");
        } // if
    } // testDequeue

    /** Test clear Method. */
    public static void testClear() {
        queue.clear();
        if (queue.size() == 0) {
            System.out.println(queue);
            System.out.println(queue.size());
            System.out.println("clear: Test Passed");
        } else {
            System.out.println("clear: Test Failed");
        } // if
    } // testClear

    /** Test enqueueAll Method. */
    public static void testEnqueueAll() {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        LinkedList<Integer> queue2 = new LinkedList<>();
        queue2.add(1);
        queue2.add(2);
        queue2.add(3);
        System.out.println(queue2);
        System.out.println(queue);
        queue.<Integer>enqueueAll(queue2);
        System.out.println(queue);
        System.out.println("enqueueAll: Test Passed");
    } // testEnqueueAll

    /** Test toArray Method. */
    public static void testToArray() {
        queue.enqueue(3);
        queue.enqueue(0);
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(1);
        System.out.println(queue.toString());

        Integer[] arr = queue.toArray(Integer[]::new);

        for (Integer element : arr) {
            System.out.println(element);
        } // for
        System.out.println("toArray: Test Passed");
    } // testToArray

    /** Test filter Method. */
    public static void testFilter() {
        queue.clear();
        queue.enqueue(3);
        queue.enqueue(0);
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(1);
        System.out.println(queue);

        Predicate<Integer> tester = (Integer i) -> {
            if (i > 2) {
                return true;
            } else {
                return false;
            } // if
        };

        System.out.println(queue.filter(tester));
        if (queue.filter(tester).toString().equals("[4, 3]")) {
            System.out.println("filter: Test Passed");
        } else {
            System.out.println("filter: Test Failed");
        } // if
    } // testFilter

} // QueueTester
