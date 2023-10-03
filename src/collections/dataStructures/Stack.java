package collections.dataStructures;

import collections.interfaces.IStack;
import exception.StackException;

public class Stack<E> implements IStack<E> {

    private Node<E> top;
    private int size;

    public Stack(){}

    public Stack(Node<E> top) {
        this.top = top;
    }

    public Stack(Stack<E> otherStack) throws StackException {
        this.top = null;
        while (!otherStack.isEmpty()) {
            E item = otherStack.pop();
            this.push(item);
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push(E item) throws StackException { // adds a new element at the top of the stack
        if(item == null){
            throw new StackException("Cannot push a null item onto the stack");
        }
        Node<E> node = new Node<>(item);
        if (!isEmpty()) {
            node.setNext(this.top);
        }
        this.top = node;
        size++;
    }

    @Override
    public E top() throws StackException {
        if(isEmpty()){
            throw new StackException("The stack is empty");
        }else{
            return top.getItem();
        }
    }

    @Override
    public E pop() throws StackException { // deletes the element at the top of the stack
        if(isEmpty()) {
            throw new StackException("The stack is empty");
        }else{
            size--;
            E item = top.getItem();
            top = top.getNext();
            return item;
        }
    }

    public void reverse() throws StackException {
        Stack<E> temp = new Stack<>();
        while(!isEmpty()){
            temp.push(this.pop());
        }
        this.top = temp.top;
    }

    @Override
    public String toString(){
        return printStack();
    }

    public String printStack(){
        Node<E> current = top;
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

    /*
    public String printStack() throws StackException {
        StringBuilder str = new StringBuilder("{");
        Stack<E> temp = new Stack<>(this);
        while (!isEmpty()){
            str.append(temp.top()).append(", ");
            temp.pop();
        }
        str.append("\b\b}");
        return str.toString();
    }
     */
}
