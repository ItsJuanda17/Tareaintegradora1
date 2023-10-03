package collections;

import static org.junit.jupiter.api.Assertions.*;

import collections.dataStructures.Queue;
import collections.interfaces.IQueue;

import exception.QueueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class QueueTest {

    private IQueue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Test
    void testEnqueueOneElement() {
        // Arrange
        String item = "item";
        String element = "";
        boolean exceptionThrown = false;

        // Act
        try {
            queue.enqueue(item);
            element = queue.front();
        } catch (QueueException e) {
            exceptionThrown = true;
        }

        // Assert
        assertFalse(exceptionThrown);
        assertFalse(queue.isEmpty());
        assertEquals(item, element);
    }

    @Test
    void testEnqueueTwoElements() {
        // Arrange
        String item1 = "item1";
        String item2 = "item2";
        String element1 = "";
        String element2 = "";
        boolean exceptionThrown = false;

        // Act
        try {
            queue.enqueue(item1);
            queue.enqueue(item2);
            element1 = queue.dequeue();
            element2 = queue.dequeue();
        } catch (QueueException e) {
            exceptionThrown = true;
        }

        // Assert
        assertFalse(exceptionThrown);
        assertEquals(item1, element1);
        assertEquals(item2, element2);
    }

    @Test
    void testEnqueueOneElementGetsOnFront() {
        // Arrange
        String item1 = "item2";
        String item2 = "item1";
        String front = "";
        boolean exceptionThrown = false;

        // Act
        try{
            queue.enqueue(item1);
            queue.enqueue(item2);
            front = queue.front();
        }catch (QueueException e){
            exceptionThrown = true;
        }

        // Assert
        assertFalse(exceptionThrown);
        assertEquals(item1, front);
        assertNotEquals(item2, front);
    }

    @Test
    void testFrontEmptyQueue() {
        // Arrange
        boolean exceptionThrown = false;

        // Act
        try{
            queue.front();
        }catch (QueueException e){
            exceptionThrown = true;
        }

        // Assert
        assertTrue(exceptionThrown);
    }

    @Test
    void testFrontNonEmpty() {
        // Arrange
        String item1 = "item1";
        String front = "";
        boolean exceptionThrown = false;

        // Act
        try {
            queue.enqueue(item1);
            front = queue.front();
        } catch (QueueException e) {
            exceptionThrown = true;
        }

        // Assert
        assertFalse(exceptionThrown);
        assertEquals(item1, front);
    }

    @Test
    void testFrontAfterDequeue() {
        // Arrange
        // Arrange
        String item1 = "item1";
        String front = "";
        boolean exceptionThrown = false;

        // Act
        try {
            queue.enqueue(item1);
            queue.dequeue();
            front = queue.front();
        } catch (QueueException e) {
            exceptionThrown = true;
        }

        // Assert
        assertTrue(exceptionThrown);
        assertNotEquals(item1, front);
    }

    @Test
    void testDequeueWhenCreated() {
        // Act
        boolean exceptionThrown = false;

        // Assert
        try {
            queue.dequeue();
        } catch (QueueException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }

    @Test
    void testDequeueWhenOneElements() {
        // Arrange
        String item1 = "item1";
        String dequeue = "";
        boolean exceptionThrown = false;

        // Act
        try {
            queue.enqueue(item1);
            dequeue = queue.dequeue();
        } catch (QueueException e) {
            exceptionThrown = true;
        }

        // Assert
        assertFalse(exceptionThrown);
        assertEquals(item1, dequeue);
        assertTrue(queue.isEmpty());
    }

    @Test
    void testDequeueWhenMultipleElements() {
        // Arrange
        String item1 = "item1";
        String item2 = "item2";
        String item3 = "item3";
        String dequeue1 = "";
        String dequeue2 = "";
        boolean exceptionThrown = false;

        // Act
        try {
            queue.enqueue(item1);
            queue.enqueue(item2);
            queue.enqueue(item3);
            dequeue1 = queue.dequeue();
            dequeue2 = queue.dequeue();
        }catch (QueueException e){
            exceptionThrown = true;
        }

        // Assert
        assertFalse(exceptionThrown);
        assertEquals(item1, dequeue1);
        assertEquals(item2, dequeue2);
        assertFalse(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterCreation() {
        // Act - Assert
        assertTrue(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterEnqueue() {
        // Arrange
        String item = "item1";
        boolean exceptionThrown = false;

        try {
            queue.enqueue(item);
        }catch (QueueException e){
            exceptionThrown = true;
        }

        // Act - Assert
        assertFalse(exceptionThrown);
        assertFalse(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterDequeuMultipleElements() {
        // Arrange
        String item1 = "item1";
        String item2 = "item2";
        boolean exceptionThrown = false;

        try {
            queue.enqueue(item1);
            queue.enqueue(item2);
            queue.dequeue();
            queue.dequeue();
        }catch (QueueException e){
            exceptionThrown = true;
        }

        // Act - Assert
        assertFalse(exceptionThrown);
        assertTrue(queue.isEmpty());
    }
}