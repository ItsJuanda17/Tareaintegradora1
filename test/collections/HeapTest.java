import collections.dataStructures.Heap;
import collections.interfaces.IHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeapTest {

    private IHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new Heap<>();
    }

    @Test
    void testParent() {
        assertEquals(0, heap.parent(1));
        assertEquals(0, heap.parent(2));
        assertEquals(1, heap.parent(3));
        assertEquals(1, heap.parent(4));
        assertEquals(2, heap.parent(5));
        assertEquals(2, heap.parent(6));
    }

    @Test
    void testLeft() {
        assertEquals(1, heap.left(0));
        assertEquals(3, heap.left(1));
        assertEquals(5, heap.left(2));
        assertEquals(7, heap.left(3));
    }

    @Test
    void testRight() {
        assertEquals(2, heap.right(0));
        assertEquals(4, heap.right(1));
        assertEquals(6, heap.right(2));
        assertEquals(8, heap.right(3));
    }

    @Test
    void testSwap() {
        Integer[] array = { 1, 2, 3, 4 };
        heap = new Heap<>();
        heap.heap = array;
        heap.size = 4;

        heap.swap(0, 3);
        assertEquals(4, heap.heap[0]);
        assertEquals(1, heap.heap[3]);
    }

    @Test
    void testHeapify() {
        Integer[] array = { 4, 3, 2, 1 };
        heap = new Heap<>();
        heap.heap = array;
        heap.size = 4;

        heap.heapify(0);
        assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, heap.heap);

        heap.heapify(1);
        assertArrayEquals(new Integer[] { 4, 1, 2, 3 }, heap.heap);
    }

    @Test
    void testBuildHeap() {
        Integer[] array = { 4, 3, 2, 1 };
        heap = new Heap<>();
        heap.heap = array;
        heap.size = 4;

        heap.buildHeap();
        assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, heap.heap);

        Integer[] expected = { 4, 3, 2, 1 };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], heap.heap[i]);
        }
    }
}
