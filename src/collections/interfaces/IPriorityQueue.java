package collections.interfaces;

import exception.QueueException;

public interface IPriorityQueue<E> {

    E getRoot();

    void insert(E element);

    E extractRoot() throws QueueException;

    void setKey(int T, E key);
}
