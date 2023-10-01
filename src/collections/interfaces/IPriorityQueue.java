package collections.interfaces;

public interface IPriorityQueue<E> {

    E getRoot();

    void insert(E element);

    E extractRoot();

    void setKey(int T, E key);
}
