package collections.dataStructures;

import collections.interfaces.IPriorityQueue;
import exception.QueueException;

// PriorityQueue extiende de Heap
public class PriorityQueue<T extends Comparable<T> > extends Heap<T> implements IPriorityQueue<T> {

    public PriorityQueue() {
        super();
    }
    @Override
    public T getRoot(){
        return heap[0];
    }

    @Override
    public void insert(T key) {
        if (isFull()) {
            resize(2*heap.length);
        }
        setKey(size, key);
        size++;
    }

    @Override
    public T extractRoot() {
        if (isEmpty()) {
            return null;
        }
        T root = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        heapify(0);
        return root;
    }


    @Override
    public void setKey(int i, T key){
        heap[i] = key;
        while (i>0 && !invariant(i)){
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void resize(int capacity) {
        System.arraycopy(heap, 0, heap = (T[]) new Comparable[capacity], 0, size + 1);
    }

    private boolean isFull() {
        return size == heap.length - 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
