package collections;

import collections.dataStructures.PriorityQueue;
import exception.QueueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    private PriorityQueue<Integer> priorityQueue;

    @BeforeEach
    void setUp() {
        priorityQueue = new PriorityQueue<>();
    }
    @Test
    public void testInsertAndExtractRoot() throws QueueException {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Insert values into the priority queue
        priorityQueue.insert(5);
        priorityQueue.insert(7);
        priorityQueue.insert(3);
        priorityQueue.insert(1);

        // Extract the root (should be the largest value)
        int extracted = priorityQueue.extractRoot();

        assertEquals(7, extracted);
    }


    @Test
    public void testInsertAndExtractMultiple() throws QueueException {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int[] values = {5, 7, 3, 1};
        for (int value : values) {
            priorityQueue.insert(value);
        }
        int[] expectedOrder = {7, 5, 3, 1};
        for (int expected : expectedOrder) {
            assertEquals(expected, priorityQueue.extractRoot());
        }
    }



    @Test
    public void testSetKey() throws QueueException {

        priorityQueue.insert(3);
        priorityQueue.insert(5);
        priorityQueue.insert(2);

        priorityQueue.setKey(0, 5);


        assertEquals(5, priorityQueue.extractRoot());
        assertEquals(3, priorityQueue.extractRoot());
        assertEquals(2, priorityQueue.extractRoot());
    }


    @Test
    public void testGetRootWhenEmpty()  {
        assertNull(priorityQueue.getRoot());
    }


    @Test
    public void testExtractRootWhenEmpty() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Try to extract the root when the priority queue is empty
        assertNull(priorityQueue.extractRoot());
    }


    @Test
    public void testIsEmpty() throws QueueException {
        assertTrue(priorityQueue.isEmpty());

        priorityQueue.insert(1);

        assertFalse(priorityQueue.isEmpty());

        priorityQueue.extractRoot();

        assertTrue(priorityQueue.isEmpty());
    }
}
