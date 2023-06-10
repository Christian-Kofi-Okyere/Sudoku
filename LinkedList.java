/*
 * Author: Christian Okyere
 * Title: Solving Sudoku
 * File: LinkedList.java
 */

public class LinkedList<T> implements Stack<T>{

    private static class Node<T>{
        // class for the node

        T data;
        Node<T> next;
        // Node<T> prev;

        public Node(T item){
            data = item;
            next = null;
            // prev = null;
        }

        public Node(T item, Node<T> next){
            data = item;
            this.next = next;
            // this.prev = null;
        }

        public T getData(){
            return data;
        }

        public Node<T> getNext(){
            return next;
        }

        public Node(T item, Node<T> next, Node<T> prev){
            data = item;
            this.next = next;
            // this.prev = prev;
        }

        // public void setNext(Node<T> newNext){
        //     this.next = newNext;
        // }

    }

    private Node<T> head;
    private Node<T> tail;

    private int size;

    public LinkedList(){
        size = 0;
        head = null;
        tail = null;
    }

    

    // adds an item to the last of the LikedList
    public void addLast(T item){
        if (size == 0){
            Node<T> temp = new Node<>(item);
            head = temp;
            tail = temp;
        } else {
            Node<T> temp = new Node<>(item, null, tail);
            tail.next = temp;
            tail = temp;
        }
        size++;
    }


    // private class LLIterator implements Iterator<T> {

    // }
    
    // adds an item to the LinkedList
    public void add(T item){
        Node<T> newNode = new Node<T>(item, head);
        head = newNode;
        size++;
    }

    // returns the size of the list
    public int size(){
        return size;
    }
    
    //returns the item given by a specified index
    public T get (int index){
        Node<T> walker = head;
        for(int i = 0; i < index; i++){
            walker = walker.getNext();
        }

        return walker.getData();
    }

    // inserts the item at the specified position in the list
    public void add(int index, T item){

        if (index == 0){
            add(item);
            return;
        }

        Node<T> walker = head;
        for (int i = 0; i < index - 1; i++) {
            walker = walker.getNext();
        }

        Node<T> current = new Node<T> (item , walker.getNext());
        size++;
        walker.next = current;
    }

    // empties the list
    public void clear(){
        head = null;
        size = 0;
    }

    // public boolean contains(Object o){
    //     for (T data : this){
    //         if (data.equals(o)){
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // public boolean equals(Object o){

    // }

    //returns true if the list is empty
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    // removes the first item in the list
    public T remove(){
        Node<T> first = head;
        head = head.next;
        size--;
        return first.getData();
    }

    //removes the item at specified position
    public T remove(int index){

        if (index == 0){
            return remove();
        }

        Node<T> walker = head;
        for (int i = 0; i < index - 1; i++){
            walker = walker.getNext();
        }

        Node<T> stuff = walker.next;
        walker.next = walker.next.next;
        return stuff.getData();
    }

    // returns a string representation of the list
    public String toString(){
        String output =  "[";
        Node<T> walker = head;

        if (this.isEmpty()){
            return "[]";
        }

        for (int i = 0; i < size - 1; i++){
            output += walker.getData();
            output += ",";
            walker = walker.getNext();
        }

        return output + "]";
    }

    // Stack methods   
    @Override
    // returns  the index of the first item in the list but does not remove it
    public T peek() {
        return get(0);
    }
    
    // method of stack that removes element on top of stack and returns it
    @Override
    public T pop() {
        T first_item = get(0);
        remove(0);
        return first_item;
    }

    // method of stack that adds item to the top of the stack
    @Override
    public void push(T item) {
        add(0, item);  
    }


    // @Override
    // public Iterator<T> iterator() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    // }

}
