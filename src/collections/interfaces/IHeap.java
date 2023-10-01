package collections.interfaces;

import collections.dataStructures.Heap;

public interface IHeap<E extends Comparable<E> > {

    int parent(int i);

    int left(int i);

    int right(int i);

    void swap(int i, int j);

    void heapify(int i);

    void buildHeap();

    Heap<E> heapSort();

}
