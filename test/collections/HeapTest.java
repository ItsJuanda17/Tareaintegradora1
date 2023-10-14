package collections;

import collections.dataStructures.Heap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeapTest {

    private Heap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new Heap<>();
    }



    @Test
    void testToString() {
        // Test 1 toString
        heap.controllerTest(12);
        heap.controllerTest(1);
        heap.controllerTest(8);


        String expected = "12 1 8 ";
        String actual = heap.toString();
        assertEquals(expected, actual);
    }


    @Test
    void testToStringWithEmptyHeap() {

        String expected = "";
        String actual = heap.toString();
        assertEquals(expected, actual);
    }



    @Test
    void testBuildHeap() {
        // Test 1 : BuildHeap
        heap.controllerTest(10);
        heap.controllerTest(5);
        heap.controllerTest(7);
        heap.buildHeap();

        String expected = "10 5 7 ";
        String actual = heap.toString();
        assertEquals(expected, actual);

        // Test 2 BuildHeap
        Heap<Integer> anotherHeap = new Heap<>();
        anotherHeap.controllerTest(15);
        anotherHeap.controllerTest(2);
        anotherHeap.controllerTest(20);
        anotherHeap.buildHeap();

        expected = "20 2 15 ";
        actual = anotherHeap.toString();
        assertEquals(expected, actual);
    }


    @Test
    void testBuildHeapWithDuplicateValues() {

        heap.controllerTest(5);
        heap.controllerTest(5);
        heap.controllerTest(5);
        heap.controllerTest(10);
        heap.controllerTest(10);
        heap.controllerTest(3);
        heap.buildHeap();


        String expected = "10 10  5 5 5 3 ";
        String actual = heap.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testBuildHeapWithDifferentValues() {
        // Prueba de buildHeap con diferentes valores
        heap.controllerTest(20);
        heap.controllerTest(15);
        heap.controllerTest(25);
        heap.controllerTest(10);
        heap.controllerTest(5);
        heap.buildHeap();

        // Asegurar que la construcción del montículo se realiza correctamente
        String expected = "25 15 20 10 5 ";
        String actual = heap.toString();
        assertEquals(expected, actual);
    }


    @Test
    void testHeapSort() {
        // test 1 heapSort
        heap.controllerTest(15);
        heap.controllerTest(3);
        heap.controllerTest(10);
        Heap<Integer> sortedHeap = heap.heapSort();

        String expected = "3 10 15 ";
        String actual = sortedHeap.toString();
        assertEquals(expected, actual);

        // test2 heapSort
        Heap<Integer> anotherHeap = new Heap<>();
        anotherHeap.controllerTest(50);
        anotherHeap.controllerTest(30);
        anotherHeap.controllerTest(20);
        sortedHeap = anotherHeap.heapSort();

        expected = "20 30 50 ";
        actual = sortedHeap.toString();
        assertEquals(expected, actual);
    }
}
