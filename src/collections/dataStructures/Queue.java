package collections.dataStructures;

import collections.interfaces.IQueue;
import exception.QueueException;

public class Queue<E> implements IQueue<E> {

    private Node<E> front;
    private Node<E> back;
    private int size;

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public E front() throws QueueException {
        if(isEmpty()) {
            throw new QueueException("The Queue is empty");
        }else {
            return front.getItem();
        }
    }

    @Override
    public E dequeue() throws QueueException {
        if(isEmpty()) {
            throw new QueueException("The Queue is empty");
        }else{
            E item = front.getItem();
            front = front.getNext();
            --size;
            return item;
        }
    }

    @Override
    public void enqueue(E item) throws QueueException {
        if(item == null){
            throw new QueueException("Item cannot be null");
        }else {
            Node<E> node = new Node<>(item);
            if (isEmpty()) {
                this.front = node;
            } else {
                this.back.setNext(node);
            }
            this.back = node;
            ++size;
        }
    }

    @Override
    public String toString(){
        return printQueue();
    }

    public String printQueue(){
        StringBuilder str = new StringBuilder();
        if(isEmpty()){
            str.append("None");
        }else {
            Node<E> current = front;
            while (current != null) {
                str.append("\t-").append(current.getItem()).append("\n");
                current = current.getNext();
            }
        }
        return str.toString();
    }
}
