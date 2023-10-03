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
        Node<E> current = front;
        StringBuilder str = new StringBuilder("[");
        while (current != null) {
            str.append(current.getItem()).append(", ");
            current = current.getNext();
        }
        if(str.toString().equals("[")){
            str.append(" ]");
        }else{
            str.append("\b\b]");
        }
        return str.toString();
    }
}
