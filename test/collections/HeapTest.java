

package collections.dataStructures.test;

import collections.dataStructures.Heap;
import collections.interfaces.IHeap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeapTest {

    private IHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new Heap<>();
    }

    @Test
    public void testParent() {
        assertEquals(0, heap.parent(1));
        assertEquals(1, heap.parent(3));
        assertEquals(0, heap.parent(2));
        assertEquals(2, heap.parent(5));
    }

    @Test
    public void testParentWithDifferentValues() {
        assertEquals(0, heap.parent(4));
        assertEquals(3, heap.parent(9));
        assertEquals(2, heap.parent(10));
        assertEquals(3, heap.parent(8));
    }

    @Test
    public void testLeft() {
        assertEquals(1, heap.left(0));
        assertEquals(3, heap.left(1));
        assertEquals(2, heap.left(3));
        assertEquals(5, heap.left(2));
    }

    @Test
    public void testLeftWithDifferentValues() {
        assertEquals(2, heap.left(1));
        assertEquals(4, heap.left(0));
        assertEquals(6, heap.left(3));
        assertEquals(4, heap.left(5));
    }

    @Test
    public void testRight() {
        assertEquals(2, heap.right(0));
        assertEquals(4, heap.right(1));
        assertEquals(3, heap.right(3));
        assertEquals(6, heap.right(2));
    }

    @Test
    public void testRightWithDifferentValues() {
        assertEquals(3, heap.right(1));
        assertEquals(5, heap.right(0));
        assertEquals(7, heap.right(4));
        assertEquals(5, heap.right(7));
    }

    @Test
    public void testSwap() {
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);

        heap.swap(0, 2);

        assertEquals(3, (int) heap.extractMax());
        assertEquals(2, (int) heap.extractMax());
        assertEquals(1, (int) heap.extractMax());
    }

    @Test
    public void testSwapWithDifferentValues() {
        heap.insert(5);
        heap.insert(9);
        heap.insert(1);

        heap.swap(1, 2);

        assertEquals(9, (int) heap.extractMax());
        assertEquals(1, (int) heap.extractMax());
        assertEquals(5, (int) heap.extractMax());
    }

    @Test
    public void testBuildHeap() {
        heap.insert(4);
        heap.insert(10);
        heap.insert(3);
        heap.insert(5);
        heap.insert(1);

        heap.buildHeap();

        assertEquals(10, (int) heap.extractMax());
        assertEquals(5, (int) heap.extractMax());
        assertEquals(4, (int) heap.extractMax());
        assertEquals(3, (int) heap.extractMax());
        assertEquals(1, (int) heap.extractMax());
    }

    @Test
    public void testBuildHeapWithDifferentValues() {
        heap.insert(8);
        heap.insert(6);
        heap.insert(2);
        heap.insert(7);
        heap.insert(9);

        heap.buildHeap();

        assertEquals(9, (int) heap.extractMax());
        assertEquals(8, (int) heap.extractMax());
        assertEquals(7, (int) heap.extractMax());
        assertEquals(6, (int) heap.extractMax());
        assertEquals(2, (int) heap.extractMax());
    }

    @Test
    public void testHeapSort() {
        heap.insert(3);
        heap.insert(1);
        heap.insert(4);
        heap.insert(2);

        IHeap<Integer> sortedHeap = heap.heapSort();

        assertEquals(1, (int) sortedHeap.extractMax());
        assertEquals(2, (int) sortedHeap.extractMax());
        assertEquals(3, (int) sortedHeap.extractMax());
        assertEquals(4, (int) sortedHeap.extractMax());
    }

    @Test
    public void testHeapSortWithDifferentValues() {
        heap.insert(7);
        heap.insert(5);
        heap.insert(2);
        heap.insert(9);

        IHeap<Integer> sortedHeap = heap.heapSort();

        assertEquals(2, (int) sortedHeap.extractMax());
        assertEquals(5, (int) sortedHeap.extractMax());
        assertEquals(7, (int) sortedHeap.extractMax());
        assertEquals(9, (int) sortedHeap.extractMax());
    }

    @Test
    public void testToString() {
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);

        String expected = "\t-3\n\t-2\n\t-1\n";
        assertEquals(expected, heap.toString());
    }

    @Test
    public void testToStringWithDifferentValues() {
        heap.insert(5);
        heap.insert(7);
        heap.insert(1);

        String expected = "\t-7\n\t-5\n\t-1\n";
        assertEquals(expected, heap.toString());
    }

}