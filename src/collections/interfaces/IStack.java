package collections.interfaces;

import exception.StackException;

public interface IStack<E> {

    int size();
    boolean isEmpty();
    void push(E item) throws StackException;
    E top() throws StackException;
    E pop() throws StackException;

}

