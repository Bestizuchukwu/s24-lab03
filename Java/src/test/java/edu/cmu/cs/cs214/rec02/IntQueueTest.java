package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
        mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        IntQueue queue = new LinkedIntQueue();
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        IntQueue queue = new LinkedIntQueue();
        assertNull(queue.peek());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        IntQueue queue = new LinkedIntQueue();
        queue.enqueue(1);
        assertEquals(Integer.valueOf(1), queue.peek());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        IntQueue queue = new LinkedIntQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertFalse(queue.isEmpty());
        assertEquals(Integer.valueOf(2), queue.peek());
    }

    @Test
    public void testContent() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testEnqueueCapacity() {
        for (int i = 0; i < ArrayIntQueue.INITIAL_SIZE; i++) {
            assertTrue(mQueue.enqueue(i));
        }
        assertEquals(ArrayIntQueue.INITIAL_SIZE, mQueue.size());

        assertTrue(mQueue.enqueue(ArrayIntQueue.INITIAL_SIZE));
        assertEquals(ArrayIntQueue.INITIAL_SIZE + 1, mQueue.size());
    }

    @Test
    public void testDequeueFromEmptyQueue() {
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testPeekFromEmptyQueue() {
        assertNull(mQueue.peek());
    }

    @Test
    public void testSizeWhenEmpty() {
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testSizeAfterEnqueue() {
        mQueue.enqueue(1);
        assertEquals(1, mQueue.size());
    }
}
