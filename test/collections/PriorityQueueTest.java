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
    void testInsertAndExtractRoot() throws QueueException {
        // Insert elements into the priority queue
        priorityQueue.insert(5);
        priorityQueue.insert(3);
        priorityQueue.insert(8);

        // Extract elements and check if they are in the correct order
        assertEquals(3, priorityQueue.extractRoot());
        assertEquals(5, priorityQueue.extractRoot());
        assertEquals(8, priorityQueue.extractRoot());

        // Ensure the priority queue is empty
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    void testResize() {
        // Insert more elements than the initial capacity to trigger resizing
        for (int i = 1; i <= 20; i++) {
            priorityQueue.insert(i);
        }

        // Check if resizing worked and the elements are still in the correct order
        for (int i = 1; i <= 20; i++) {
            assertEquals(i, priorityQueue.extractRoot());
        }

        // Ensure the priority queue is empty
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    void testExceptionWhenExtractingFromEmptyQueue() {
        assertThrows(QueueException.class, () -> priorityQueue.extractRoot());
    }

    @Test
    void testSetKey() {
        // Insert elements into the priority queue
        priorityQueue.insert(5);
        priorityQueue.insert(3);
        priorityQueue.insert(8);

        // Change the value of an element and check if it's still in the correct order
        priorityQueue.setKey(1, 1);
        assertEquals(1, priorityQueue.extractRoot());
        assertEquals(3, priorityQueue.extractRoot());
        assertEquals(8, priorityQueue.extractRoot());

        // Ensure the priority queue is empty
        assertTrue(priorityQueue.isEmpty());
    }
}
