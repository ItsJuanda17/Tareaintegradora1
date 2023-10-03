package collections.interfaces;

import exception.QueueException;

public interface IQueue<E> {

    public boolean isEmpty();

    public E front() throws QueueException ;

    public E dequeue()throws QueueException;

    public void enqueue(E item) throws QueueException;

}
