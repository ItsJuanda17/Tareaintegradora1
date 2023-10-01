package collections.dataStructures;

import collections.interfaces.IHeap;

import java.util.Arrays;

public class Heap<T extends Comparable<T>> implements IHeap<T> {

    protected T[] heap;
    protected int size;

    public Heap() {
        heap = (T[]) new Comparable[10];
        size = 0;
    }

    // HEAP METHODS
    protected boolean invariant(int i){
        return heap[parent(i)].compareTo(heap[i]) >= 0;
    }

    @Override
    public int parent(int i) {
        return (i - 1) / 2;
    }

    @Override
    public int left(int i) {
        return 2 * i+1;
    }

    @Override
    public int right(int i) {
        return 2 * i + 2;
    }

    @Override
    public void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @Override
    public void heapify(int i) {
        int left = left(i);
        int right = right(i);
        int largest = i;

        if (left < size && heap[left] != null && (heap[largest] == null || heap[left].compareTo(heap[largest]) > 0)) {
            largest = left;
        }

        if (right < size && heap[right] != null && (heap[largest] == null || heap[right].compareTo(heap[largest]) > 0)) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    @Override
    public void buildHeap() {
        size = heap.length;
        for (int i = heap.length / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    @Override
    public Heap<T> heapSort() {
        Heap<T> sortedHeap = new Heap<>();
        sortedHeap.heap = Arrays.copyOf(heap, heap.length);
        sortedHeap.size = size;

        sortedHeap.buildHeap();
        for (int i = sortedHeap.heap.length - 1; i >= 0; i--) {
            sortedHeap.swap(0, i);
            sortedHeap.size--;
            sortedHeap.heapify(0);
        }
        return sortedHeap;
    }

    @Override
    public String toString(){
        StringBuilder msg = new StringBuilder();
        for (T t : heap) {
            if (t != null) {
                msg.append(t).append(" ");
            }
        }
        return msg.toString();
    }

}