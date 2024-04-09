package edu.cmu.cs.cs214.rec02;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedIntQueueTest {

    private IntQueue mQueue;

    @Before
    public void setUp() {
        // Initialize a new LinkedIntQueue before each test
        mQueue = new LinkedIntQueue();
    }

    @After
    public void tearDown() {
        // Clear the queue after each test
        mQueue.clear();
    }

    @Test
    public void testEnqueueDequeue() {
        // Enqueue elements and then dequeue to ensure order is maintained
        assertTrue(mQueue.isEmpty());
        assertTrue(mQueue.enqueue(1));
        assertFalse(mQueue.isEmpty());
        assertEquals(1, mQueue.size());

        assertEquals(Integer.valueOf(1), mQueue.peek());
        assertEquals(Integer.valueOf(1), mQueue.dequeue());
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testPeek() {
        // Test peek method with non-empty and empty queue
        assertNull(mQueue.peek());

        mQueue.enqueue(1);
        assertEquals(Integer.valueOf(1), mQueue.peek());
        assertEquals(1, mQueue.size());
    }

    @Test
    public void testIsEmpty() {
        // Test isEmpty method for both empty and non-empty queues
        assertTrue(mQueue.isEmpty());

        mQueue.enqueue(1);
        assertFalse(mQueue.isEmpty());

        mQueue.dequeue();
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testClear() {
        // Test clear method
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
        assertEquals(0, mQueue.size());
        assertNull(mQueue.peek());
    }

    @Test
    public void testSize() {
        // Test size method for empty and non-empty queues
        assertEquals(0, mQueue.size());

        mQueue.enqueue(1);
        assertEquals(1, mQueue.size());

        mQueue.enqueue(2);
        assertEquals(2, mQueue.size());

        mQueue.dequeue();
        assertEquals(1, mQueue.size());

        mQueue.clear();
        assertEquals(0, mQueue.size());
    }
}
