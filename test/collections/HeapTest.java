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


        String expected = (
                "\t-12\n" +
                "\t-1\n" +
                "\t-8\n"
                );
        String actual = heap.toString();
        assertEquals(expected, actual);
    }


    @Test
    void testToStringWithEmptyHeap() {
        String expected = "None";
        String actual = heap.toString();
        assertEquals(expected, actual);
    }



    @Test
    void testBuildHeap() {
        // Test 1 : BuildHeap
        heap.controllerTest(5);
        heap.controllerTest(10);
        heap.controllerTest(7);
        heap.buildHeap();

        String expected = (
                "\t-10\n" +
                "\t-5\n" +
                "\t-7\n"
                );
        String actual = heap.toString();
        assertEquals(expected, actual);

        // Test 2 BuildHeap
        Heap<Integer> anotherHeap = new Heap<>();
        anotherHeap.controllerTest(15);
        anotherHeap.controllerTest(2);
        anotherHeap.controllerTest(20);
        anotherHeap.buildHeap();

        expected = (
                "\t-20\n" +
                "\t-2\n" +
                "\t-15\n"
                );
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


        String expected = (
                "\t-10\n" +
                "\t-10\n" +
                "\t-5\n" +
                "\t-5\n" +
                "\t-5\n" +
                "\t-3\n"
                );
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
        String expected = (
                "\t-25\n" +
                "\t-15\n" +
                "\t-20\n" +
                "\t-10\n" +
                "\t-5\n"
                );
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

        String expected = (
                "\t-3\n" +
                "\t-10\n" +
                "\t-15\n"
                );
        String actual = sortedHeap.toString();
        assertEquals(expected, actual);

        // test2 heapSort
        Heap<Integer> anotherHeap = new Heap<>();
        anotherHeap.controllerTest(50);
        anotherHeap.controllerTest(30);
        anotherHeap.controllerTest(20);
        sortedHeap = anotherHeap.heapSort();

        expected = (
                "\t-20\n" +
                "\t-30\n" +
                "\t-50\n"
                );
        actual = sortedHeap.toString();
        assertEquals(expected, actual);
    }
}
